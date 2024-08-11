/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CharitableDAOImpl implements CharitableDAO {
    private static Connection connection;
    
    public CharitableDAOImpl() throws SQLException, ClassNotFoundException {
    this.connection = DBConnection.getConnection();
    }
    
      // Constructor with Connection parameter
    public CharitableDAOImpl(Connection connection) {
        this.connection = connection;
    }

    private static final String all = "SELECT * FROM charitable";
    private static final String byID = "SELECT * FROM charitable WHERE charitable_id = ?";
    private static final String insert = "INSERT INTO charitable (charitable_name, charitable_email, charitable_password, charitable_description) VALUES (?, ?, ?, ?)";
    private static final String update = "UPDATE charitable SET charitable_name = ?, charitable_email = ?, charitable_password = ?, charitable_description = ? WHERE charitable_id = ?";
    private static final String delete = "DELETE FROM charitable WHERE charitable_id = ?";
    private static final String QUERY = "SELECT * FROM charitable WHERE username = ? AND password = ?";


    @Override
    public List<CharitableDTO> getAllCharitable() throws SQLException {
        List<CharitableDTO> charitables = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(all)) {
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                charitables.add(new CharitableDTO(results.getInt("charitable_id"), results.getString("charitable_name"), results.getString("charitable_email"), results.getString("charitable_password"), results.getString("charitable_description")));
            }
        }
        return charitables;
    }

    @Override
    public CharitableDTO getCharitableById(int charitable_id) throws SQLException {
        CharitableDTO charitables = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(byID)) {
            preparedStatement.setInt(1, charitable_id);
            try(ResultSet results = preparedStatement.executeQuery()){
            if (results.next()) {
                return new CharitableDTO(results.getInt("charitable_id"), results.getString("charitable_name"), results.getString("charitable_email"), results.getString("charitable_password"),
                results.getString("charitable_description"));
            }
            }
        }
        return charitables;
    }

    @Override
    public void addCharitable(CharitableDTO charitable) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, charitable.getCharitableName());
            preparedStatement.setString(2, charitable.getCharitableEmail());
            preparedStatement.setString(3, charitable.getCharitablePassword());
            preparedStatement.setString(4, charitable.getCharitableDescription());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Added course: " + charitable);
            } else {
                System.out.println("Failed to add course: " + charitable);
            }
    }
    }

    @Override
    public void updateCharitable(CharitableDTO charitable) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setString(1, charitable.getCharitableName());
            preparedStatement.setString(2, charitable.getCharitableEmail());
            preparedStatement.setString(3, charitable.getCharitablePassword());
            preparedStatement.setString(4, charitable.getCharitableDescription());
            preparedStatement.setInt(5, charitable.getCharitableId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteCharitable(CharitableDTO charitable) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setInt(1, charitable.getCharitableId());
            preparedStatement.executeUpdate();
        }
    }
     @Override
    public CharitableDTO getCharitableByUsernameAndPassword(String charitable_name, String charitable_password) throws SQLException {
        CharitableDTO org = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setString(1, charitable_name);
            preparedStatement.setString(2, charitable_password);
            ResultSet results = preparedStatement.executeQuery();
            if (results.next()) {
                org = new CharitableDTO(
                    results.getInt("charitable_id"),
                    results.getString("charitable_name"),
                    results.getString("charitable_email"),
                    results.getString("charitable_password"),
                    results.getString("charitable_description")
                    
                );
            }
        }
        return org;
    }

}