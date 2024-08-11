/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 
package FWRP_Servlet;

import DataAccess.ConsumerDAO;
import DataAccess.ConsumerDAOImpl;
import DataAccess.ConsumerDTO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ConsumerServlet")
public class ConsumerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {    
            ConsumerDAO consumerDAO = new ConsumerDAOImpl();
            List<ConsumerDTO> consumers = consumerDAO.getAllConsumers();
            request.setAttribute("consumers", consumers);
            request.getRequestDispatcher("consumerList.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error retrieving consumers", e);
        }
    }
}
*/