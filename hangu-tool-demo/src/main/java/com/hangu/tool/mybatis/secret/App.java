package com.hangu.tool.mybatis.secret;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(basePackages = {"com.hangu.tool.mybatis.secret.dao.mapper"})
@Slf4j
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
