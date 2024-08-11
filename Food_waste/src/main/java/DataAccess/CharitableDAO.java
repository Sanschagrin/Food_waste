package DataAccess;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ggreg
 */
public interface CharitableDAO {

    /**
     * Retrieves a list of all charitable
     *
     * @return List<CharitableDTO>
     * @throws SQLException
     */
    List<CharitableDTO> getAllCharitable() throws SQLException;

    /**
     * Retrieves a charitable entity from the database by its unique ID
     *
     * @param charitable_id
     * @return CharitableDTO
     * @throws SQLException
     */
    CharitableDTO getCharitableById(int charitable_id) throws SQLException;

    /**
     * Adds a new charitable entity to the database.
     *
     * @param charitable
     * @throws SQLException
     */
    void addCharitable(CharitableDTO charitable) throws SQLException;

    /**
     * Updates an existing charitable entity in the database.
     *
     * @param charitable
     * @throws SQLException
     */
    void updateCharitable(CharitableDTO charitable) throws SQLException;

    /**
     * Deletes an existing charitable entity from the database.
     *
     * @param charitable
     * @throws SQLException
     */
    void deleteCharitable(CharitableDTO charitable) throws SQLException;

    /**
     * Retrieves a charitable entity from the database
     *
     * @param charitable_name
     * @param charitable_password
     * @return
     * @throws SQLException
     */
    CharitableDTO getCharitableByUsernameAndPassword(String charitable_name, String charitable_password) throws SQLException;

}
