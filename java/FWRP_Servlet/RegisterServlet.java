package FWRP_Servlet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import DataAccess.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
/**
 *
 * @author ggreg
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("user_type");

        try {
            if ("consumer".equals(userType)) {
                ConsumerDAO consumerDAO = new ConsumerDAOImpl();
                ConsumerDTO consumer = new ConsumerDTO(0, username, email, password, false);
                consumerDAO.addConsumer(consumer);
            } else if ("retailer".equals(userType)) {
                RetailerDAO retailerDAO = new RetailerDAOImpl();
                RetailerDTO retailer = new RetailerDTO(0, username, email, password, "");
                retailerDAO.addRetailer(retailer);
            } else if ("charitable_organization".equals(userType)) {
                CharitableDAO charitableDAO = new CharitableDAOImpl();
                CharitableDTO charitable = new CharitableDTO(0, username, email, password, "");
                charitableDAO.addCharitable(charitable);
            }
            response.sendRedirect("FWRP_JSP/RegistrationSuccess.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            response.sendRedirect("Failed.jsp");
        }
    }
}