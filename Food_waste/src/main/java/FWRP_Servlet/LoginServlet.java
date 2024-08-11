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

        try {
            // Fetch retailer information
            String retailerIdParam = request.getParameter("retailer_id");
            if (retailerIdParam == null || retailerIdParam.isEmpty()) {
                throw new NumberFormatException("Retailer ID is missing or invalid");
            }
            int retailerId = Integer.parseInt(retailerIdParam);

            RetailerDAO retailerDAO = new RetailerDAOImpl();
            List<RetailerDTO> retailers = retailerDAO.getAllRetailers();
            request.setAttribute("retailers", retailers);

            InventoryDAO inventoryDAO = new InventoryDAOImpl();
            List<InventoryDTO> item = inventoryDAO.getItemsByRetailerId(retailerId); // Fetch items for the specific retailer
            request.setAttribute("item", item);

            request.getRequestDispatcher("RetailerHome.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            throw new ServletException("Error retrieving data", e);
        }
    }
}
