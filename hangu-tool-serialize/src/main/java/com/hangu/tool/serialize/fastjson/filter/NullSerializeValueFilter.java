package com.hangu.tool.serialize.fastjson.filter;

import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.ContextValueFilter;
import com.hangu.tool.common.util.SimpleHashMap;
import com.hangu.tool.serialize.fastjson.annotated.NullJSONField;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * 空值序列化 filter
 * @author wuzhenhong
 * @date 2022/9/22 17:38
 */
public class NullSerializeValueFilter implements ContextValueFilter {

    private final SimpleHashMap<Type, Supplier> cache = new SimpleHashMap<>();

    @Override
    public Object process(BeanContext context, Object object, String name, Object value) {

        // 1. 只处理 null 值
        // 2. 当序列化的对象为 Map 内的key时，key与value的类型是没有对象关系的
        // 比如 key是String类型，value可以是其他的任何类型，所以这个context会为 null
        // 我们的注解也没法标注这种key中，像 key -》 BigDecimal这种，value为null的就不能处理
        if(Objects.nonNull(value) || (object instanceof Map) || Objects.isNull(context)) {
            return value;
        }
        Field field = context.getField();
        if(Objects.isNull(field)) {
            return value;
        }
        NullJSONField nullValueDeal = field.getAnnotation(NullJSONField.class);
        if(Objects.isNull(nullValueDeal)) {
            return value;
        }
        Class<? extends Supplier<?>> functionClass = nullValueDeal.nullsUsing();
        Supplier<?> supplier = cache.get(functionClass);
        if(Objects.isNull(supplier)) {
            try {
                supplier = functionClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("fastJson 空值序列化器构建失败！", e);
            }
        }
        //多线程下可能会重复创建，但不影响正确性
        cache.put(functionClass, supplier);
        return supplier.get();
    }
}
