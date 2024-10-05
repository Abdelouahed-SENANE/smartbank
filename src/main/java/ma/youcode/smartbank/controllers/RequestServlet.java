package ma.youcode.smartbank.controllers;

import jakarta.persistence.EntityManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.smartbank.dao.implementations.RequestDaoImpl;
import ma.youcode.smartbank.dao.implementations.RequestStatusHistoryHistoryDaoImp;
import ma.youcode.smartbank.dao.implementations.StatusDaoImpl;
import ma.youcode.smartbank.entities.Request;
import ma.youcode.smartbank.entities.RequestStatusHistory;
import ma.youcode.smartbank.entities.Status;
import ma.youcode.smartbank.enums.RequestStatus;
import ma.youcode.smartbank.services.implementations.RequestServiceImpl;
import ma.youcode.smartbank.services.implementations.RequestStatusHistoryServiceImpl;
import ma.youcode.smartbank.services.implementations.StatusServiceImpl;
import ma.youcode.smartbank.services.interfaces.RequestService;
import ma.youcode.smartbank.services.interfaces.RequestStatusHistoryService;
import ma.youcode.smartbank.services.interfaces.StatusService;
import ma.youcode.smartbank.singleton.HibernateTools;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@WebServlet
@MultipartConfig
public class RequestServlet extends HttpServlet {
    private RequestService requestService;
    private StatusService statusService;
    private RequestStatusHistoryService historyService;
    public void init() {
        requestService = new RequestServiceImpl(new RequestDaoImpl());
        statusService = new StatusServiceImpl(new StatusDaoImpl());
        historyService = new RequestStatusHistoryServiceImpl(new RequestStatusHistoryHistoryDaoImp());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException , ServletException {

        switch(req.getPathInfo()) {
            case "/creer":
                create(req , res);
                break;
            case "/liste":
                list(req, res);
                break;
            default:
                res.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found");
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException , ServletException  {

        switch(req.getPathInfo()) {
            case "/save":
                save(req , res);
                break;
            case "/delete":
                // Todo Delete  function to delete  demande here

                break;
            default:
                res.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found");
        }


    }


    private void save(HttpServletRequest req , HttpServletResponse res) throws IOException , ServletException {
        res.setContentType("text/plain");

        String email = req.getParameter("email");
        String project = req.getParameter("project");
        String job = req.getParameter("job");
        String amount = req.getParameter("amount");
        String duration = req.getParameter("duration");
        String monthly = req.getParameter("monthly");
        String fees = req.getParameter("fees");
        String phone = req.getParameter("phone");
        String civility = req.getParameter("civility");
        String lastName = req.getParameter("last_name");
        String firstName = req.getParameter("first_name");
        String cin = req.getParameter("cin");
        String birthDate = req.getParameter("birth_date");
        String hiringDate = req.getParameter("hiring_date");
        String income = req.getParameter("income");

        Request request = new Request();
        request.setAmount(Double.parseDouble(amount));
        request.setCin(cin);
        request.setEmail(email);
        request.setFirstname(firstName);
        request.setLastname(lastName);
        request.setMonthly(Double.parseDouble(monthly));
        request.setIncome(Double.parseDouble(income));
        request.setFees(Double.parseDouble(fees));
        request.setCivility(civility);
        request.setDuration(Integer.parseInt(duration));
        request.setJobName(job);
        request.setPhone(phone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        request.setBirthday(LocalDate.parse(birthDate , formatter));
        request.setDateOfHire(LocalDate.parse(hiringDate , formatter));
        request.setProjectName(project);

        try {
            requestService.save(request);
            request.setRequestId(request.getRequestId());
            Optional<Status> optionalStatus = statusService.getStatusByName("EN ATTENTE");
            if (optionalStatus.isPresent()) {
                RequestStatusHistory history = new RequestStatusHistory(request , optionalStatus.get());
                historyService.save(history);
            }

            res.getWriter().write("Data added successfully" );
        } catch (Exception e) {
            res.getWriter().write("Error saving request: " + e.getMessage());
        }



    }

    private void create(HttpServletRequest req , HttpServletResponse res) throws IOException,ServletException {
        req.getRequestDispatcher("/views/create-request.jsp").include(req , res);
    }
    private void list(HttpServletRequest req , HttpServletResponse res) throws IOException,ServletException {
        req.getRequestDispatcher("/views/request-list.jsp").include(req , res);
    }


    @Override
    public void destroy() {}
}
