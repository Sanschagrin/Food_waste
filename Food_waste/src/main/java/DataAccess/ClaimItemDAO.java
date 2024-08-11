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

    /**
     * add the claim items
     *
     * @param claimItem
     */
    void addClaimItem(ClaimItemDTO claimItem);

    /**
     * list of the claims in the database
     *
     * @param claim_id
     * @return
     */
    List<ClaimItemDTO> getClaimItemsByClaimId(int claim_id);

    /**
     *
     * @return List ClaimItemDTO
     */
    List<ClaimItemDTO> getAllClaims();

    /**
     * update the item
     *
     * @param claim_id
     * @param item_id
     * @param quantity
     */
    void updateClaimItemQuantity(int claim_id, int item_id, int quantity);

    /**
     *
     * @param claims
     * @return List ClaimDTO claims
     * @throws SQLException
     */
    List<ClaimItemDTO> getClaimItemsByClaims(List<ClaimDTO> claims) throws SQLException;

    /**
     *
     * @param charitableId
     * @return List ClaimItemDTO
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    List<ClaimItemDTO> getClaimItemsByCharitableId(int charitableId) throws SQLException, ClassNotFoundException;

}
