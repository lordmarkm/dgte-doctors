package com.dgtedr.backend.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.dgtedr.project.shared.client.config.FeignClientConfig;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@Import(FeignClientConfig.class)
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

}
