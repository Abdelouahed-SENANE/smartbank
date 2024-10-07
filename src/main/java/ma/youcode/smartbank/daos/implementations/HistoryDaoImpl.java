package ma.youcode.smartbank.daos.implementations;

import ma.youcode.smartbank.daos.interfaces.HistoryDao;
import ma.youcode.smartbank.entities.History;

import java.util.UUID;

public class HistoryDaoImpl extends GenericDaoImpl<History, UUID> implements HistoryDao {

    public HistoryDaoImpl() {
        super(History.class);
    }


}
