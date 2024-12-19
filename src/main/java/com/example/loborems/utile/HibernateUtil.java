package com.example.loborems.utile;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import com.example.loborems.modules.Client;

public class HibernateUtil {
    private static HibernateUtil instance = null;
    private static SessionFactory sessionFactory;

   //singleton constructor for session factory
    private HibernateUtil() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            //add class configuration
            configuration.addAnnotatedClass(Client.class);
            StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            sessionFactory = configuration.buildSessionFactory(standardRegistry);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Failed to initialize Hibernate session factory.");
        }
    }
    //singleton instance
    public static HibernateUtil getInstance() {
        if (instance == null) {
            synchronized (HibernateUtil.class) {
                if (instance == null) {
                    instance = new HibernateUtil();
                }
            }
        }
        return instance;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            getInstance();
        }
        return sessionFactory;
    }
}
