package com.hangu.tool.sensitive.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hangu.tool.sensitive.resolve.ResponseBodyResolve;
import com.hangu.tool.sensitive.util.JacksonBodyAdviceUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
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
    @ConditionalOnBean(ObjectMapper.class)
    public JacksonBodyAdviceUtil jacksonBodyAdviceUtil(ObjectMapper objectMapper) {
        // 如果存在springBoot自己的，那么就使用springboot的，不过又可能存在springboot虽然注入了Jackson，但是
        // 又修改了的情况
        JacksonBodyAdviceUtil jacksonBodyAdviceUtil = new JacksonBodyAdviceUtil();
        jacksonBodyAdviceUtil.setObjectMapper(objectMapper);
        return jacksonBodyAdviceUtil;
    }

}
