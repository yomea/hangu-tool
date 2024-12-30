package com.hangu.tool.dubbo.center.config;

import com.hangu.tool.dubbo.center.listener.RegistryLocalToDubboCenter;
import com.hangu.tool.dubbo.center.properties.RegistryLocalToDubboProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author wuzhenhong
 * @date 2024/12/30 10:50
 */
@ConditionalOnProperty(prefix = "registry.local.to.dubbo", name = "enable", havingValue = "true")
@AutoConfiguration
@EnableConfigurationProperties(value = RegistryLocalToDubboProperties.class)
public class RegistryLocalToDubboConfig {

    @Bean
    public RegistryLocalToDubboCenter registryLocalToDubboCenter(
        RegistryLocalToDubboProperties registryLocalToDubboProperties) {

        return new RegistryLocalToDubboCenter(registryLocalToDubboProperties);
    }
}
