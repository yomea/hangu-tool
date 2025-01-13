package com.hangu.tool.serialize.fastjson.annotated;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.function.Supplier;

/**
 * 空值序列化
 * @author wuzhenhong
 * @date 2022/9/22 17:33
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD})
public @interface NullJSONField {

    Class<? extends Supplier<?>> nullsUsing();
}