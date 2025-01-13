package com.hangu.tool.dubbo.center;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.0")
public class GreetingsServiceImpl implements GreetingsService {
    @Override
    public String sayHello(String name) {
        return "Hello, " + name + "!";
    }
}