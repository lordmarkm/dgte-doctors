package com.dgtedr.backend.gateway.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.dgtedr.backend.shared.client.SpecialtyClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = SpecialtyClient.class)
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
