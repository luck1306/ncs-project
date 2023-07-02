package com.example.ncsproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class NcsProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(NcsProjectApplication.class, args);
    }

}
