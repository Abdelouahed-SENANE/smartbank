package ma.youcode.smartbank.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import ma.youcode.smartbank.daos.implementations.RequestDaoImpl;
import ma.youcode.smartbank.daos.implementations.HistoryDaoImpl;
import ma.youcode.smartbank.daos.implementations.StatusDaoImpl;
import ma.youcode.smartbank.dtos.RequestFilterDTO;
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
import java.time.format.DateTimeParseException;
import java.util.*;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        switch (req.getPathInfo()) {
            case "/creer":
                create(req, res);
                break;
            case "/liste":
                list(req, res);
                break;
            case "/filter":
                filter(req, res);
                break;
            default:
                res.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found");
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        switch (req.getPathInfo()) {
            case "/save":
                save(req, res);
                break;
            case "/delete":
                delete(req, res);
            case "/update":
                update(req, res);
                break;
            default:
                res.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found");
        }


    }


    private void save(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
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
        request.setBirthday(LocalDate.parse(birthDate, formatter));
        request.setDateOfHire(LocalDate.parse(hiringDate, formatter));
        request.setProjectName(project);

        try {
            Optional<Status> optionalStatus = statusService.getStatusByName("EN ATTENTE");
            if (optionalStatus.isPresent()) {
                requestService.save(request);
                History history = new History(request, optionalStatus.get());
                historyService.save(history);
            }
            res.getWriter().write(new Response().success("Votre demande a été ajoutée avec succès.", 200 , null));
        } catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write(Response.error("Votre demande n'a pas été ajoutée avec succès.", 401 , null));
            res.getWriter().write("Error saving requests: " + e.getMessage());
        }

    }
    private void update(HttpServletRequest req,HttpServletResponse res) throws IOException  {

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        if (req.getParameter("requestId").trim().isEmpty()) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write(Response.error("Cannot update this Entity" , 401 , null));
            return;
        }
        UUID requestId = UUID.fromString(req.getParameter("requestId"));
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

        Request updatedRequest = new Request();
        updatedRequest.setRequestId(requestId);
        updatedRequest.setAmount(Double.parseDouble(amount));
        updatedRequest.setCin(cin);
        updatedRequest.setEmail(email);
        updatedRequest.setFirstname(firstName);
        updatedRequest.setLastname(lastName);
        updatedRequest.setMonthly(Double.parseDouble(monthly));
        updatedRequest.setIncome(Double.parseDouble(income));
        updatedRequest.setFees(Double.parseDouble(fees));
        updatedRequest.setCivility(civility);
        updatedRequest.setDuration(Integer.parseInt(duration));
        updatedRequest.setJobName(job);
        updatedRequest.setPhone(phone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        updatedRequest.setBirthday(LocalDate.parse(birthDate, formatter));
        updatedRequest.setDateOfHire(LocalDate.parse(hiringDate, formatter));
        updatedRequest.setProjectName(project);

        try {
            requestService.update(updatedRequest);
            res.setStatus(HttpServletResponse.SC_OK);
            res.getWriter().write(Response.error("Cannot update this Entity" , 400 , null));
        }catch (Exception e) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.getWriter().write(Response.error("Cannot update this Entity" , 400 , null));
        }

    }
    private void delete(HttpServletRequest req , HttpServletResponse res) throws IOException   {

        if(!req.getParameter("requestId").isEmpty()) {
            Request request = new Request();
            request.setRequestId(UUID.fromString(req.getParameter("requestId")));
            requestService.delete(request);
            res.setStatus(HttpServletResponse.SC_OK);
            res.getWriter().write(Response.success("L'entité a été supprimée avec succès.", 200, null));
            return;
        }
        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        res.getWriter().write(Response.error("Cette entité ne peut pas être supprimée.", 400 , null));
    }

    private void create(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.getRequestDispatcher("/views/create-request.jsp").include(req, res);
    }

    private void list(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        List<Optional<Request>> optionalsRequests = requestService.getAllRequestsAndStatuses();
        List<Request> requests = optionalsRequests.stream().filter(Optional::isPresent).map(Optional::get).toList();
        req.setAttribute("requests", requests);
        req.getRequestDispatcher("/views/request-list.jsp").include(req, res);
    }

    private void filter(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        res.setContentType("application/json");
        String searchParam = req.getParameter("name");
        String dateParam = req.getParameter("creationDate");

        RequestFilterDTO filterDTO = new RequestFilterDTO();

        filterDTO.setName(searchParam);
        if (dateParam != null && !dateParam.trim().isEmpty()) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                LocalDate creationDate = LocalDate.parse(dateParam, df);
                filterDTO.setCreationDate(creationDate);
            } catch (DateTimeParseException e) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                res.getWriter().write(Response.error("Invalid date format. Please use 'yyyy-MM-dd", 401 , null));
                return;
            }
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<RequestFilterDTO>> violations = validator.validate(filterDTO);
        if (!violations.isEmpty()) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.getWriter().write(Response.error("An Error occurred " , 400 , violations));
            return;
        }

        List<Optional<Request>> requestsFiltered = requestService.getFilteredRequests(filterDTO);

        if(!requestsFiltered.isEmpty()) {
            res.getWriter().write(Response.success("Data has been retrieve successfully", 200 , requestsFiltered));
        }else {
            res.getWriter().write(Response.success("Data not exists", 404 , null));
        }

    }

    @Override
    public void destroy() {
    }
}
