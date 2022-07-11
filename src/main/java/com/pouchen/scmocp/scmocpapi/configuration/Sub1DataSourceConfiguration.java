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

@Configuration
@EnableJpaRepositories(basePackages = "com.pouchen.scmocp.scmocpapi.entity",
        entityManagerFactoryRef = "sub1EntityManagerFactory",
        transactionManagerRef= "sub1TransactionManager")
public class Sub1DataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.sub1")
    public DataSourceProperties cardDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.sub1.configuration")
    public DataSource cardDataSource() {
        return cardDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "sub1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean sub1EntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(cardDataSource())
                .packages("com.pouchen.scmocp.scmocpapi.entity")
                .build();
    }

    @Bean
    public PlatformTransactionManager sub1TransactionManager(
            final @Qualifier("sub1EntityManagerFactory") LocalContainerEntityManagerFactoryBean sub1EntityManagerFactory) {
        return new JpaTransactionManager(sub1EntityManagerFactory.getObject());
    }

}