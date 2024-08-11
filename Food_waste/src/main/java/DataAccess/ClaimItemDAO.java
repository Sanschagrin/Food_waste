/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataAccess;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Mylene
 */
public interface ClaimItemDAO {
    void addClaimItem(ClaimItemDTO claimItem);
    List<ClaimItemDTO> getClaimItemsByClaimId(int claim_id); 
    List<ClaimItemDTO> getAllClaims();
    void updateClaimItemQuantity(int claim_id, int item_id, int quantity);
    List<ClaimItemDTO> getClaimItemsByClaims(List<ClaimDTO> claims) throws SQLException;
    List<ClaimItemDTO> getClaimItemsByCharitableId(int charitableId) throws SQLException, ClassNotFoundException;


    
    
}
