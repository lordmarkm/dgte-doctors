package com.ampota.user;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import xyz.quadx.xpay.shared.email.MailSenderConfig;
import xyz.quadx.xpay.shared.firebase.FirebaseConfig;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(repositoryImplementationPostfix = "CustomImpl")
@Import({
//    AuditLogConfig.class,
    FirebaseConfig.class,
    MailSenderConfig.class
})
public class AmpotaUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmpotaUserApplication.class, args);
    }

}
