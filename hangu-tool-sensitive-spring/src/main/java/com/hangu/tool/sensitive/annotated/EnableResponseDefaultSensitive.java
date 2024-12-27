package com.hangu.tool.sensitive.annotated;

import com.hangu.tool.sensitive.config.AutoResponseResolveDefaultSensitiveConfig;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * @author wuzhenhong
 * @date 2024/12/27 16:52
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(AutoResponseResolveDefaultSensitiveConfig.class)
public @interface EnableResponseDefaultSensitive {

}
