package com.hangu.tool.dubbo.center.annotated;

import com.hangu.tool.dubbo.center.config.RegistryLocalToDubboConfig;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * 非springboot的时候使用该注解
 *
 * @author wuzhenhong
 * @date 2024/12/27 16:52
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(RegistryLocalToDubboConfig.class)
public @interface EnableAutoRegistryLocalToCenter {

}
