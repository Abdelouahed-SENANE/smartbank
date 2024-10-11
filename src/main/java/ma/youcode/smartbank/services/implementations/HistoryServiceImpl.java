package ma.youcode.smartbank.services.implementations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import ma.youcode.smartbank.daos.interfaces.HistoryDao;
import ma.youcode.smartbank.entities.History;
import ma.youcode.smartbank.services.interfaces.HistoryService;
@RequestScoped
public class HistoryServiceImpl implements HistoryService {
    @Inject
    private  HistoryDao historyDao;
    public HistoryServiceImpl(){
    }

    @Override
    public void save(History history) {
        this.historyDao.save(history);
    }
}
