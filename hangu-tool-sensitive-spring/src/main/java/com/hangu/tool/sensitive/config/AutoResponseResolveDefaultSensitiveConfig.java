package com.hangu.tool.sensitive.config;

import com.hangu.tool.sensitive.resolve.ResponseBodyResolve;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuzhenhong
 * @date 2024/12/27 16:14
 */
@Configuration
public class AutoResponseResolveDefaultSensitiveConfig {

    @Bean
    public ResponseBodyResolve responseBodyResolve() {
        return new ResponseBodyResolve();
    }

}
