package ma.youcode.smartbank.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet
@MultipartConfig
public class RequestServlet extends HttpServlet {


    public void init() {
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
        String hasCredit = req.getParameter("has_credit");


        
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
