package com.hangu.tool.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author wuzhenhong
 * @date 2024/12/27 9:48
 */
public class FieldReflectorUtil {

    private static final List<Class<?>> CLASS_LIST = Arrays
        .asList(Object.class, String.class, BigDecimal.class, double.class, Double.class
            , int.class, Integer.class, long.class, Long.class, float.class, Float.class, short.class, Short.class
            , byte.class, Byte.class, boolean.class, Boolean.class);

    public static List<Field> reflectFields(Object parameter) {
        if (Objects.isNull(parameter)) {
            return Collections.emptyList();
        }
        Class<?> clazz = parameter.getClass();
        if (CLASS_LIST.contains(clazz) || clazz.getName().startsWith("java.lang")) {
            return Collections.emptyList();
        }
        // 只处理java bean，这种字段上才可能存在注解
        return FieldReflectorUtil.getFieldList(clazz);
    }

    private static List<Field> getFieldList(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        FieldReflectorUtil.getFields(fieldList, clazz);
        return fieldList;
    }

    private static void getFields(List<Field> fieldList, Class<?> tClass) {

        if (tClass == null || tClass == Object.class || tClass.getName().startsWith("java.lang")) {
            return;
        }
        Field[] fields = tClass.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())) {
                    fieldList.add(field);
                }

            }
        }
        FieldReflectorUtil.getFields(fieldList, tClass.getSuperclass());
    }

    public static Field findField(Class<?> clazz, String name) {
        return FieldReflectorUtil.findField(clazz, name, null);
    }

    public static Field findField(Class<?> clazz, String name, Class<?> type) {
        for (Class<?> searchType = clazz; Object.class != searchType && searchType != null;
            searchType = searchType.getSuperclass()) {
            Field[] fields = getDeclaredFields(searchType);
            Field[] var5 = fields;
            int var6 = fields.length;

            for (int var7 = 0; var7 < var6; ++var7) {
                Field field = var5[var7];
                if ((name == null || name.equals(field.getName())) && (type == null || type.equals(field.getType()))) {
                    return field;
                }
            }
        }

        return null;
    }

    private static Field[] getDeclaredFields(Class<?> clazz) {
        try {
            Field[] result = clazz.getDeclaredFields();
            return result;
        } catch (Throwable var3) {
            throw new IllegalStateException(
                "Failed to introspect Class [" + clazz.getName() + "] from ClassLoader [" + clazz.getClassLoader()
                    + "]", var3);
        }
    }

}
