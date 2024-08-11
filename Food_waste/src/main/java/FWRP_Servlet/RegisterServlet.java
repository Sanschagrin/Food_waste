package FWRP_Servlet;

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

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("user_type");
        String description = request.getParameter("description");

        try {
            if ("consumer".equals(userType)) {
                ConsumerDAO consumerDAO = new ConsumerDAOImpl();
                ConsumerDTO consumer = new ConsumerDTO(0, username, email, password, false);
                consumerDAO.addConsumer(consumer);
            } else if ("retailer".equals(userType)) {
                RetailerDAO retailerDAO = new RetailerDAOImpl();
                RetailerDTO retailer = new RetailerDTO(0, username, email, password, description);
                retailerDAO.addRetailer(retailer);
            } else if ("charitable_organization".equals(userType)) {
                CharitableDAO charitableDAO = new CharitableDAOImpl();
                CharitableDTO charitable = new CharitableDTO(0, username, email, password, description);
                charitableDAO.addCharitable(charitable);
            }
            response.sendRedirect("RegistrationSuccess.jsp");
        } catch (SQLException | ClassNotFoundException e) {
            response.sendRedirect("Failed.jsp");
        }
    }
}
