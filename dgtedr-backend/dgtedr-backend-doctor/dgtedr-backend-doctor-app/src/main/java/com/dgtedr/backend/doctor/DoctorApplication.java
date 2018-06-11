package com.dgtedr.backend.doctor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dgtedr.backend.doctor.service.SpecialtyService;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = SpecialtyService.class,
    repositoryImplementationPostfix = "CustomImpl")
public class DoctorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoctorApplication.class, args);
    }

}
