/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 
package FWRP_Servlet;

import DataAccess.CharitableDAO;
import DataAccess.CharitableDAOImpl;
import DataAccess.CharitableDTO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/CharitableServlet")
public class CharitableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {    
            CharitableDAO charitableDAO = new CharitableDAOImpl();
            List<CharitableDTO> charitables = charitableDAO.getAllCharitable();
            request.setAttribute("charitables", charitables);
            request.getRequestDispatcher("charitableList.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error retrieving charitable organizations", e);
        }
    }
}
*/