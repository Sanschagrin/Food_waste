package DataAccess;

import java.sql.SQLException;
import java.util.List;

public interface PurchaseItemDAO {

    /**
     *
     * @param purchaseItem
     * @throws SQLException
     */
    void addPurchaseItem(PurchaseItemDTO purchaseItem) throws SQLException;

    /**
     *
     * @param purchaseId
     * @return List<PurchaseItemDTO>
     * @throws SQLException
     */
    List<PurchaseItemDTO> getPurchaseItemsByPurchaseId(int purchaseId) throws SQLException;

    /**
     *
     * @param purchaseItemId
     * @return PurchaseItemDTO
     * @throws SQLException
     */
    PurchaseItemDTO getPurchaseItemById(int purchaseItemId) throws SQLException;
}
