package com.pouchen.scmocp.scmocpapi.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

// @Configuration
@EnableJpaRepositories(basePackages = "com.pouchen.scmocp.scmocpapi.dao.pet",
        entityManagerFactoryRef = "petEntityManagerFactory",
        transactionManagerRef= "petTransactionManager")
public class PetDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.pet")
    public DataSourceProperties cardDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.pet.configuration")
    public DataSource cardDataSource() {
        return cardDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "petEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean petEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(cardDataSource())
                .packages("com.pouchen.scmocp.scmocpapi.dao.pet")
                .build();
    }

    @Bean
    public PlatformTransactionManager petTransactionManager(
            final @Qualifier("petEntityManagerFactory") LocalContainerEntityManagerFactoryBean petEntityManagerFactory) {
        return new JpaTransactionManager(petEntityManagerFactory.getObject());
    }

}