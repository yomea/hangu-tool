package com.hangu.tool.sensitive.annotated;

import com.hangu.tool.sensitive.service.DesensitizationService;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记某字段敏感
 *
 * @author wuzhenhong
 * @date 2024/12/23 14:10
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sensitive {

    Class<DesensitizationService>[] desensitization() default {};
}
