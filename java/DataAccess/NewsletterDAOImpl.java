/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ggreg
 */
public class NewsletterDAOImpl implements NewsletterDAO{
    private static Connection connection;
    
    public NewsletterDAOImpl() throws SQLException, ClassNotFoundException {
    this.connection = DBConnection.getConnection();
    }

    private static final String all = "SELECT * FROM Newsletter";
    private static final String byID = "SELECT * FROM Newsletter WHERE newsletter_id = ?";
    private static final String insert = "INSERT INTO Newsletter (newsletter_id, newsletter_name, newsletter_article, item_id, sale) VALUES (?, ?, ?, ?, ?)";
    private static final String update = "UPDATE Newsletter SET newsletter_id = ?, newsletter_name = ?, newsletter_article = ?, item_id = ?, sale = ? WHERE newsletter_id = ?";
    private static final String delete = "DELETE FROM Newsletter WHERE newsletter_id = ?";

    @Override
    public List<NewsletterDTO> getAllNewsletters() throws SQLException {
        List<NewsletterDTO> newsletter = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(all)) {
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                newsletter.add(new NewsletterDTO(results.getInt("newsletter_id"), results.getString("newsletter_name"), results.getString("newsletter_article"), results.getInt("item_id"), results.getDouble("sale")));
            }
        }
        return newsletter;
    }

    @Override
    public NewsletterDTO getNewsletterById(int newsletter_id) throws SQLException {
        NewsletterDTO newsletter = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(byID)) {
            preparedStatement.setInt(1, newsletter_id);
            try(ResultSet results = preparedStatement.executeQuery()){
            if (results.next()) {
                return new NewsletterDTO(results.getInt("newsletter_id"), results.getString("newsletter_name"), results.getString("newsletter_article"), results.getInt("item_id"), results.getDouble("sale"));
            }
            }
        }
        return newsletter;
    }

    @Override
    public void addNewsletter(NewsletterDTO newsletter) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, newsletter.getNewsletterName());
            preparedStatement.setString(2, newsletter.getNewsletterArticle());
            preparedStatement.setInt(3, newsletter.getItemId());
            preparedStatement.setDouble(4, newsletter.getSale());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Added newsletter: " + newsletter);
            } else {
                System.out.println("Failed to add newsletter: " + newsletter);
            }
    }
    }

    @Override
    public void updateNewsletter(NewsletterDTO newsletter) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setString(1, newsletter.getNewsletterName());
            preparedStatement.setString(2, newsletter.getNewsletterArticle());
            preparedStatement.setInt(3, newsletter.getItemId());
            preparedStatement.setDouble(4, newsletter.getSale());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteNewsletter(NewsletterDTO newsletter) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setInt(1, newsletter.getNewsletterId());
            preparedStatement.executeUpdate();
        }
    }

}
