package com.hangu.tool.dubbo.center;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.constants.RegistryConstants;
import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.common.utils.UrlUtils;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.RegistryFactory;
import org.apache.dubbo.registry.integration.RegistryProtocol;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * @author wuzhenhong
 * @date 2023/12/22 15:06
 */
@Component
public class ManualRegistryToDubboCenter implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    @Value("${server.port}")
    private Integer port;

    @Value("${app.name}")
    private String appName;

    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, RegistryConfig> registryConfigMap = applicationContext.getBeansOfType(RegistryConfig.class);
        if(Objects.isNull(registryConfigMap) || registryConfigMap.isEmpty()) {
            return;
        }
        RegistryConfig registryConfig = registryConfigMap.entrySet().iterator().next().getValue();
        RegistryProtocol registryProtocol = RegistryProtocol.getRegistryProtocol();
        Field field = ReflectionUtils.findField(RegistryProtocol.class, "registryFactory");
        field.setAccessible(true);
        org.apache.dubbo.registry.RegistryFactory registryFactory = null;
        try {
            registryFactory = (RegistryFactory) field.get(registryProtocol);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        // String protocol, String host, int port, String path
        // zookeeper://192.168.203.233:2181
        List<URL> urls = org.apache.dubbo.common.utils.UrlUtils.parseURLs(registryConfig.getAddress(), new HashMap<>());
        Registry registry = registryFactory.getRegistry(urls.get(0));
        //String protocol, String host, int port, String path
        URL url = new URL(CommonConstants.DEFAULT_PROTOCOL, NetUtils.getLocalHost(), port, appName);
        registry.register(url);
        // 打印已注册到注册中心的路径
        List<URL> consumerList = registry.lookup(UrlUtils.getEmptyUrl(appName, RegistryConstants.PROVIDERS_CATEGORY));
        Optional.ofNullable(consumerList)
            .orElse(Collections.emptyList())
            .stream().forEach(e -> {
                System.out.println("host:" + e.getIp() + "   port:"+e.getPort());
            });

//        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
//        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = requestMappingHandlerMapping.getHandlerMethods();
//        handlerMethodMap.keySet().stream().forEach(requestMappingInfo -> {
//        });

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}