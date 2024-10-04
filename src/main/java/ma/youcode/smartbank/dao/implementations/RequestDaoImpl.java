package ma.youcode.smartbank.dao.implementations;

import ma.youcode.smartbank.dao.interfaces.RequestDao;
import ma.youcode.smartbank.entities.Request;

import java.util.UUID;

public class RequestDaoImpl extends GenericDaoImpl<Request , UUID> implements RequestDao {

    public RequestDaoImpl(){
        super(Request.class);
    }


}
