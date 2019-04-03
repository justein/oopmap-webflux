package com.nova.lyn.webfluxmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nova"})
public class WebfluxMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxMongoApplication.class, args);
    }

}
