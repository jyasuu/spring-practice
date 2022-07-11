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
        entityManagerFactoryRef = "sub2EntityManagerFactory",
        transactionManagerRef= "sub2TransactionManager")
public class Sub2DataSourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.sub2")
    public DataSourceProperties cardDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.sub2.configuration")
    public DataSource cardDataSource() {
        return cardDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "sub2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean sub2EntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(cardDataSource())
                .packages("com.pouchen.scmocp.scmocpapi.entity")
                .build();
    }

    @Bean
    public PlatformTransactionManager sub2TransactionManager(
            final @Qualifier("sub2EntityManagerFactory") LocalContainerEntityManagerFactoryBean sub2EntityManagerFactory) {
        return new JpaTransactionManager(sub2EntityManagerFactory.getObject());
    }

}