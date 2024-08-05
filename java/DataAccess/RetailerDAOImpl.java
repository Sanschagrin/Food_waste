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
public class RetailerDAOImpl implements RetailerDAO{
    private static Connection connection;
    
    public RetailerDAOImpl() throws SQLException, ClassNotFoundException {
    this.connection = DBConnection.getConnection();
    }

    private static final String all = "SELECT * FROM Retailer";
    private static final String byID = "SELECT * FROM Retailer WHERE retailer_id = ?";
    private static final String insert = "INSERT INTO Retailer (retailer_id, retailer_name, retailer_email, retailer_password, retailer_description) VALUES (?, ?, ?, ?, ?)";
    private static final String update = "UPDATE Retailer SET retailer_id = ?, retailer_name = ?, retailer_email = ?, retailer_password = ?, retailer_description = ? WHERE retailer_id = ?";
    private static final String delete = "DELETE FROM Retailer WHERE retailer_id = ?";
    private static final String authenticate = "SELECT * FROM Retailer WHERE retailer_email = ? AND retailer_password = ?";

    @Override
    public List<RetailerDTO> getAllRetailers() throws SQLException {
        List<RetailerDTO> consumers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(all)) {
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                consumers.add(new RetailerDTO(results.getInt("retailer_id"), results.getString("retailer_name"), results.getString("retailer_email"), results.getString("retailer_password"), results.getString("retailer_description")));
            }
        }
        return consumers;
    }

    @Override
    public RetailerDTO getRetailerById(int retailer_id) throws SQLException {
        RetailerDTO retailer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(byID)) {
            preparedStatement.setInt(1, retailer_id);
            try(ResultSet results = preparedStatement.executeQuery()){
            if (results.next()) {
                return new RetailerDTO(results.getInt("retailer_id"), results.getString("retailer_name"), results.getString("retailer_email"), results.getString("retailer_password"), results.getString("retailer_description"));
            }
            }
        }
        return retailer;
    }

    @Override
    public void addRetailer(RetailerDTO retailer) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, retailer.getRetailerName());
            preparedStatement.setString(2, retailer.getRetailerEmail());
            preparedStatement.setString(3, retailer.getRetailerPassword());
            preparedStatement.setString(4, retailer.getRetailerDescription());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Added retailer: " + retailer);
            } else {
                System.out.println("Failed to add retailer: " + retailer);
            }
    }
    }

    @Override
    public void updateRetailer(RetailerDTO retailer) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setString(1, retailer.getRetailerName());
            preparedStatement.setString(2, retailer.getRetailerEmail());
            preparedStatement.setString(3, retailer.getRetailerPassword());
            preparedStatement.setString(4, retailer.getRetailerDescription());
            preparedStatement.setInt(5, retailer.getRetailerId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteRetailer(RetailerDTO retailer) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setInt(1, retailer.getRetailerId());
            preparedStatement.executeUpdate();
        }
    }
        @Override
    public RetailerDTO getRetailerByUsernameAndPassword(String retailer_name, String retailer_password) throws SQLException {
        RetailerDTO retailer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(authenticate)) {
            preparedStatement.setString(1, retailer_name);
            preparedStatement.setString(2, retailer_password);
            ResultSet results = preparedStatement.executeQuery();
            if (results.next()) {
                retailer = new RetailerDTO(
                    results.getInt("retailer_id"),
                    results.getString("retailer_name"),
                    results.getString("retailer_email"),
                    results.getString("retailer_password"),
                    results.getString("retailer_description")
                );
            }
        }
        return retailer;
    }
}

