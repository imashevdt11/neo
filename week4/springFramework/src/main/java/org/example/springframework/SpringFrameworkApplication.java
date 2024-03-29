package org.example.springframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.example.springframework.configs", "org.example.springframework.utils"})
public class SpringFrameworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFrameworkApplication.class, args);
    }

}
