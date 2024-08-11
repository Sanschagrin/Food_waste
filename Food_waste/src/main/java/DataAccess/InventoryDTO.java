package DataAccess;

import java.sql.Date;

/**
 *
 * @author ggreg
 */
public class InventoryDTO {

    private int item_id;
    private int retailer_id;
    private String item_name;
    private String item_description;
    private double price;
    private Date expiry_date;
    private int quantity;

    /**
     * Parameterized constructor
     *
     * @param item_id
     * @param retailer_id
     * @param item_name
     * @param item_description
     * @param price
     * @param expiry_date
     * @param quantity
     */
    public InventoryDTO(int item_id, int retailer_id, String item_name, String item_description, double price, Date expiry_date, int quantity) {
        this.item_id = item_id;
        this.retailer_id = retailer_id;
        this.item_name = item_name;
        this.item_description = item_description;
        this.price = price;
        this.expiry_date = expiry_date;
        this.quantity = quantity;

    }

    public int getItemId() {
        return item_id;
    }

    public void setItemId(int item_id) {
        this.item_id = item_id;
    }

    public int getRetailerId() {
        return retailer_id;
    }

    public void setRetailerId(int retailer_id) {
        this.retailer_id = retailer_id;
    }

    public String getItemName() {
        return item_name;
    }

    public void setItemName(String item_name) {
        this.item_name = item_name;
    }

    public String getItemDescription() {
        return item_description;
    }

    public void setItemDescription(String item_description) {
        this.item_description = item_description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getExpiryDate() {
        return expiry_date;
    }

    public void setExpiryDate(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
