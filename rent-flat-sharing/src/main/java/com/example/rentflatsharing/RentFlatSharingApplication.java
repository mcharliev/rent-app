package com.example.rentflatsharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@ComponentScans({
        @ComponentScan(basePackages = "com.example.rentutils"),
        @ComponentScan(basePackages = "com.example.rentflatsharing"),
})
public class RentFlatSharingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentFlatSharingApplication.class, args);
    }

}
