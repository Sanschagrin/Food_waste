/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataAccess;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ggreg and mylene
 */
public interface InventoryDAO {
    List<InventoryDTO> getAllItems() throws SQLException;
    InventoryDTO getItemById(int item_id) throws SQLException;
    void addItem(InventoryDTO item) throws SQLException;
    void updateItem(InventoryDTO item) throws SQLException;
    void deleteItem(int item_id) throws SQLException;
    List<InventoryDTO> getItemsByRetailerId(int retailerId) throws SQLException;  // New method

}
