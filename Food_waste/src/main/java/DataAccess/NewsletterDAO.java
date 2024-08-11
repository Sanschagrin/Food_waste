package DataAccess;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ggreg
 */
public interface NewsletterDAO {

    /**
     *
     * @return List NewsletterDTO
     * @throws SQLException
     */
    List<NewsletterDTO> getAllNewsletters() throws SQLException;

    /**
     *
     * @param newsletter_id
     * @return NewsletterDTO
     * @throws SQLException
     */
    NewsletterDTO getNewsletterById(int newsletter_id) throws SQLException;

    /**
     *
     * @param newsletter
     * @throws SQLException
     */
    void addNewsletter(NewsletterDTO newsletter) throws SQLException;

    /**
     *
     * @param newsletter
     * @throws SQLException
     */
    void updateNewsletter(NewsletterDTO newsletter) throws SQLException;

    /**
     *
     * @param newsletter
     * @throws SQLException
     */
    void deleteNewsletter(NewsletterDTO newsletter) throws SQLException;
}
