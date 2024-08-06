/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

/**
 *
 * @author ggreg
 */

public class ConsumerDTO {
    private int consumer_id;
    private String consumer_name;
    private String consumer_email;
    private String consumer_password;
    private boolean subscriber;
    
    public ConsumerDTO(int consumer_id, String consumer_name, String consumer_email, String consumer_password, boolean subscriber){
    this.consumer_id = consumer_id;
    this.consumer_name = consumer_name;
    this.consumer_email = consumer_email;
    this.consumer_password = consumer_password;
    this.subscriber = subscriber;
    }
    
    public int getConsumerId(){
        return consumer_id;
    }
    public void setConsumerId(int consumer_id){
        this.consumer_id = consumer_id;
    }
    public String getConsumerName(){
        return consumer_name;
    }
    public void setConsumerName(String consumer_name){
        this.consumer_name = consumer_name;
    }
    public String getConsumerEmail(){
        return consumer_email;
    }
    public void setConsumerEmail(String consumer_email){
        this.consumer_email = consumer_email;
    }
    public String getConsumerPassword(){
        return consumer_password;
    }
    public void setConsumerPassword(String consumer_password){
        this.consumer_password = consumer_password;
    }
    public boolean getSubscriber(){
        return subscriber;
    }
    public void setSubscriber(boolean subscriber){
        this.subscriber = subscriber;
    }
}
