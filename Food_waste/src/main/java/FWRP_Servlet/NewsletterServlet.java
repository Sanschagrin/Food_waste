/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FWRP_Servlet;

/**
 *
 * @author ggreg
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import DataAccess.NewsletterDAO;
import DataAccess.NewsletterDAOImpl;
import DataAccess.NewsletterDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/listNewsletters")
public class NewsletterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NewsletterDAO newsletterDAO;

    @Override
    public void init() throws ServletException {
        try {
            newsletterDAO = new NewsletterDAOImpl();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Database connection error", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<NewsletterDTO> newsletters = newsletterDAO.getAllNewsletters();
            request.setAttribute("newsletters", newsletters);
            request.getRequestDispatcher("listNewsletters.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving newsletters", e);
        }
    }
}