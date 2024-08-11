package DataAccess;

import java.sql.SQLException;
import java.util.List;

public interface PurchaseItemDAO {
    void addPurchaseItem(PurchaseItemDTO purchaseItem) throws SQLException;
    List<PurchaseItemDTO> getPurchaseItemsByPurchaseId(int purchaseId) throws SQLException;
    PurchaseItemDTO getPurchaseItemById(int purchaseItemId) throws SQLException;
}
