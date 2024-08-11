package DataAccess;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ggreg and mylene
 */
public interface InventoryDAO {

    /**
     *
     * @return List InventoryDTO
     * @throws SQLException
     */
    List<InventoryDTO> getAllItems() throws SQLException;

    /**
     *
     * @param item_id
     * @return InventoryDTO
     * @throws SQLException
     */
    InventoryDTO getItemById(int item_id) throws SQLException;

    /**
     *
     * @param item
     * @throws SQLException
     */
    void addItem(InventoryDTO item) throws SQLException;

    /**
     *
     * @param item
     * @throws SQLException
     */
    void updateItem(InventoryDTO item) throws SQLException;

    /**
     *
     * @param item_id
     * @throws SQLException
     */
    void deleteItem(int item_id) throws SQLException;

    /**
     *
     * @param retailerId
     * @return List InventoryDTO
     * @throws SQLException
     */
    List<InventoryDTO> getItemsByRetailerId(int retailerId) throws SQLException;  // New method

}
