package DataAccess;

/**
 *
 * @author mylen
 */
public class ClaimDTO {

    private int claim_id;
    private int charitable_id;

    /**
     * constructor
     *
     * @param claim_id
     * @param charitable_id
     */

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
