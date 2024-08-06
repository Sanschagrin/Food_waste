/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

/**
 *
 * @author ggreg
 */
public class RetailerDTO {
    private int retailer_id;
    private String retailer_name;
    private String retailer_email;
    private String retailer_password;
    private String retailer_description;
    
    public RetailerDTO(int retailer_id, String retailer_name, String retailer_email, String retailer_password, String retailer_description){
        this.retailer_id = retailer_id;
        this.retailer_name = retailer_name;
        this.retailer_email = retailer_email;
        this.retailer_password = retailer_password;
        this.retailer_description = retailer_description;
    }
    
    public int getRetailerId(){
        return retailer_id;
    }
    public void setRetailerId(int retailer_id){
        this.retailer_id = retailer_id;
    }
    public String getRetailerName(){
        return retailer_name;
    }
    public void setRetailerName(String retailer_name){
        this.retailer_name = retailer_name;
    }
    public String getRetailerEmail(){
        return retailer_email;
    }
    public void setRetailerEmail(String retailer_email){
        this.retailer_email = retailer_email;
    }
    public String getRetailerPassword(){
        return retailer_password;
    }
    public void setRetailerPassword(String retailer_password){
        this.retailer_password = retailer_password;
    }
    public String getRetailerDescription(){
        return retailer_description;
    }
    public void setRetailerDescription(String retailer_description){
        this.retailer_description = retailer_description;
    }
}
