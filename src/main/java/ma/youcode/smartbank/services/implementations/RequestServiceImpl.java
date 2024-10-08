package ma.youcode.smartbank.services.implementations;

import ma.youcode.smartbank.daos.interfaces.RequestDao;
import ma.youcode.smartbank.dtos.RequestFilterDTO;
import ma.youcode.smartbank.entities.Request;
import ma.youcode.smartbank.services.interfaces.RequestService;

import java.util.List;
import java.util.Optional;

public class RequestServiceImpl implements RequestService {
    private final RequestDao requestDaoImpl;

    public RequestServiceImpl(RequestDao requestDaoImpl) {
        this.requestDaoImpl = requestDaoImpl;
    }

    @Override
    public void save(Request request) {
        requestDaoImpl.save(request);
    }

    @Override
    public List<Optional<Request>> getAllRequestsAndStatuses() {
        return requestDaoImpl.findAllRequestsWithStatuses();
    }

    @Override
    public List<Optional<Request>> getFilteredRequests(RequestFilterDTO filterDTO) {
        return requestDaoImpl.findFilteredRequests(filterDTO);
    }

    @Override
    public void delete(Request request) {
        requestDaoImpl.delete(request);
    }

    @Override
    public void update(Request request) {
        requestDaoImpl.update(request);
    }
}
