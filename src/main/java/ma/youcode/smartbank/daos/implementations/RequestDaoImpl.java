package ma.youcode.smartbank.daos.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ma.youcode.smartbank.daos.interfaces.RequestDao;
import ma.youcode.smartbank.entities.Request;
import ma.youcode.smartbank.singleton.HibernateTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RequestDaoImpl extends GenericDaoImpl<Request , UUID> implements RequestDao {

    public RequestDaoImpl(){
        super(Request.class);
    }

    @Override
    public List<Optional<Request>> findAllRequestsWithStatuses() {
        EntityManager entityManager = HibernateTools.getInstance().getEntityManager();
        List<Optional<Request>> requests = new ArrayList<>();
        try{
            entityManager.getTransaction().begin();
            String queryString = "SELECT  r  FROM Request r " +
                    " LEFT JOIN FETCH r.statusHistories";
            TypedQuery<Request> query = entityManager.createQuery(queryString , Request.class);
            List<Request> listResult = query.getResultList();
            requests = listResult.stream().map(Optional::of).toList();
            entityManager.getTransaction().commit();

        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return requests;
    }

    @Override
    public Optional<Request> findById(UUID uuid) {
        return Optional.empty();
    }
}
