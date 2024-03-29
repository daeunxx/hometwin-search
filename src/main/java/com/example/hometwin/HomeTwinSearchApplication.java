package com.example.hometwin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class HomeTwinSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeTwinSearchApplication.class, args);
    }

}
