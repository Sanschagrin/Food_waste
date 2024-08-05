/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

/**
 *
 * @author ggreg
 */
public class CharitableDTO {
    private int charitable_id;
    private String charitable_name;
    private String charitable_email;
    private String charitable_password;
    private String charitable_description;
    
    public CharitableDTO(int charitable_id, String charitable_name, String charitable_email, String charitable_password, String charitable_description){
        this.charitable_id = charitable_id;
        this.charitable_name = charitable_name;
        this.charitable_email = charitable_email;
        this.charitable_password = charitable_password;
        this.charitable_description = charitable_description;
        
    }
    
    public int getCharitableId(){
        return charitable_id;
    }
    public void setCharitableId(int charitable_id){
        this.charitable_id = charitable_id;
    }
    public String getCharitableName(){
        return charitable_name;
    }
    public void setCharitableName(String charitable_name){
        this.charitable_name = charitable_name;
    }
    public String getCharitableEmail(){
        return charitable_email;
    }
    public void setCharitableEmail(String charitable_email){
        this.charitable_email = charitable_email;
    }
    public String getCharitablePassword(){
        return charitable_password;
    }
    public void setCharitablePassword(String charitable_password){
        this.charitable_password = charitable_password;
    }
    public String getCharitableDescription(){
        return charitable_description;
    }
    public void setCharitableDescription(String charitable_description){
        this.charitable_description = charitable_description;
    }
}
