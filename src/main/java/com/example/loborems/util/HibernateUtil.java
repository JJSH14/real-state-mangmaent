package com.example.loborems.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.example.loborems.models.User;
import com.example.loborems.models.Role;
import com.example.loborems.models.Permission;

import java.util.Properties;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = buildSessionFactory();
        } catch (Exception ex) {
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory buildSessionFactory() {
        try {
            // Create Configuration
            Configuration configuration = new Configuration();

            // Add properties
            Properties hibernateProperties = new Properties();

            // Database connection settings with no password
            hibernateProperties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            hibernateProperties.put("hibernate.connection.url",
                    "jdbc:mysql://localhost:3306/LoboDB?allowPublicKeyRetrieval=true&useSSL=false");
            hibernateProperties.put("hibernate.connection.username", "root");
            hibernateProperties.put("hibernate.connection.password", "");

            // SQL dialect
            hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

            // Connection pool settings
            hibernateProperties.put("hibernate.c3p0.min_size", "5");
            hibernateProperties.put("hibernate.c3p0.max_size", "20");
            hibernateProperties.put("hibernate.c3p0.timeout", "300");
            hibernateProperties.put("hibernate.c3p0.max_statements", "50");
            hibernateProperties.put("hibernate.c3p0.idle_test_period", "3000");

            // Other Hibernate properties
            hibernateProperties.put("hibernate.show_sql", "true");
            hibernateProperties.put("hibernate.format_sql", "true");
            hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
            hibernateProperties.put("hibernate.current_session_context_class", "thread");

            // Apply properties to configuration
            configuration.setProperties(hibernateProperties);

            // Add annotated classes
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Role.class);
            configuration.addAnnotatedClass(Permission.class);

            // Create ServiceRegistry
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            // Build and return SessionFactory
            return configuration.buildSessionFactory(serviceRegistry);

        } catch (Exception ex) {
            System.err.println("SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public static boolean testConnection() {
        try {
            getSessionFactory().openSession().close();
            return true;
        } catch (Exception e) {
            System.err.println("Connection test failed: " + e.getMessage());
            return false;
        }
    }
}