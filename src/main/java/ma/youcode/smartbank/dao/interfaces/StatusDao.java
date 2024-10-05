package ma.youcode.smartbank.dao.interfaces;

import ma.youcode.smartbank.entities.Status;

import java.util.Optional;

public interface StatusDao {
    Optional<Status> findByName(String name);
}
