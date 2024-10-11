package ma.youcode.smartbank.daos.implementations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import ma.youcode.smartbank.daos.interfaces.HistoryDao;
import ma.youcode.smartbank.entities.History;

import java.util.UUID;

@RequestScoped

public class HistoryDaoImpl extends GenericDaoImpl<History, UUID> implements HistoryDao {

    public HistoryDaoImpl() {
        super(History.class);
    }


}
