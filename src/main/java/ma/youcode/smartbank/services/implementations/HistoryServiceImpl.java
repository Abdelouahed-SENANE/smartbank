package ma.youcode.smartbank.services.implementations;

import ma.youcode.smartbank.dao.interfaces.HistoryDao;
import ma.youcode.smartbank.entities.History;
import ma.youcode.smartbank.services.interfaces.HistoryService;

public class HistoryServiceImpl implements HistoryService {
    private final HistoryDao historyDao;
    public HistoryServiceImpl(HistoryDao historyDao){
        this.historyDao = historyDao;
    }

    @Override
    public void save(History history) {
        this.historyDao.save(history);
    }
}
