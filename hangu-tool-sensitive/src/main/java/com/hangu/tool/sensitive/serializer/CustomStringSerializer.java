package com.hangu.tool.sensitive.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import com.hangu.tool.common.util.FieldReflectorUtil;
import com.hangu.tool.common.util.SimpleHashMap;
import com.hangu.tool.sensitive.annotated.Sensitive;
import com.hangu.tool.sensitive.config.DefaultSensitiveConfig;
import com.hangu.tool.sensitive.service.DesensitizationService;
import com.hangu.tool.sensitive.service.EncryptService;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Objects;

public class CustomStringSerializer extends StdScalarSerializer<String> {

    private final SimpleHashMap<Class<?>, Object> CACHE = new SimpleHashMap<>(16);

    public CustomStringSerializer() {
        super(String.class, false);
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        // 获取到当前要解析到的字段的名字
        String name = gen.getOutputContext().getCurrentName();
        Class<?> currentParseObj = gen.getOutputContext().getCurrentValue().getClass();
        Field field = FieldReflectorUtil.findField(currentParseObj, name);
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
        Class<DesensitizationService>[] desensitizationServiceClasses = sensitive.desensitization();
        if (Objects.isNull(desensitizationServiceClasses) || desensitizationServiceClasses.length == 0) {
            Class<? extends DesensitizationService> desensitizationServiceClass = DefaultSensitiveConfig.getDefaultDesensitization();
            if (Objects.isNull(desensitizationServiceClass)) {
                throw new RuntimeException("默认的脱敏策略不能为空！");
            } else {
                desensitizationServiceClasses = new Class[]{desensitizationServiceClass};
            }
        }

        Class<? extends EncryptService> encryptClass = DefaultSensitiveConfig.getDefaultEncrypt();
        if (Objects.isNull(encryptClass)) {
            throw new RuntimeException("默认的加密策略不能为空！");
        }

        String desensitizationValue = value;
        for (Class<DesensitizationService> desensitizationClass : desensitizationServiceClasses) {
            DesensitizationService desensitizationService = this.getByCache(desensitizationClass);
            desensitizationValue = desensitizationService.desensitization(value);
        }

        // TODO：在并发高的时候，采用加密的方式用于反向脱敏可能影响性能
        // TODO：下个版本将提供脱敏逻辑接口，让用户自定义如何脱敏
        // TODO：最好结合业务去脱敏
        String encryptValue;
        EncryptService encryptService = this.getByCache(encryptClass);
        encryptValue = encryptService.encrypt(value);
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
        try {
            value = clazz.newInstance();
            CACHE.put(clazz, value);
            return (T) value;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
