package DataAccess;

public class PurchaseItemDTO {

    private int purchaseId;
    private int itemId;
    private int quantity;

    /**
     * parameterized Constructor
     *
     * @param purchaseId
     * @param itemId
     * @param quantity
     */
    public PurchaseItemDTO(int purchaseId, int itemId, int quantity) {
        this.purchaseId = purchaseId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
