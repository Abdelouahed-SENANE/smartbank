package ma.youcode.smartbank.services.implementations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import ma.youcode.smartbank.daos.interfaces.StatusDao;
import ma.youcode.smartbank.entities.Status;
import ma.youcode.smartbank.services.interfaces.StatusService;

import java.util.Optional;

@RequestScoped
public class StatusServiceImpl implements StatusService {
    @Inject
    private  StatusDao statusDao;
    public StatusServiceImpl() {

    }

    @Override
    public Optional<Status> getStatusByName(String name) {
        return statusDao.findByName(name);
    }
}
