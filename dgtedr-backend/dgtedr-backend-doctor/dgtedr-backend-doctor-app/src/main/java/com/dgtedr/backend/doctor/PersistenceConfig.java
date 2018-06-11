package com.dgtedr.backend.doctor;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.google.common.collect.ImmutableMap;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.mynt.core.jpa.service",
        "com.mynt.payment.**.service",
        "xyz.mynt.payment.**.service"},
    repositoryImplementationPostfix = "CustomImpl")
public class PersistenceConfig {

    @Autowired
    private Environment env;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
        EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource())
            .packages(
                    "com.efs.core.jpa.model",
                    "com.dgtedr.backend.doctor.model")
            .persistenceUnit("primary")
            .properties(ImmutableMap.of(
                "hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"),
                "hibernate.dialect", env.getProperty("spring.jpa.database-platform")))
            .build();
    }

    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) throws PropertyVetoException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory(builder).getObject());
        return transactionManager;
    }

}