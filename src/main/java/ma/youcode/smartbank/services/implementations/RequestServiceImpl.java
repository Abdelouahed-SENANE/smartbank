package ma.youcode.smartbank.services.implementations;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import ma.youcode.smartbank.daos.interfaces.RequestDao;
import ma.youcode.smartbank.dtos.RequestFilterDTO;
import ma.youcode.smartbank.entities.History;
import ma.youcode.smartbank.entities.Request;
import ma.youcode.smartbank.entities.Status;
import ma.youcode.smartbank.services.interfaces.HistoryService;
import ma.youcode.smartbank.services.interfaces.RequestService;
import ma.youcode.smartbank.services.interfaces.StatusService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RequestServiceImpl implements RequestService {
    @Inject  private  RequestDao requestDaoImpl;
    @Inject
    private StatusService statusService;
    @Inject
    private HistoryService historyService;
    public RequestServiceImpl(){}

    @Override
    public void save(Request request) {

        double monthly = calculateMonthly(request);
        if (monthly != request.getMonthly()){
            throw new IllegalArgumentException("Monthly payment does not match the expected value.");
        }
        Optional<Status> optionalStatus = statusService.getStatusByName("EN ATTENTE");
        if (optionalStatus.isPresent()) {
            History history = new History(request, optionalStatus.get());
            historyService.save(history);
        }
        requestDaoImpl.save(request);
    }

    @Override
    public List<Optional<Request>> getAllRequestsAndStatuses() {
        return requestDaoImpl.findAllRequestsWithStatuses();
    }

    @Override
    public List<Optional<Request>> getFilteredRequests(RequestFilterDTO filterDTO) {
        return requestDaoImpl.findFilteredRequests(filterDTO);
    }

    @Override
    public void delete(Request request) {
        requestDaoImpl.delete(request);
    }

    @Override
    public void update(Request request) {
        requestDaoImpl.update(request);
    }

    @Override
    public  double calculateMonthly(Request request) {
        double i = (0.10 / 12);
        double a = request.getAmount() * i;
        double b = 1 - Math.pow(1 + i , -request.getDuration());
        double monthly = a / b;
        BigDecimal bd = new BigDecimal(monthly);
        return bd.setScale(2 , RoundingMode.HALF_UP).doubleValue();
    }
}
