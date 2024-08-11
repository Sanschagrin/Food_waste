package FWRP_Servlet;

import DataAccess.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@WebServlet("/ClaimOrPurchaseServlet")
public class ClaimOrPurchaseServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        String itemIdParam = request.getParameter("item_id");
        String quantityParam = request.getParameter("quantity");
        String action = request.getParameter("action");

        if (itemIdParam == null || quantityParam == null || action == null) {
            sendErrorResponse(response, "Missing parameters.");
            return;
        }

        int item_id;
        int quantity;
        try {
            item_id = Integer.parseInt(itemIdParam);
            quantity = Integer.parseInt(quantityParam);
        } catch (NumberFormatException e) {
            sendErrorResponse(response, "Invalid item ID or quantity.");
            return;
        }

        try {
            if ("claim".equals(action) && user instanceof CharitableDTO) {
                CharitableDTO charitable = (CharitableDTO) user;
                processClaim(charitable.getCharitableId(), item_id, quantity);
                response.sendRedirect("OrgHome.jsp"); // Redirect to org home page after processing

            } else if ("purchase".equals(action) && user instanceof ConsumerDTO) {
                ConsumerDTO consumer = (ConsumerDTO) user;
                processPurchase(consumer.getConsumerId(), item_id, quantity);
                response.sendRedirect("consumerHome.jsp"); // Redirect to consumer home page after processing

            } else {
                sendErrorResponse(response, "Invalid action or user type.");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorResponse(response, "An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     *
     * @param charitable_id
     * @param item_id
     * @param quantity
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void processClaim(int charitable_id, int item_id, int quantity) throws SQLException, ClassNotFoundException {
        ClaimDAO claimDAO = new ClaimDAOImpl();
        ClaimItemDAO claimItemDAO = new ClaimItemDAOImpl();
        InventoryDAO inventoryDAO = new InventoryDAOImpl();

        // Reduce the item quantity in inventory
        InventoryDTO item = inventoryDAO.getItemById(item_id);
        if (item == null) {
            throw new IllegalArgumentException("Item not found in inventory");
        }
        int newQuantity = item.getQuantity() - quantity;
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Insufficient quantity in inventory");
        }
        item.setQuantity(newQuantity);
        inventoryDAO.updateItem(item);

        // Record the claim
        ClaimDTO claim = new ClaimDTO(0, charitable_id); // Claim ID will be auto-incremented
        int claim_id = claimDAO.addClaim(claim);
        ClaimItemDTO claimItem = new ClaimItemDTO(claim_id, item_id, quantity);
        claimItemDAO.addClaimItem(claimItem);
    }

    /**
     *
     * @param consumer_id
     * @param item_id
     * @param quantity
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private void processPurchase(int consumer_id, int item_id, int quantity) throws SQLException, ClassNotFoundException {
        PurchaseDAO purchaseDAO = new PurchaseDAOImpl();
        PurchaseItemDAO purchaseItemDAO = new PurchaseItemDAOImpl();
        InventoryDAO inventoryDAO = new InventoryDAOImpl();

        // Reduce the item quantity in inventory
        InventoryDTO item = inventoryDAO.getItemById(item_id);
        if (item == null) {
            throw new IllegalArgumentException("Item not found in inventory");
        }
        int newQuantity = item.getQuantity() - quantity;
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Insufficient quantity in inventory");
        }
        item.setQuantity(newQuantity);
        inventoryDAO.updateItem(item);

        // Record the claim
        PurchaseDTO purchase = new PurchaseDTO(0, consumer_id); // Claim ID will be auto-incremented
        int purchase_id = purchaseDAO.addPurchase(purchase);
        PurchaseItemDTO purchaseItem = new PurchaseItemDTO(purchase_id, item_id, quantity);
        purchaseItemDAO.addPurchaseItem(purchaseItem);
    }

    /**
     *
     * @param response
     * @param message
     * @throws IOException
     */
    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/plain");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400 Bad Request
        PrintWriter out = response.getWriter();
        out.println(message);
        out.flush();
    }
}
