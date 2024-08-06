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
    List<RetailerDTO> getAllRetailers() throws SQLException;
    RetailerDTO getRetailerById(int retailer_id) throws SQLException;
    void addRetailer(RetailerDTO retailer) throws SQLException;
    void updateRetailer(RetailerDTO retailer) throws SQLException;
    void deleteRetailer(RetailerDTO retailer) throws SQLException;
    RetailerDTO getRetailerByUsernameAndPassword(String retailer_name, String retailer_password) throws SQLException;

}
