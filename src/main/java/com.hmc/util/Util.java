package com.hmc.util;

import com.hmc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.util.Properties;



public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/hmc";
    private static final String NAME = "root";
    private static final String PASSWORD = "root";
    public static Connection connection;
    public static SessionFactory sessionFactory;

    //EntityManager
    public static SessionFactory getHibernateSession() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties prop = new Properties();

                prop.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                prop.put(Environment.URL, URL);
                prop.put(Environment.USER, NAME);
                prop.put(Environment.PASS, PASSWORD);
                prop.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                prop.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(prop);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
            }
        }
        return sessionFactory;
    }

}
