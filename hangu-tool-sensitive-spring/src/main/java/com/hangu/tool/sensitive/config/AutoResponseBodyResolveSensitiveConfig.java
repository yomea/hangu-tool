package com.hangu.tool.sensitive.config;

import com.hangu.tool.sensitive.processor.InjectObjectMapperBeanPostProcessor;
import com.hangu.tool.sensitive.resolve.ResponseBodyResolve;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 使用 spring 提供的 JackSon
 *
 * @author wuzhenhong
 * @date 2024/12/27 16:14
 */
@Configuration
public class AutoResponseBodyResolveSensitiveConfig {

    @Bean
    public ResponseBodyResolve responseBodyResolve() {
        return new ResponseBodyResolve();
    }

    @Bean
    public InjectObjectMapperBeanPostProcessor injectObjectMapperBeanPostProcessor() {
        // 使用 springboot 中配置的JackSon
        return new InjectObjectMapperBeanPostProcessor();
    }

}
