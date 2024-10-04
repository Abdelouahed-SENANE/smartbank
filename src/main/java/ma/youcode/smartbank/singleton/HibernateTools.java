package ma.youcode.smartbank.singleton;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateTools {
    private static  HibernateTools instance;
    private final SessionFactory sessionFactory;

    private  HibernateTools() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static synchronized HibernateTools getInstance() {
        if (instance == null) {
            instance = new HibernateTools();
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void shutdown() {
        getSessionFactory().close();
    }
}
