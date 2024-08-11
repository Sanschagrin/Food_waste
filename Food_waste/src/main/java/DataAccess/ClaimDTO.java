/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

/**
 *
 * @author mylen
 */
public class ClaimDTO {
    private int claim_id;
    private int charitable_id;

    public ClaimDTO(int claim_id, int charitable_id) {
        this.claim_id = claim_id;
        this.charitable_id = charitable_id;
    }

    public int getClaimId() {
        return claim_id;
    }

    public void setClaimId(int claim_id) {
        this.claim_id = claim_id;
    }

    public int getCharitableId() {
        return charitable_id;
    }

    public void setCharitableId(int charitable_id) {
        this.charitable_id = charitable_id;
    }
}
