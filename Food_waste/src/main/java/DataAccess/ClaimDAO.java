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

    /**
     *
     * @param claim
     * @return addClaim(ClaimDTO claim)
     * @throws SQLException
     */
    int addClaim(ClaimDTO claim) throws SQLException;

    /**
     *
     * @return List<ClaimDTO>
     * @throws SQLException
     */
    List<ClaimDTO> getAllClaims() throws SQLException;

    /**
     *
     * @param claimId
     * @return claimId
     * @throws SQLException
     */
    ClaimDTO getClaimById(int claimId) throws SQLException;

    /**
     *
     * @param claim
     * @throws SQLException
     */
    void updateClaim(ClaimDTO claim) throws SQLException;

    /**
     *
     * @param claimId
     * @throws SQLException
     */
    void deleteClaim(int claimId) throws SQLException;

    /**
     *
     * @param charitableId
     * @return int charitableId
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    List<ClaimItemDTO> getClaimItemsByCharitableId(int charitableId) throws SQLException, ClassNotFoundException; // New method

}
