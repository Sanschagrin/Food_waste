/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

package FWRP_Servlet;

import DataAccess.ConsumerDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ConsumerProfileServlet")
public class ConsumerProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConsumerDTO consumer = (ConsumerDTO) request.getSession().getAttribute("loggedInUser");
        if (consumer != null) {
            request.setAttribute("consumer", consumer);
            request.getRequestDispatcher("consumerProfile.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
 */
