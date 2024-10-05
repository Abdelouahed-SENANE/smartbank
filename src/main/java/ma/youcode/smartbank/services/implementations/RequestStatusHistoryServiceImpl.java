package ma.youcode.smartbank.services.implementations;

import ma.youcode.smartbank.dao.implementations.RequestStatusHistoryHistoryDaoImp;
import ma.youcode.smartbank.dao.interfaces.RequestStatusHistoryDao;
import ma.youcode.smartbank.entities.RequestStatusHistory;
import ma.youcode.smartbank.services.interfaces.RequestStatusHistoryService;

public class RequestStatusHistoryServiceImpl implements RequestStatusHistoryService {
    private final RequestStatusHistoryDao historyDao;
    public RequestStatusHistoryServiceImpl(RequestStatusHistoryDao historyDao){
        this.historyDao = historyDao;
    }

    @Override
    public void save(RequestStatusHistory history) {
        this.historyDao.save(history);
    }
}
