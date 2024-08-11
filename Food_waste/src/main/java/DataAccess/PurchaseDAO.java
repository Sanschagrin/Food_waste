package DataAccess;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mylen
 */
public interface PurchaseDAO {

    /**
     *
     * @param purchase
     * @return
     * @throws SQLException
     */
    int addPurchase(PurchaseDTO purchase) throws SQLException;

    /**
     *
     * @param purchase
     * @throws SQLException
     */
    void createPurchase(PurchaseDTO purchase) throws SQLException;

    /**
     *
     * @param consumerId
     * @return List<PurchaseItemDTO>
     * @throws SQLException
     */
    List<PurchaseDTO> getPurchasesByConsumerId(int consumerId) throws SQLException;

    /**
     *
     * @param consumerId
     * @return List<PurchaseItemDTO>
     * @throws SQLException
     */
    List<PurchaseItemDTO> getPurchaseItemsByConsumerId(int consumerId) throws SQLException;

    /**
     *
     * @param purchaseId
     * @return PurchaseDTO
     * @throws SQLException
     */
    PurchaseDTO getPurchaseById(int purchaseId) throws SQLException;
}
