package ma.youcode.smartbank.daos.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import ma.youcode.smartbank.daos.interfaces.GenericDao;
import ma.youcode.smartbank.entities.Request;
import ma.youcode.smartbank.singleton.HibernateTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GenericDaoImpl<T, ID> implements GenericDao<T, ID> {
    private final Class<T> type;

    public GenericDaoImpl(Class<T> type) {
        this.type = type;
    }

    public EntityManager getEntityManager() {
        return HibernateTools.getInstance().getEntityManager();
    }

    ;

    @Override
    public void save(T entity) {
        EntityManager entityManager = getEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(T entity) {
        EntityManager entityManager = getEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error to update  : " + e.getMessage());
        }
    }

    @Override
    public List<Optional<T>> findAll() {

        EntityManager entityManager = getEntityManager();
        List<Optional<T>> results = new ArrayList<>();
        try{

            String queryString = "SELECT  r  FROM" + type +" r";
            TypedQuery<T> query = entityManager.createQuery(queryString , type);
            List<T> listResult = query.getResultList();
            results = listResult.stream().map(Optional::of).toList();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error to retrieve data " + e.getMessage());
        }finally {
            entityManager.close();
        }
        return results;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.empty();
    }

    @Override
    public void delete(T entity) {
        EntityManager entityManager = getEntityManager();
        Request r = (Request) entity;

        try {
            entityManager.getTransaction().begin();
            T mergedEntity = entityManager.merge(entity);
            entityManager.remove(mergedEntity);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error to delete this : " + e.getMessage());
        }

    }
}
