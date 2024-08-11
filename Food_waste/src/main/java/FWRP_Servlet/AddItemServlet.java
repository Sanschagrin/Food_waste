package FWRP_Servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DataAccess.*;
import javax.servlet.http.HttpSession;

@WebServlet("/AddItemServlet")
public class AddItemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int retailerId = parseIntegerParameter(request.getParameter("retailer_id"));
            String itemName = request.getParameter("item_name");
            String itemDescription = request.getParameter("item_description");
            double price = parseDoubleParameter(request.getParameter("price"));
            Date expiryDate = Date.valueOf(request.getParameter("expiry_date"));
            int quantity = parseIntegerParameter(request.getParameter("quantity"));
        
        try {
            InventoryDTO item = new InventoryDTO(0, retailerId, itemName, itemDescription, price, expiryDate, quantity);
            
            // Save item using DAO

            InventoryDAO inventoryDAO = new InventoryDAOImpl();
            inventoryDAO.addItem(item);
            
            // Redirect to retailer home page
            response.sendRedirect("RetailerHome.jsp");
        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace(); 
            System.out.println("Failed to add item: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input: " + e.getMessage());
        }
    }

    private int parseIntegerParameter(String parameter) {
        if (parameter == null || parameter.isEmpty()) {
            throw new NumberFormatException("Invalid integer parameter: null or empty");
        }
        return Integer.parseInt(parameter);
    }

    private double parseDoubleParameter(String parameter) {
        if (parameter == null || parameter.isEmpty()) {
            throw new NumberFormatException("Invalid double parameter: null or empty");
        }
        return Double.parseDouble(parameter);
    }
}

