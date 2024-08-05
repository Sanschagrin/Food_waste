/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ggreg
 */
public class InventoryDAOImpl implements InventoryDAO{
    private static Connection connection;
    
    public InventoryDAOImpl() throws SQLException, ClassNotFoundException {
    this.connection = DBConnection.getConnection();
    }

    private static final String all = "SELECT * FROM Inventory";
    private static final String byID = "SELECT * FROM Inventory WHERE item_id = ?";
    private static final String insert = "INSERT INTO Inventory (retailer_id, item_name, item_description, price, expiry_date) VALUES (?, ?, ?, ?, ?)";
    private static final String update = "UPDATE Inventory SET retailer_id = ?, item_name = ?, item_description = ?, price = ?, expiry_date = ? WHERE item_id = ?";
    private static final String delete = "DELETE FROM Inventory WHERE item_id = ?";

    @Override
    public List<InventoryDTO> getAllItems() throws SQLException {
        List<InventoryDTO> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(all)) {
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                items.add(new InventoryDTO(results.getInt("item_id"), results.getInt("retailer_id"), results.getString("item_name"), results.getString("item_description"), results.getDouble("price"), results.getDate("expiry_date")));
            }
        }
        return items;
    }

    @Override
    public InventoryDTO getItemById(int item_id) throws SQLException {
        InventoryDTO item = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(byID)) {
            preparedStatement.setInt(1, item_id);
            try(ResultSet results = preparedStatement.executeQuery()){
            if (results.next()) {
                return new InventoryDTO(results.getInt("item_id"), results.getInt("retailer_id"), results.getString("item_name"), results.getString("item_description"), results.getDouble("price"), results.getDate("expiry_date"));
            }
            }
        }
        return item;
    }

    @Override
    public void addItem(InventoryDTO item) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, item.getItemName());
            preparedStatement.setString(2, item.getItemDescription());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.setDate(4, item.getExpiryDate());
            preparedStatement.setInt(5, item.getRetailerid());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Added item: " + item);
            } else {
                System.out.println("Failed to add item: " + item);
            }
    }
    }

    @Override
    public void updateItem(InventoryDTO item) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setString(1, item.getItemName());
            preparedStatement.setString(2, item.getItemDescription());
            preparedStatement.setDouble(4, item.getPrice());
            preparedStatement.setDate(5, item.getExpiryDate());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteItem(InventoryDTO item) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setInt(1, item.getItemId());
            preparedStatement.executeUpdate();
        }
    }

}


