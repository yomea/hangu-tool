package com.hangu.tool.sensitive3;

import com.hangu.tool.sensitive.annotated.EnableResponseDefaultSensitive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableResponseDefaultSensitive
@Slf4j
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
