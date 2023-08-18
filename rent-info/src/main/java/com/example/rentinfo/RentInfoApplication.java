package com.example.rentinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableEurekaClient
@ComponentScans({
        @ComponentScan(basePackages = "com.example.rentutils"),
        @ComponentScan(basePackages = "com.example.rentinfo"),
})
public class RentInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentInfoApplication.class, args);
    }

}
