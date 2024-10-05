package ma.youcode.smartbank.dao.implementations;

import ma.youcode.smartbank.dao.interfaces.RequestStatusHistoryDao;
import ma.youcode.smartbank.entities.RequestStatusHistory;

import java.util.UUID;

public class RequestStatusHistoryHistoryDaoImp extends GenericDaoImpl<RequestStatusHistory, UUID> implements RequestStatusHistoryDao {

    public RequestStatusHistoryHistoryDaoImp() {
        super(RequestStatusHistory.class);
    }


}
