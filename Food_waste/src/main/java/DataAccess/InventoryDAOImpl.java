package DataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAOImpl implements InventoryDAO {

    private Connection connection;

    public InventoryDAOImpl() throws SQLException, ClassNotFoundException {
        // Initialize database connection
        connection = DBConnection.getConnection();
    }
    
     // Constructor with Connection parameter
    public InventoryDAOImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<InventoryDTO> getAllItems() throws SQLException {
        List<InventoryDTO> items = new ArrayList<>();
        String query = "SELECT * FROM inventory";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                InventoryDTO item = new InventoryDTO(
                    rs.getInt("item_id"),
                    rs.getInt("retailer_id"),
                    rs.getString("item_name"),
                    rs.getString("item_description"),
                    rs.getDouble("price"),
                    rs.getDate("expiry_date"),
                    rs.getInt("quantity")
                );
                items.add(item);
            }
        }
        return items;
    }

    @Override
    public InventoryDTO getItemById(int item_id) throws SQLException {
        String query = "SELECT * FROM inventory WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, item_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new InventoryDTO(
                        rs.getInt("item_id"),
                        rs.getInt("retailer_id"),
                        rs.getString("item_name"),
                        rs.getString("item_description"),
                        rs.getDouble("price"),
                        rs.getDate("expiry_date"),
                        rs.getInt("quantity")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public void addItem(InventoryDTO item) throws SQLException {
        String query = "INSERT INTO inventory (retailer_id, item_name, item_description, price, expiry_date, quantity) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, item.getRetailerId());
            stmt.setString(2, item.getItemName());
            stmt.setString(3, item.getItemDescription());
            stmt.setDouble(4, item.getPrice());
            stmt.setDate(5, item.getExpiryDate());
            stmt.setInt(6, item.getQuantity());
            stmt.executeUpdate();
        }
    }

    @Override
    public void updateItem(InventoryDTO item) throws SQLException {
        String query = "UPDATE inventory SET retailer_id = ?, item_name = ?, item_description = ?, price = ?, expiry_date = ?, quantity = ? WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, item.getRetailerId());
            stmt.setString(2, item.getItemName());
            stmt.setString(3, item.getItemDescription());
            stmt.setDouble(4, item.getPrice());
            stmt.setDate(5, item.getExpiryDate());
            stmt.setInt(6, item.getQuantity());
            stmt.setInt(7, item.getItemId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteItem(int item_id) throws SQLException {
        String query = "DELETE FROM inventory WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, item_id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<InventoryDTO> getItemsByRetailerId(int retailerId) throws SQLException {
        List<InventoryDTO> items = new ArrayList<>();
        String query = "SELECT * FROM inventory WHERE retailer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, retailerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    InventoryDTO item = new InventoryDTO(
                        rs.getInt("item_id"),
                        rs.getInt("retailer_id"),
                        rs.getString("item_name"),
                        rs.getString("item_description"),
                        rs.getDouble("price"),
                        rs.getDate("expiry_date"),
                        rs.getInt("quantity")
                    );
                    items.add(item);
                }
            }
        }
        return items;
    }
}
