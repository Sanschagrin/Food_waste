/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author mylen
 */

public interface ClaimDAO {
    int addClaim(ClaimDTO claim) throws SQLException;
    List<ClaimDTO> getAllClaims() throws SQLException;
    ClaimDTO getClaimById(int claimId) throws SQLException;
    void updateClaim(ClaimDTO claim) throws SQLException;
    void deleteClaim(int claimId) throws SQLException;
    List<ClaimItemDTO> getClaimItemsByCharitableId(int charitableId) throws SQLException, ClassNotFoundException; // New method

}
