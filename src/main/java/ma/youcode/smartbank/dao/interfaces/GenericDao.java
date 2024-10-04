package ma.youcode.smartbank.dao.interfaces;


import java.util.List;
import java.util.Optional;

public interface GenericDao<T , ID> {

    void save(T entity);
    Optional<T> findById(ID id);
    void update(T entity);
    List<Optional<T>> findAll();

}
