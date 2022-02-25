package com.hmc.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.hmc")
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class JavaConfig {
    @Autowired
    private Environment environment;

    private static final String URL = "jdbc:mysql://localhost:3306/hmc";
    private static final String NAME = "root";
    private static final String PASSWORD = "root";


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(getDataSource());
        HibernateJpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter(); //  в качесте JPA провайдера используем хибер
        em.setJpaVendorAdapter(hibernateAdapter);

        Properties prop = new Properties();

        prop.put(org.hibernate.cfg.Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        prop.put(org.hibernate.cfg.Environment.URL, URL);
        prop.put(org.hibernate.cfg.Environment.USER, NAME);
        prop.put(org.hibernate.cfg.Environment.PASS, PASSWORD);
        prop.put(org.hibernate.cfg.Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        prop.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "update");



        em.setJpaProperties(prop);
        em.setPackagesToScan("com.hmc");

        return em;

    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getRequiredProperty("db.url"));
        dataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
        dataSource.setUsername(environment.getRequiredProperty("db.username"));
        dataSource.setPassword(environment.getRequiredProperty("db.password"));

        return dataSource;
    }


    @Bean
    public TransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(getDataSource());
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }
}
