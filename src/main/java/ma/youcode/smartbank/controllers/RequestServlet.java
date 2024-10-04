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
import ma.youcode.smartbank.entities.Request;
import ma.youcode.smartbank.services.implementations.RequestServiceImpl;
import ma.youcode.smartbank.services.interfaces.RequestService;
import ma.youcode.smartbank.singleton.HibernateTools;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet
@MultipartConfig
public class RequestServlet extends HttpServlet {
    private RequestService requestService;

    public void init() {
        requestService = new RequestServiceImpl(new RequestDaoImpl());
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

//        String email = req.getParameter("email");
//        String project = req.getParameter("project");
//        String job = req.getParameter("job");
//        String amount = req.getParameter("amount");
//        String duration = req.getParameter("duration");
//        String monthly = req.getParameter("monthly");
//        String fees = req.getParameter("fees");
//        String phone = req.getParameter("phone");
//        String civility = req.getParameter("civility");
//        String lastName = req.getParameter("last_name");
//        String firstName = req.getParameter("first_name");
//        String cin = req.getParameter("cin");
//        String birthDate = req.getParameter("birth_date");
//        String hiringDate = req.getParameter("hiring_date");
//        String income = req.getParameter("income");
//        String hasCredit = req.getParameter("has_credit");
//
//        Request request = new Request();
//        request.setAmount(Double.parseDouble(amount));
//        request.setCin(cin);
//        request.setEmail(email);
//        request.setFirstname(firstName);
//        request.setLastname(lastName);
//        request.setMonthly(Double.parseDouble(monthly));
//        request.setIncome(Double.parseDouble(income));
//        request.setStatus(Request.RequestStatus.EN_ATTENTE);
//        request.setDuration(Integer.parseInt(duration));
//        request.setJob(job);
//        request.setPhone(phone);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        request.setBirthday(LocalDate.parse(birthDate , formatter));
//        request.setDateOfHire(LocalDate.parse(hiringDate , formatter));
//        request.setProject(project);
//
//        try {
//            requestService.save(request);
//            res.getWriter().write("Data Added succefully ");
//        } catch (Exception e) {
//            res.getWriter().write("Error saving request: " + e.getMessage());
//        }



        EntityManager em = HibernateTools.getInstance().getEntityManager();
        try {
            em.getTransaction().begin();

            Request request = new Request();
            request.setProject("Project Name");
            request.setJob("Job Title");
            request.setAmount(1000.0);
            request.setDuration(12);
            request.setMonthly(83.33);
            request.setEmail("example@example.com");
            request.setPhone("0123456789");
            request.setLastname("Doe");
            request.setFirstname("John");
            request.setCin("123456");
            request.setBirthday(LocalDate.of(1990, 1, 1));
            request.setDateOfHire(LocalDate.of(2015, 1, 1));
            request.setIncome(5000.0);
            request.setStatus(Request.RequestStatus.EN_ATTENTE);

            em.persist(request);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace(); // Log the exception for debugging
        } finally {
            em.close();
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
