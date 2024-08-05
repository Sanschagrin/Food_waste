/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    
    public InventoryDTO(int item_id, int retailer_id, String item_name, String item_description, double price, Date expiry_date){
    this.item_id = item_id;
    this.retailer_id = retailer_id;
    this.item_name = item_name;
    this.item_description = item_description;
    this.price = price;
    this.expiry_date = expiry_date;
        
    }
    
    public int getItemId(){
        return item_id;
    }
    public void setItemId(int item_id){
        this.item_id = item_id;
    }
    public int getRetailerid(){
        return retailer_id;
    }
    public void setRetailerId(int retailer_id){
        this.retailer_id = retailer_id;
    }
    public String getItemName(){
        return item_name;
    }
    public void setItemName(String item_name){
        this.item_name = item_name;
    }
    public String getItemDescription(){
        return item_description;
    }
    public void setItemDescription(String item_description){
        this.item_description = item_description;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public Date getExpiryDate(){
        return expiry_date;
    }
    public void setExpiryDat(Date expiry_date){
        this.expiry_date = expiry_date;
    }
}
