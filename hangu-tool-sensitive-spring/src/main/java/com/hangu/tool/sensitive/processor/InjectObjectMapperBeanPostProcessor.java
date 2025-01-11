package com.hangu.tool.sensitive.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hangu.tool.sensitive.util.JacksonBodyAdviceUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author wuzhenhong
 * @date 2025/1/11 16:10
 */
public class InjectObjectMapperBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ObjectMapper) {
            ObjectMapper objectMapper = (ObjectMapper) bean;
            JacksonBodyAdviceUtil.setObjectMapper(objectMapper);
        }
        return bean;
    }
}
