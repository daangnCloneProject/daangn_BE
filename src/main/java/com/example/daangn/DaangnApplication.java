package com.example.daangn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DaangnApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaangnApplication.class, args);
    }

}
