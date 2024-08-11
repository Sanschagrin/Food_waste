package DataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAOImpl implements PurchaseDAO {

    private Connection connection;

    private static final String INSERT_PURCHASE_SQL = "INSERT INTO purchases (consumer_id, purchase_date) VALUES (?, ?)";
    private static final String SELECT_PURCHASES_BY_CONSUMER_ID_SQL = "SELECT * FROM purchases WHERE consumer_id = ?";
    private static final String SELECT_PURCHASE_ITEMS_BY_CONSUMER_ID_SQL = "SELECT * FROM purchase_items WHERE purchase_id IN (SELECT purchase_id FROM purchases WHERE consumer_id = ?)";
    private static final String SELECT_PURCHASE_BY_ID_SQL = "SELECT * FROM purchases WHERE purchase_id = ?";

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public PurchaseDAOImpl() throws SQLException, ClassNotFoundException {
        connection = DBConnection.getConnection();
    }

    /**
     *
     * @param purchase
     * @return
     * @throws SQLException
     */
    @Override
    public int addPurchase(PurchaseDTO purchase) throws SQLException {
        String sql = "INSERT INTO purchases (consumer_id) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, purchase.getConsumerId());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating purchase failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating purchase failed, no ID obtained.");
                }
            }
        }
    }

    /**
     *
     * @param purchase
     * @throws SQLException
     */
    @Override
    public void createPurchase(PurchaseDTO purchase) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PURCHASE_SQL)) {
            preparedStatement.setInt(1, purchase.getConsumerId());
            preparedStatement.executeUpdate();
        }
    }

    /**
     *
     * @param consumerId
     * @return List<PurchaseDTO>
     * @throws SQLException
     */
    @Override
    public List<PurchaseDTO> getPurchasesByConsumerId(int consumerId) throws SQLException {
        List<PurchaseDTO> purchases = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PURCHASES_BY_CONSUMER_ID_SQL)) {
            preparedStatement.setInt(1, consumerId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int purchaseId = rs.getInt("purchase_id");
                purchases.add(new PurchaseDTO(purchaseId, consumerId));
            }
        }
        return purchases;
    }

    /**
     *
     * @param consumerId
     * @return List<PurchaseDTO>
     * @throws SQLException
     */
    @Override
    public List<PurchaseItemDTO> getPurchaseItemsByConsumerId(int consumerId) throws SQLException {
        List<PurchaseItemDTO> purchaseItems = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PURCHASE_ITEMS_BY_CONSUMER_ID_SQL)) {
            preparedStatement.setInt(1, consumerId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int purchaseId = rs.getInt("purchase_id");
                int itemId = rs.getInt("item_id");
                int quantity = rs.getInt("quantity");
                purchaseItems.add(new PurchaseItemDTO(purchaseId, itemId, quantity));
            }
        }
        return purchaseItems;
    }

    /**
     *
     * @param purchaseId
     * @return PurchaseDTO
     * @throws SQLException
     */
    @Override
    public PurchaseDTO getPurchaseById(int purchaseId) throws SQLException {
        PurchaseDTO purchase = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PURCHASE_BY_ID_SQL)) {
            preparedStatement.setInt(1, purchaseId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int consumerId = rs.getInt("consumer_id");
                purchase = new PurchaseDTO(purchaseId, consumerId);
            }
        }
        return purchase;
    }
}
