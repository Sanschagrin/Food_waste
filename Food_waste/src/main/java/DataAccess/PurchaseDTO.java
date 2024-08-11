package DataAccess;

public class PurchaseDTO {
    private int purchaseId;
    private int consumerId;

    public PurchaseDTO(int purchaseId, int consumerId) {
        this.purchaseId = purchaseId;
        this.consumerId = consumerId;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }
}
