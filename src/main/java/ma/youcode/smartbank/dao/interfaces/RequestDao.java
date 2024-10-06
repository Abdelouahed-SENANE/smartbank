package ma.youcode.smartbank.dao.interfaces;

import ma.youcode.smartbank.entities.Request;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RequestDao extends GenericDao<Request , UUID> {

    List<Optional<Request>> findAllRequestsWithStatuses();
}
