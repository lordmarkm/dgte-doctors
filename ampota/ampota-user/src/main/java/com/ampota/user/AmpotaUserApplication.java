package com.ampota.user;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(repositoryImplementationPostfix = "CustomImpl")
public class AmpotaUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmpotaUserApplication.class, args);
    }

}
