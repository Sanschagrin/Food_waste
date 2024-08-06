/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FWRP_Servlet;

import DataAccess.RetailerDAO;
import DataAccess.RetailerDAOImpl;
import DataAccess.RetailerDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mylen
 */
@WebServlet("/RetailerServlet")
public class RetailerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {    
            RetailerDAO retailerDAO = new RetailerDAOImpl();
            List<RetailerDTO> retailers = retailerDAO.getAllRetailers();
            request.setAttribute("retailers", retailers);
            request.getRequestDispatcher("retailerList.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error retrieving retailers", e);
        }
    }
}