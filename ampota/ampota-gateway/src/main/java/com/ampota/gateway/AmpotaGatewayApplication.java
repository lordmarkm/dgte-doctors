package com.ampota.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import com.ampota.shared.client.UserProfileClient;

import xyz.quadx.xpay.shared.audit.AuditLogConfig;
import xyz.quadx.xpay.shared.firebase.FirebaseConfig;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = {
    UserProfileClient.class
})
@Import({
    AuditLogConfig.class,
    FirebaseConfig.class
})
public class AmpotaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmpotaGatewayApplication.class, args);
    }

}
