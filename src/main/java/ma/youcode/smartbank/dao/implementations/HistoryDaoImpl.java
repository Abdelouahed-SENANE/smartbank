package ma.youcode.smartbank.dao.implementations;

import ma.youcode.smartbank.dao.interfaces.HistoryDao;
import ma.youcode.smartbank.entities.History;

import java.util.UUID;

public class HistoryDaoImpl extends GenericDaoImpl<History, UUID> implements HistoryDao {

    public HistoryDaoImpl() {
        super(History.class);
    }


}
