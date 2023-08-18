package com.example.rentgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RentGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentGatewayApplication.class, args);
    }

}
