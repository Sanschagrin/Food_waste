/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FWRP_Servlet;

import DataAccess.NewsletterDAO;
import DataAccess.NewsletterDAOImpl;
import DataAccess.NewsletterDTO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addNewsletterServlet")
public class AddNewsletterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private NewsletterDAO newsletterDAO;

    /**
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        try {
            newsletterDAO = new NewsletterDAOImpl();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Database connection error", e);
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("addNewsletter.jsp").forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("newsletter_name");
            String article = request.getParameter("newsletter_article");
            int itemId = Integer.parseInt(request.getParameter("item_id"));
            double sale = Double.parseDouble(request.getParameter("sale"));
            java.sql.Date uploadDate = java.sql.Date.valueOf(request.getParameter("uploadDate"));

            NewsletterDTO newsletter = new NewsletterDTO(0, name, article, itemId, sale, uploadDate);
            newsletter.setUploadDate(uploadDate);
            newsletterDAO.addNewsletter(newsletter);

            response.sendRedirect("listNewsletters");
        } catch (SQLException e) {
            throw new ServletException("Error adding newsletter", e);
        }
    }
}
