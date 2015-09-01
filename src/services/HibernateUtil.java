package services;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory =
            buildSessionFactory();


    private static SessionFactory buildSessionFactory() {

        try
        {
            Configuration configuration = new Configuration().configure("services/hibernate.cfg.xml");

            // Since version 4.x, service registry is being used
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties()).build();

            // Create session factory instance
            return configuration.buildSessionFactory(serviceRegistry);
        }

        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Returns the session factory
     * @return
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}


