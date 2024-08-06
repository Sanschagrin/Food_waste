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
public interface InventoryDAO {
    List<InventoryDTO> getAllItems() throws SQLException;
    InventoryDTO getItemById(int item_id) throws SQLException;
    void addItem(InventoryDTO item) throws SQLException;
    void updateItem(InventoryDTO item) throws SQLException;
    void deleteItem(InventoryDTO item) throws SQLException;
}
