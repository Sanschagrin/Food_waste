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
public class NewsletterDTO {
    private int newsletter_id;
    private String newsletter_name;
    private String newsletter_article;
    private int item_id;
    private double sale_price;
    private Date uploadDate; // Add this line

    public NewsletterDTO(int newsletter_id, String newsletter_name, String newsletter_article, int item_id, double sale_price, Date uploadDate) {
        this.newsletter_id = newsletter_id;
        this.newsletter_name = newsletter_name;
        this.newsletter_article = newsletter_article;
        this.item_id = item_id;
        this.sale_price = sale_price;
        this.uploadDate = uploadDate;  // Initialize this field
    }

    public int getNewsletterId() {
        return newsletter_id;
    }

    public void setNewsletterId(int newsletter_id) {
        this.newsletter_id = newsletter_id;
    }

    public String getNewsletterName() {
        return newsletter_name;
    }

    public void setNewsletterName(String newsletter_name) {
        this.newsletter_name = newsletter_name;
    }

    public String getNewsletterArticle() {
        return newsletter_article;
    }

    public void setNewsletterArticle(String newsletter_article) {
        this.newsletter_article = newsletter_article;
    }

    public int getItemId() {
        return item_id;
    }

    public void setItemId(int item_id) {
        this.item_id = item_id;
    }

    public double getSale() {
        return sale_price;
    }

    public void setSale(double sale_price) {
        this.sale_price = sale_price;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}