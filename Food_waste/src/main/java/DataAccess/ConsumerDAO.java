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
public interface ConsumerDAO {
    List<ConsumerDTO> getAllConsumers() throws SQLException;
    ConsumerDTO getConsumerById(int consumer_id) throws SQLException;
    void addConsumer(ConsumerDTO consumer) throws SQLException;
    void updateConsumer(ConsumerDTO consumer) throws SQLException;
    void deleteConsumer(ConsumerDTO consumer) throws SQLException;
    ConsumerDTO getConsumerByUsernameAndPassword(String consumer_name, String consumer_password) throws SQLException;

}
