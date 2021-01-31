package com.rayes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringBootCurrencyStatApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootCurrencyStatApp.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8088"));
        app.run(args);
    }
}
