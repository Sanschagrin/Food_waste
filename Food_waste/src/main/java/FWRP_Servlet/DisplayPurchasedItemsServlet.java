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

@WebServlet("/DisplayPurchasedItemsServlet")
public class DisplayPurchasedItemsServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        if (user instanceof ConsumerDTO) {
            ConsumerDTO consumer = (ConsumerDTO) user;
            try {
                PurchaseDAO purchaseDAO = new PurchaseDAOImpl();
                InventoryDAO inventoryDAO = new InventoryDAOImpl();
                List<PurchaseItemDTO> purchaseItems = purchaseDAO.getPurchaseItemsByConsumerId(consumer.getConsumerId());

                request.setAttribute("purchasedItems", purchaseItems);
                for (PurchaseItemDTO purchaseItem : purchaseItems) {
                    InventoryDTO item = inventoryDAO.getItemById(purchaseItem.getItemId());
                    request.setAttribute("item_" + purchaseItem.getItemId(), item);
                }

                request.getRequestDispatcher("consumerHome.jsp").forward(request, response);
            } catch (SQLException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "A database error occurred.");
            } catch (ClassNotFoundException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "A class not found error occurred.");
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
