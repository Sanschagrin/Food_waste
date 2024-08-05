/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataAccess;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ggreg
 */
public interface NewsletterDAO {
    List<NewsletterDTO> getAllNewsletters() throws SQLException;
    NewsletterDTO getNewsletterById(int newsletter_id) throws SQLException;
    void addNewsletter(NewsletterDTO newsletter) throws SQLException;
    void updateNewsletter(NewsletterDTO newsletter) throws SQLException;
    void deleteNewsletter(NewsletterDTO newsletter) throws SQLException;
}
