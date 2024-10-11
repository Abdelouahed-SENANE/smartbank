package ma.youcode.smartbank.services.interfaces;

import ma.youcode.smartbank.entities.Status;

import java.util.Optional;

public interface StatusService {
    Optional<Status> getStatusByName(String name);
}
