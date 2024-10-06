package ma.youcode.smartbank.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.smartbank.dao.implementations.RequestDaoImpl;
import ma.youcode.smartbank.dao.implementations.HistoryDaoImpl;
import ma.youcode.smartbank.dao.implementations.StatusDaoImpl;
import ma.youcode.smartbank.entities.Request;
import ma.youcode.smartbank.entities.History;
import ma.youcode.smartbank.entities.Status;
import ma.youcode.smartbank.responses.Response;
import ma.youcode.smartbank.services.implementations.RequestServiceImpl;
import ma.youcode.smartbank.services.implementations.HistoryServiceImpl;
import ma.youcode.smartbank.services.implementations.StatusServiceImpl;
import ma.youcode.smartbank.services.interfaces.RequestService;
import ma.youcode.smartbank.services.interfaces.HistoryService;
import ma.youcode.smartbank.services.interfaces.StatusService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@WebServlet
@MultipartConfig
public class RequestServlet extends HttpServlet {
    private RequestService requestService;
    private StatusService statusService;
    private HistoryService historyService;
    public void init() {
        requestService = new RequestServiceImpl(new RequestDaoImpl());
        statusService = new StatusServiceImpl(new StatusDaoImpl());
        historyService = new HistoryServiceImpl(new HistoryDaoImpl());
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
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

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
            Optional<Status> optionalStatus = statusService.getStatusByName("EN ATTENTE");
            if (optionalStatus.isPresent()) {
                requestService.save(request);
                History history = new History(request , optionalStatus.get());
                historyService.save(history);
            }
            Response response = new Response("Votre demande a été ajoutée avec succès." , 200);
            res.getWriter().write(response.responseJson());
        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            Response response = new Response("Votre demande n'a pas été ajoutée avec succès." , 401);
            res.getWriter().write(response.responseJson());
            res.getWriter().write("Error saving requests: " + e.getMessage());
        }

    }

    private void create(HttpServletRequest req , HttpServletResponse res) throws IOException,ServletException {
        req.getRequestDispatcher("/views/create-request.jsp").include(req , res);
    }
    private void list(HttpServletRequest req , HttpServletResponse res) throws IOException,ServletException {
         List<Optional<Request>> optionalsRequests = requestService.getAllRequestsAndStatuses();
         List<Request> requests = optionalsRequests.stream().filter(Optional::isPresent).map(Optional::get).toList();
        req.setAttribute("requests" , requests);
        req.getRequestDispatcher("/views/request-list.jsp").include(req , res);
    }


    @Override
    public void destroy() {}
}
