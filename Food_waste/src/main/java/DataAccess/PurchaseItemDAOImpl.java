package DataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseItemDAOImpl implements PurchaseItemDAO {

    private Connection connection;

    private static final String INSERT_PURCHASE_ITEM_SQL = "INSERT INTO purchase_items (purchase_id, item_id, quantity) VALUES (?, ?, ?)";
    private static final String SELECT_PURCHASE_ITEMS_BY_PURCHASE_ID_SQL = "SELECT * FROM purchase_items WHERE purchase_id = ?";
    private static final String SELECT_PURCHASE_ITEM_BY_ID_SQL = "SELECT * FROM purchase_items WHERE purchase_item_id = ?";

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public PurchaseItemDAOImpl() throws SQLException, ClassNotFoundException {
        connection = DBConnection.getConnection();
    }

    /**
     *
     * @param purchaseItem
     * @throws SQLException
     */
    @Override
    public void addPurchaseItem(PurchaseItemDTO purchaseItem) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PURCHASE_ITEM_SQL)) {
            preparedStatement.setInt(1, purchaseItem.getPurchaseId());
            preparedStatement.setInt(2, purchaseItem.getItemId());
            preparedStatement.setInt(3, purchaseItem.getQuantity());
            preparedStatement.executeUpdate();
        }
    }

    /**
     *
     * @param purchaseId
     * @return List PurchaseItemDTO 
     * @throws SQLException
     */
    @Override
    public List<PurchaseItemDTO> getPurchaseItemsByPurchaseId(int purchaseId) throws SQLException {
        List<PurchaseItemDTO> purchaseItems = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PURCHASE_ITEMS_BY_PURCHASE_ID_SQL)) {
            preparedStatement.setInt(1, purchaseId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int purchaseItemId = rs.getInt("purchase_item_id");
                int itemId = rs.getInt("item_id");
                int quantity = rs.getInt("quantity");
                purchaseItems.add(new PurchaseItemDTO(purchaseId, itemId, quantity));
            }
        }
        return purchaseItems;
    }

    /**
     *
     * @param purchaseItemId
     * @return PurchaseItemDTO
     * @throws SQLException
     */
    @Override
    public PurchaseItemDTO getPurchaseItemById(int purchaseItemId) throws SQLException {
        PurchaseItemDTO purchaseItem = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PURCHASE_ITEM_BY_ID_SQL)) {
            preparedStatement.setInt(1, purchaseItemId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int purchaseId = rs.getInt("purchase_id");
                int itemId = rs.getInt("item_id");
                int quantity = rs.getInt("quantity");
                purchaseItem = new PurchaseItemDTO(purchaseId, itemId, quantity);
            }
        }
        return purchaseItem;
    }
}
