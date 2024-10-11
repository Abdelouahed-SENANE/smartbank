package ma.youcode.smartbank.singleton;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class HibernateTools {
    private static  HibernateTools instance;
    private final EntityManagerFactory entityManagerFactory;

    private  HibernateTools() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }

    public static synchronized HibernateTools getInstance() {
        if (instance == null) {
            instance = new HibernateTools();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }


}
