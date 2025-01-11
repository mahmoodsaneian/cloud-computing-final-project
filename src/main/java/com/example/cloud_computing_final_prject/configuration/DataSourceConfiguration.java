package com.example.cloud_computing_final_prject.configuration;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager",
        basePackages = {"com.example.cloud_computing_final_prject.repository"}
)
public class DataSourceConfiguration {

    @Value("${MASTER_HOST:jdbc:postgresql://master-svc:5432/postgres}")
    private String masterUrl;

    @Value("${MASTER_USER:user}")
    private String masterUsername;

    @Value("${MASTER_PASSWORD:password}")
    private String masterPassword;

    @Value("${SLAVE_HOST:jdbc:postgresql://slave-svc:5432/postgres}")
    private String slaveUrl;

    @Value("${SLAVE_USER:user}")
    private String slaveUsername;

    @Value("${SLAVE_PASSWORD:password}")
    private String slavePassword;

    @Primary
    @Bean(name = "masterDataSource")
    public DataSource masterDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(masterUrl);
        dataSource.setUsername(masterUsername);
        dataSource.setPassword(masterPassword);
        return dataSource;
    }


    @Bean(name = "slaveDataSource")
    public DataSource slaveDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(slaveUrl);
        dataSource.setUsername(slaveUsername);
        dataSource.setPassword(slavePassword);
        return dataSource;
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mainEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("masterDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.cloud_computing_final_prject.data")
                .persistenceUnit("master")
                .build();
    }

    @Primary
    @Bean(name = "mainTransactionManager")
    public PlatformTransactionManager mainTransactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }


    @Bean(name = "spareEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean spareEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("slaveDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.cloud.ordertrackingapp.entity")
                .persistenceUnit("slave")
                .build();
    }

    @Bean(name = "spareTransactionManager")
    public PlatformTransactionManager spareTransactionManager(
            @Qualifier("spareEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
