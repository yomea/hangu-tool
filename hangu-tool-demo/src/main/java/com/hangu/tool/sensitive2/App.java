package com.hangu.tool.sensitive2;

import com.hangu.tool.sensitive.annotated.EnableResponseBodySensitive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableResponseBodySensitive
@Slf4j
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
