package ma.youcode.smartbank.daos.implementations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ma.youcode.smartbank.daos.interfaces.RequestDao;
import ma.youcode.smartbank.dtos.RequestFilterDTO;
import ma.youcode.smartbank.entities.History;
import ma.youcode.smartbank.entities.Request;
import ma.youcode.smartbank.singleton.HibernateTools;

import java.util.*;

@ApplicationScoped
public class RequestDaoImpl extends GenericDaoImpl<Request , UUID> implements RequestDao {
    public RequestDaoImpl(){
        super(Request.class);
    }

    @Override
    public List<Optional<Request>> findAllRequestsWithStatuses() {
        List<Optional<Request>> requests = new ArrayList<>();
        EntityManager entityManager = HibernateTools.getInstance().getEntityManager();

        try{
            entityManager.getTransaction().begin();
            String queryString = "SELECT  r  FROM Request r " +
                    " LEFT JOIN FETCH r.statusHistories sh" +
                    " ORDER BY sh.changedAt DESC ";
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

    @Override
    public List<Optional<Request>> findFilteredRequests(RequestFilterDTO filterDTO) {
        List<Optional<Request>> filteredRequests = new ArrayList<>();
        EntityManager entityManager = HibernateTools.getInstance().getEntityManager();

        try {

            entityManager.getTransaction().begin();
            if (filterDTO != null) {
                StringBuilder queryString = new StringBuilder("SELECT r FROM Request r LEFT JOIN FETCH r.statusHistories WHERE 1=1");
                boolean hasFilters = false;
                if (filterDTO.getName() != null && !filterDTO.getName().isEmpty()) {
                    queryString.append(" AND r.lastname LIKE :lastname");
                    hasFilters = true;
                }

                if (filterDTO.getCreationDate() != null) {
                    queryString.append(" AND r.createdat LIKE :createdAt");
                    hasFilters = true;
                }

                if (!hasFilters) {
                    return Collections.emptyList();
                }

                TypedQuery<Request> query = entityManager.createQuery(queryString.toString(), Request.class);

                if (filterDTO.getName() != null && !filterDTO.getName().isEmpty()) {
                    query.setParameter("lastName", "%" + filterDTO.getName() + "%");
                }

                if (filterDTO.getCreationDate() != null) {
                    query.setParameter("createdAt", filterDTO.getCreationDate());
                }

                List<Request> test = query.getResultList();
                filteredRequests = query.getResultList().stream().map(Optional::of).toList();
                System.out.println("Filtered Requests: " + filteredRequests);
            }
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            e.printStackTrace();
        } finally {
            entityManager.close();
        }

        return filteredRequests;
    }

}
