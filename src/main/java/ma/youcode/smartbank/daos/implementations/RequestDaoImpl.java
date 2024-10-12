package ma.youcode.smartbank.daos.implementations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ma.youcode.smartbank.daos.interfaces.RequestDao;
import ma.youcode.smartbank.dtos.RequestFilterDTO;
import ma.youcode.smartbank.entities.History;
import ma.youcode.smartbank.entities.Request;
import ma.youcode.smartbank.singleton.HibernateTools;

import java.util.*;
import java.util.logging.Logger;

@ApplicationScoped
public class RequestDaoImpl extends GenericDaoImpl<Request , UUID> implements RequestDao {
    public RequestDaoImpl(){
        super(Request.class);
    }
    private final Logger logger = Logger.getLogger(RequestDaoImpl.class.getName());

    @Override
    public List<Optional<Request>> findAllRequestsWithStatuses() {
        List<Optional<Request>> requests = new ArrayList<>();
        EntityManager entityManager = HibernateTools.getInstance().getEntityManager();

        try{
            entityManager.getTransaction().begin();
            String queryString = "SELECT  r  FROM Request r " +
                    " LEFT JOIN FETCH r.histories sh" +
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
                StringBuilder hql = new StringBuilder(
                        "SELECT r FROM Request r" +
                        " JOIN FETCH r.histories h" +
                        " WHERE h.changedAt = ( " +
                        "SELECT MAX(h2.changedAt) FROM History" +
                        " h2 WHERE h2.request.requestId = r.requestId " +
                        ")");

                if (filterDTO.getStatusName() != null && !filterDTO.getStatusName().isEmpty()) {
                    hql.append(" AND h.status.statusName LIKE :statusName");
                }
                if (filterDTO.getCreationDate() != null ) {
                    hql.append(" AND DATE(r.createdAt) = :createdAt");
                }

                TypedQuery<Request> query = entityManager.createQuery(hql.toString() , Request.class);

                if (filterDTO.getStatusName() != null && !filterDTO.getStatusName().isEmpty()){
                    query.setParameter("statusName" , "%" + filterDTO.getStatusName() + "%");
                }
                if (filterDTO.getCreationDate() != null ) {
                    query.setParameter("createdAt" , java.sql.Date.valueOf(filterDTO.getCreationDate()));
                }
                filteredRequests = query.getResultStream().map(Optional::of).toList();
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
