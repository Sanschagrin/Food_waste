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
public class NewsletterDAOImpl implements NewsletterDAO {

    private static Connection connection;

    /**
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public NewsletterDAOImpl() throws SQLException, ClassNotFoundException {
        this.connection = DBConnection.getConnection();
    }

    private static final String all = "SELECT * FROM Newsletter";
    private static final String byID = "SELECT * FROM Newsletter WHERE newsletter_id = ?";
    private static final String insert = "INSERT INTO Newsletter (newsletter_name, newsletter_article, item_id, sale_price, uploadDate) VALUES (?, ?, ?, ?, ?)";
    private static final String update = "UPDATE Newsletter SET newsletter_id = ?, newsletter_name = ?, newsletter_article = ?, item_id = ?, sale_price = ? WHERE newsletter_id = ?";
    private static final String delete = "DELETE FROM Newsletter WHERE newsletter_id = ?";
    private static final String allSortedByDate = "SELECT * FROM Newsletter ORDER BY uploadDate DESC";

    /**
     *
     * @return @throws SQLException
     */
    @Override
    public List<NewsletterDTO> getAllNewsletters() throws SQLException {
        List<NewsletterDTO> newsletters = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(allSortedByDate)) {
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                newsletters.add(new NewsletterDTO(
                        results.getInt("newsletter_id"),
                        results.getString("newsletter_name"),
                        results.getString("newsletter_article"),
                        results.getInt("item_id"),
                        results.getDouble("sale_price"),
                        results.getDate("uploadDate")
                ));
            }
        }
        return newsletters;
    }

    /**
     *
     * @param newsletter_id
     * @return NewsletterDTO
     * @throws SQLException
     */
    @Override
    public NewsletterDTO getNewsletterById(int newsletter_id) throws SQLException {
        NewsletterDTO newsletter = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(byID)) {
            preparedStatement.setInt(1, newsletter_id);
            try (ResultSet results = preparedStatement.executeQuery()) {
                if (results.next()) {
                    return new NewsletterDTO(results.getInt("newsletter_id"), results.getString("newsletter_name"), results.getString("newsletter_article"), results.getInt("item_id"), results.getDouble("sale_price"), results.getDate("uploadDate"));
                }
            }
        }
        return newsletter;
    }

    /**
     *
     * @param newsletter
     * @throws SQLException
     */
    @Override
    public void addNewsletter(NewsletterDTO newsletter) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, newsletter.getNewsletterName());
            preparedStatement.setString(2, newsletter.getNewsletterArticle());
            preparedStatement.setInt(3, newsletter.getItemId());
            preparedStatement.setDouble(4, newsletter.getSale());
            preparedStatement.setDate(5, new java.sql.Date(newsletter.getUploadDate().getTime()));
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Added newsletter: " + newsletter);
            } else {
                System.out.println("Failed to add newsletter: " + newsletter);
            }
        }
    }

    /**
     *
     * @param newsletter
     * @throws SQLException
     */
    @Override
    public void updateNewsletter(NewsletterDTO newsletter) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setString(1, newsletter.getNewsletterName());
            preparedStatement.setString(2, newsletter.getNewsletterArticle());
            preparedStatement.setInt(3, newsletter.getItemId());
            preparedStatement.setDouble(4, newsletter.getSale());
            preparedStatement.setDate(5, newsletter.getUploadDate());
            preparedStatement.executeUpdate();
        }
    }

    /**
     *
     * @param newsletter
     * @throws SQLException
     */
    @Override
    public void deleteNewsletter(NewsletterDTO newsletter) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setInt(1, newsletter.getNewsletterId());
            preparedStatement.executeUpdate();
        }
    }

}
