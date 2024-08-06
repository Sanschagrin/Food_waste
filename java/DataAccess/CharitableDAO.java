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
public interface CharitableDAO {
    List<CharitableDTO> getAllCharitable() throws SQLException;
    CharitableDTO getCharitableById(int charitable_id) throws SQLException;
    void addCharitable(CharitableDTO charitable) throws SQLException;
    void updateCharitable(CharitableDTO charitable) throws SQLException;
    void deleteCharitable(CharitableDTO charitable) throws SQLException;
    CharitableDTO getCharitableByUsernameAndPassword(String charitable_name, String charitable_password) throws SQLException;

    
}
