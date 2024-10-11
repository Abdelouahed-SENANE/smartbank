package ma.youcode.smartbank.daos.interfaces;

import ma.youcode.smartbank.dtos.RequestFilterDTO;
import ma.youcode.smartbank.entities.Request;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RequestDao extends GenericDao<Request , UUID> {

    List<Optional<Request>> findAllRequestsWithStatuses();
    List<Optional<Request>> findFilteredRequests(RequestFilterDTO filterDTO);

}
