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
public interface RetailerDAO {

    /**
     *
     * @return @throws SQLException
     */
    List<RetailerDTO> getAllRetailers() throws SQLException;

    /**
     *
     * @param retailer_id
     * @return RetailerDTO
     * @throws SQLException
     */
    RetailerDTO getRetailerById(int retailer_id) throws SQLException;

    /**
     *
     * @param retailer
     * @throws SQLException
     */
    void addRetailer(RetailerDTO retailer) throws SQLException;

    /**
     *
     * @param retailer
     * @throws SQLException
     */
    void updateRetailer(RetailerDTO retailer) throws SQLException;

    /**
     *
     * @param retailer
     * @throws SQLException
     */
    void deleteRetailer(RetailerDTO retailer) throws SQLException;

    /**
     *
     * @param retailer_name
     * @param retailer_password
     * @return RetailerDTO
     * @throws SQLException
     */
    RetailerDTO getRetailerByUsernameAndPassword(String retailer_name, String retailer_password) throws SQLException;

}
