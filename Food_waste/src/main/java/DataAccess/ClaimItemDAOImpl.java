/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClaimItemDAOImpl implements ClaimItemDAO {
    private Connection connection;

    public ClaimItemDAOImpl() throws SQLException, ClassNotFoundException {
        connection = DBConnection.getConnection();
    }

    @Override
    public void addClaimItem(ClaimItemDTO claimItem) {
        String sql = "INSERT INTO claim_items (claim_id, item_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, claimItem.getClaimId());
            ps.setInt(2, claimItem.getItemId());
            ps.setInt(3, claimItem.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ClaimItemDTO> getClaimItemsByClaimId(int claim_id) {
        List<ClaimItemDTO> claimItems = new ArrayList<>();
        String sql = "SELECT * FROM claim_items WHERE claim_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, claim_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClaimItemDTO claimItem = new ClaimItemDTO(
                    rs.getInt("claim_id"),
                    rs.getInt("item_id"),
                    rs.getInt("quantity")
                );
                claimItems.add(claimItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return claimItems;
    }

    @Override
    public List<ClaimItemDTO> getAllClaims() {
        List<ClaimItemDTO> allClaims = new ArrayList<>();
        String sql = "SELECT * FROM claim_items";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClaimItemDTO claimItem = new ClaimItemDTO(
                    rs.getInt("claim_id"),
                    rs.getInt("item_id"),
                    rs.getInt("quantity")
                );
                allClaims.add(claimItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allClaims;
    }

    @Override
    public void updateClaimItemQuantity(int claim_id, int item_id, int quantity) {
        String sql = "UPDATE claim_items SET quantity = ? WHERE claim_id = ? AND item_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, claim_id);
            ps.setInt(3, item_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<ClaimItemDTO> getClaimItemsByClaims(List<ClaimDTO> claims) throws SQLException {
        List<ClaimItemDTO> claimItems = new ArrayList<>();
        String sql = "SELECT * FROM claim_items WHERE claim_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (ClaimDTO claim : claims) {
                statement.setInt(1, claim.getClaimId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        ClaimItemDTO claimItem = new ClaimItemDTO(
                            resultSet.getInt("claim_id"),
                            resultSet.getInt("item_id"),
                            resultSet.getInt("quantity")
                        );
                        claimItems.add(claimItem);
                    }
                }
            }
        }
        return claimItems;
    }
    
      @Override
    public List<ClaimItemDTO> getClaimItemsByCharitableId(int charitableId) throws SQLException, ClassNotFoundException {
        List<ClaimItemDTO> claimItems = new ArrayList<>();
        String query = "SELECT ci.item_id, ci.quantity " +
                       "FROM claim_items ci " +
                       "JOIN claims c ON ci.claim_id = c.claim_id " +
                       "WHERE c.charitable_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
             
            pstmt.setInt(1, charitableId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int itemId = rs.getInt("item_id");
                    int quantity = rs.getInt("quantity");
                    
                    ClaimItemDTO claimItem = new ClaimItemDTO();
                    claimItem.setItemId(itemId);
                    claimItem.setQuantity(quantity);
                    
                    claimItems.add(claimItem);
                }
            }
        }
        return claimItems;
    }

}
