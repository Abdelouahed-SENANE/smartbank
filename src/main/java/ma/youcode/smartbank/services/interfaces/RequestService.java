package ma.youcode.smartbank.services.interfaces;

import ma.youcode.smartbank.entities.Request;

import java.util.List;
import java.util.Optional;

public interface RequestService {

    void save(Request request);
    List<Optional<Request>> getAllRequestsAndStatuses();
}
