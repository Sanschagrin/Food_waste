package FWRP_Servlet;

import DataAccess.ConsumerDTO;
import DataAccess.NewsletterDAO;
import DataAccess.NewsletterDAOImpl;
import DataAccess.NewsletterDTO;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author ggreg
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Object user = session.getAttribute("user");

            if (user instanceof RetailerDTO) {
                RetailerDAO retailerDAO = new RetailerDAOImpl();
                List<RetailerDTO> retailers = retailerDAO.getAllRetailers();
                request.setAttribute("retailers", retailers);
                NewsletterDAO newsletterDAO = new NewsletterDAOImpl();
                List<NewsletterDTO> newsletters = newsletterDAO.getAllNewsletters();
                request.setAttribute("newsletters", newsletters);

                request.getRequestDispatcher("Home.jsp").forward(request, response);
                // } else if (user instanceof ConsumerDTO) {
                // request.getRequestDispatcher("consumerHome.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error retrieving data", e);
        }
    }
}
