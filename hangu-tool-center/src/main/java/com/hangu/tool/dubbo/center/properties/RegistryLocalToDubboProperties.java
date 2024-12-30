package com.hangu.tool.dubbo.center.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wuzhenhong
 * @date 2024/12/30 10:49
 */
@ConfigurationProperties(prefix = "registry.local.to.dubbo")
public class RegistryLocalToDubboProperties {

    private boolean enable;

    private Integer port;

    private String appName;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
