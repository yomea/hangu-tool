package com.hangu.tool.sensitive.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import com.hangu.tool.sensitive.annotated.Sensitive;
import com.hangu.tool.sensitive.service.DesensitizationService;
import com.hangu.tool.sensitive.service.EncryptService;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

public class CustomStringSerializer extends StdScalarSerializer<String> {

    private final Map<Class<?>, Object> CACHE = new ConcurrentHashMap<>(8192);

    public CustomStringSerializer() {
        super(String.class, false);
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        // 获取到当前要解析到的字段的名字
        String name = gen.getOutputContext().getCurrentName();
        Class<?> currentParseObj = gen.getOutputContext().getCurrentValue().getClass();
        Field field = ReflectionUtils.findField(currentParseObj, name);
        Sensitive sensitive = field.getAnnotation(Sensitive.class);
        if (Objects.nonNull(sensitive)) {
            this.doDesensitization(gen, name, value, sensitive);
        } else {
            gen.writeString(value);
        }
    }

    @Override
    public boolean isEmpty(SerializerProvider prov, String value) {
        return value == null || value.isEmpty();
    }

    @Override
    public final void serializeWithType(String value, JsonGenerator gen, SerializerProvider provider,
        TypeSerializer typeSer) throws IOException {
        gen.writeString(value);
    }

    @Override
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return createSchemaNode("string", true);
    }

    @Override
    public void acceptJsonFormatVisitor(
        JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        visitStringFormat(visitor, typeHint);
    }

    private void doDesensitization(JsonGenerator gen, String name, String value, Sensitive sensitive)
        throws IOException {
        Class<DesensitizationService> desensitizationServiceClass = sensitive.desensitization();
        DesensitizationService desensitizationService = this.getByCache(desensitizationServiceClass);
        String desensitizationValue = desensitizationService.desensitization(value);
        Class<EncryptService> encryptServiceClass = sensitive.encrypt();
        EncryptService encryptService = this.getByCache(encryptServiceClass);
        String encryptValue = encryptService.encrypt(value);
        gen.writeStartObject();
        gen.writeStringField(name + "Desensitization", desensitizationValue);
        gen.writeStringField(name + "Encrypt", encryptValue);
        gen.writeEndObject();
    }

    private <T> T getByCache(Class<?> clazz) {
        Object value = CACHE.get(clazz);
        if (Objects.nonNull(value)) {
            return (T) value;
        }
        value = BeanUtils.instantiateClass(clazz);
        CACHE.put(clazz, value);
        return (T) value;
    }

}