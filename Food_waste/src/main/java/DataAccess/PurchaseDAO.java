/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author mylen
 */
public interface PurchaseDAO {
    int addPurchase(PurchaseDTO purchase) throws SQLException;
    void createPurchase(PurchaseDTO purchase) throws SQLException;
    List<PurchaseDTO> getPurchasesByConsumerId(int consumerId) throws SQLException;
    List<PurchaseItemDTO> getPurchaseItemsByConsumerId(int consumerId) throws SQLException;
    PurchaseDTO getPurchaseById(int purchaseId) throws SQLException;
}

