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
 * @author mylen
 */

public class ClaimDAOImpl implements ClaimDAO {
    private Connection connection;

    public ClaimDAOImpl() throws SQLException, ClassNotFoundException {
        connection = DBConnection.getConnection();
    }

    @Override
    public int addClaim(ClaimDTO claim) throws SQLException {
        String sql = "INSERT INTO claims (charitable_id) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, claim.getCharitableId());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating claim failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating claim failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public List<ClaimDTO> getAllClaims() throws SQLException {
        String sql = "SELECT claim_id, charitable_id FROM claims";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            List<ClaimDTO> claims = new ArrayList<>();
            while (resultSet.next()) {
                int claim_id = resultSet.getInt("claim_id");
                int charitable_id = resultSet.getInt("charitable_id");
                ClaimDTO claim = new ClaimDTO(claim_id, charitable_id);
                claims.add(claim);
            }
            return claims;
        }
    }

    @Override
    public ClaimDTO getClaimById(int claim_id) throws SQLException {
        String sql = "SELECT claim_id, charitable_id FROM claims WHERE claim_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, claim_id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int charitable_id = resultSet.getInt("charitable_id");
                    return new ClaimDTO(claim_id, charitable_id);
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public void updateClaim(ClaimDTO claim) throws SQLException {
        String sql = "UPDATE claims SET charitable_id = ? WHERE claim_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, claim.getCharitableId());
            statement.setInt(2, claim.getClaimId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteClaim(int claimId) throws SQLException {
        String sql = "DELETE FROM claims WHERE claim_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, claimId);
            statement.executeUpdate();
        }
    }
    
    /**
     *
     * @param charitableId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
       @Override
    public List<ClaimItemDTO> getClaimItemsByCharitableId(int charitableId) throws SQLException, ClassNotFoundException {
        List<ClaimItemDTO> claimedItems = new ArrayList<>();
        String query = "SELECT ci.claim_id, ci.item_id, ci.quantity " +
                       "FROM claim_items ci " +
                       "JOIN claims c ON ci.claim_id = c.claim_id " +
                       "WHERE c.charitable_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, charitableId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int claimId = resultSet.getInt("claim_id");
                    int itemId = resultSet.getInt("item_id");
                    int quantity = resultSet.getInt("quantity");
                    claimedItems.add(new ClaimItemDTO(claimId, itemId, quantity));
                }
            }
        }
        return claimedItems;
    }


}

