package com.ampota.card;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ampota.shared.client.UserProfileClient;

import xyz.quadx.xpay.shared.firebase.FirebaseConfig;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(repositoryImplementationPostfix = "CustomImpl")
@Import({
    //AuditLogConfig.class,
    FirebaseConfig.class,
})
@EnableFeignClients(basePackageClasses = {
    UserProfileClient.class
})
public class AmpotaCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmpotaCardApplication.class, args);
    }

}
