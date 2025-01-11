package com.hangu.tool.sensitive.config;

import com.hangu.tool.sensitive.serializer.CustomStringSerializer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * @author wuzhenhong
 * @date 2024/12/27 16:14
 */
@AutoConfiguration
public class AutoSensitiveConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        // 使用前加载下默认配置
        DefaultSensitiveStrategy.loadDefaultSensitive();
        return jacksonObjectMapperBuilder ->
            jacksonObjectMapperBuilder
                .serializerByType(String.class, new CustomStringSerializer());
    }
}
