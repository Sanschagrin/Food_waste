/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FWRP_Servlet;

import DataAccess.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            ConsumerDAO consumerDAO = new ConsumerDAOImpl();
            RetailerDAO retailerDAO = new RetailerDAOImpl();
            CharitableDAO charitableDAO = new CharitableDAOImpl();

            // Check if the user is a consumer
            List<ConsumerDTO> consumers = consumerDAO.getAllConsumers();
            for (ConsumerDTO consumer : consumers) {
                if (consumer.getConsumerName().equals(username) && consumer.getConsumerPassword().equals(password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", consumer);
                    session.setAttribute("userType", "consumer");
                    response.sendRedirect("LoginSuccessful.jsp");
                    return;
                }
            }

            // Check if the user is a retailer
            List<RetailerDTO> retailers = retailerDAO.getAllRetailers();
            for (RetailerDTO retailer : retailers) {
                if (retailer.getRetailerName().equals(username) && retailer.getRetailerPassword().equals(password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", retailer);
                    session.setAttribute("userType", "retailer");
                    response.sendRedirect("LoginSuccessful.jsp");
                    return;
                }
            }

            // Check if the user is a charitable organization
            List<CharitableDTO> charitables = charitableDAO.getAllCharitable();
            for (CharitableDTO charitable : charitables) {
                if (charitable.getCharitableName().equals(username) && charitable.getCharitablePassword().equals(password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", charitable);
                    session.setAttribute("userType", "charitable_organization");
                    response.sendRedirect("LoginSuccessful.jsp");
                    return;
                }
            }

            // If no match is found, redirect to error page
            response.sendRedirect("loginError.jsp");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendRedirect("Failed.jsp");
        }
    }
}   