package ma.youcode.smartbank.dao.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import ma.youcode.smartbank.dao.interfaces.GenericDao;
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
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return results;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.empty();
    }


}
