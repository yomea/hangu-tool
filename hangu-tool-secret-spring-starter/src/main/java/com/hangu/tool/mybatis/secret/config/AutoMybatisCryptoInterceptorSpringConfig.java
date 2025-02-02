package com.hangu.tool.mybatis.secret.config;

import com.hangu.tool.mybatis.secret.aspect.EnOrDecryptAspect;
import com.hangu.tool.mybatis.secret.processor.ConfigInterceptorBeanPostProcessor;
import com.hangu.tool.mybatis.secret.processor.ConfigMapperBeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 当加解密插件是通过jar包利用springboot的自动装配的提供方式时，不修改原项目中的 SqlSessionFactory 配置，
 * 那么可以通过以下方式动态加入
 *
 * @author wuzhenhong
 * @date 2024/12/27 10:28
 */
@AutoConfiguration
public class AutoMybatisCryptoInterceptorSpringConfig {

    @Bean
    public EnOrDecryptAspect cryptoAspect() {
        return new EnOrDecryptAspect();
    }

    @Bean
    public ConfigInterceptorBeanPostProcessor configInterceptorBeanPostProcessor() {
        return new ConfigInterceptorBeanPostProcessor();
    }

    @Bean
    public ConfigMapperBeanPostProcessor configMapperBeanPostProcessor() {
        return new ConfigMapperBeanPostProcessor();
    }
}
