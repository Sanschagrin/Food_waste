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

@WebServlet("/DisplayClaimedItemsServlet")
public class DisplayClaimedItemsServlet extends HttpServlet {

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

        if (user instanceof CharitableDTO) {
            CharitableDTO charitable = (CharitableDTO) user;
            try {
                ClaimDAO claimDAO = new ClaimDAOImpl();
                InventoryDAO inventoryDAO = new InventoryDAOImpl();
                List<ClaimItemDTO> claimedItems = claimDAO.getClaimItemsByCharitableId(charitable.getCharitableId());

                request.setAttribute("claimedItems", claimedItems);
                for (ClaimItemDTO claimItem : claimedItems) {
                    InventoryDTO item = inventoryDAO.getItemById(claimItem.getItemId());
                    request.setAttribute("item_" + claimItem.getItemId(), item);
                }

                request.getRequestDispatcher("OrgHome.jsp").forward(request, response);
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
