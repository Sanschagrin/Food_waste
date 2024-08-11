package FWRP_Servlet;

import DataAccess.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RetailerServlet")
public class RetailerServlet extends HttpServlet {

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
            List<InventoryDTO> items = inventoryDAO.getItemsByRetailerId(retailerId); // Fetch items for the specific retailer
            request.setAttribute("items", items);

            request.getRequestDispatcher("RetailerHome.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error retrieving data: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
