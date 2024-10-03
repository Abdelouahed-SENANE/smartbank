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
@WebServlet("/simulateur")
@MultipartConfig
public class RequestServlet extends HttpServlet {
    private String message;
    public void init() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException , ServletException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/simulator.jsp");
        dispatcher.include(request , response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        response.setContentType("text/plain");

        String email = request.getParameter("email");
        String project = request.getParameter("project");
        String job = request.getParameter("job");
        String amount = request.getParameter("amount");
        String duration = request.getParameter("duration");
        String monthly = request.getParameter("monthly");
        String fees = request.getParameter("fees");
        String phone = request.getParameter("phone");
        String civility = request.getParameter("civility");
        String lastName = request.getParameter("last_name");
        String firstName = request.getParameter("first_name");
        String cin = request.getParameter("cin");
        String birthDate = request.getParameter("birth_date");
        String hiringDate = request.getParameter("hiring_date");
        String income = request.getParameter("income");
        String hasCredit = request.getParameter("has_credit");



    }
    @Override
    public void destroy() {}

}
