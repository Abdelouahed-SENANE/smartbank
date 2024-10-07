package ma.youcode.smartbank.services.implementations;

import ma.youcode.smartbank.daos.interfaces.StatusDao;
import ma.youcode.smartbank.entities.Status;
import ma.youcode.smartbank.services.interfaces.StatusService;

import java.util.Optional;

public class StatusServiceImpl implements StatusService {
    private final StatusDao statusDao;
    public StatusServiceImpl(StatusDao statusDao) {
        this.statusDao= statusDao;
    }

    @Override
    public Optional<Status> getStatusByName(String name) {
        return statusDao.findByName(name);
    }
}
