package com.hangu.tool.sensitive.config;

import com.hangu.tool.sensitive.serializer.CustomStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuzhenhong
 * @date 2024/12/27 16:14
 */
@Configuration
public class AutoSensitiveConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder ->
            jacksonObjectMapperBuilder
                .serializerByType(String.class, new CustomStringSerializer());
    }
}