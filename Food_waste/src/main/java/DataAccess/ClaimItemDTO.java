/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

/**
 *
 * @author mylen
 */
public class ClaimItemDTO {
    private int claim_id;
    private int item_id;
    private int quantity;

    public ClaimItemDTO(int claim_id, int item_id , int quantity){
    this.claim_id = claim_id;
    this.item_id = item_id;
    this.quantity = quantity;
    }
    
    public int getClaimId(){
        return claim_id;
    }
    public void setClaimId(int claim_id){
        this.claim_id = claim_id;
    }
    public int getItemId(){
        return item_id;
    }
    public void setItemId(int item_id){
        this.item_id = item_id;
    }
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
}

}