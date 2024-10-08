package ma.youcode.smartbank.daos.interfaces;


import java.util.List;
import java.util.Optional;

public interface GenericDao<T , ID> {

    void save(T entity);
    Optional<T> findById(ID id);
    void update(T entity);
    List<Optional<T>> findAll();
    void delete(T entity);
}
