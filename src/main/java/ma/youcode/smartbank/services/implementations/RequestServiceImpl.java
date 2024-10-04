package ma.youcode.smartbank.services.implementations;

import ma.youcode.smartbank.dao.interfaces.RequestDao;
import ma.youcode.smartbank.entities.Request;
import ma.youcode.smartbank.services.interfaces.RequestService;

public class RequestServiceImpl implements RequestService {
    private final RequestDao requestDaoImpl;

    public RequestServiceImpl(RequestDao requestDaoImpl) {
        this.requestDaoImpl = requestDaoImpl;
    }

    @Override
    public void save(Request request) {
        requestDaoImpl.save(request);
    }
}
