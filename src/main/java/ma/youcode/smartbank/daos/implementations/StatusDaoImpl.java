package ma.youcode.smartbank.daos.implementations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import ma.youcode.smartbank.daos.interfaces.StatusDao;
import ma.youcode.smartbank.entities.Status;
import ma.youcode.smartbank.singleton.HibernateTools;

import java.util.Optional;

@RequestScoped
public class StatusDaoImpl implements StatusDao {

    public EntityManager getEntityManager() {
        return HibernateTools.getInstance().getEntityManager();
    };

    @Override
    public Optional<Status> findByName(String name) {
        EntityManager entityManager = getEntityManager();
        Status result = null;

        try{
            entityManager.getTransaction().begin();
            String queryStr = "SELECT s FROM Status s WHERE s.statusName = :name";
            TypedQuery<Status> query = entityManager.createQuery(queryStr, Status.class);
            query.setParameter("name" , name);

            result = query.getSingleResult();
            entityManager.getTransaction().commit();

        }catch (NoResultException e) {
            entityManager.getTransaction().rollback();
            return Optional.empty();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }

        return Optional.ofNullable(result);
    }
}
