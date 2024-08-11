package DataAccess;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ggreg
 */
public interface ConsumerDAO {

    /**
     *
     * @return List<ConsumerDTO>
     * @throws SQLException
     */
    List<ConsumerDTO> getAllConsumers() throws SQLException;

    /**
     *
     * @param consumer_id
     * @return ConsumerDTO
     * @throws SQLException
     */
    ConsumerDTO getConsumerById(int consumer_id) throws SQLException;

    /**
     *
     * @param consumer
     * @throws SQLException
     */
    void addConsumer(ConsumerDTO consumer) throws SQLException;

    /**
     *
     * @param consumer
     * @throws SQLException
     */
    void updateConsumer(ConsumerDTO consumer) throws SQLException;

    /**
     *
     * @param consumer
     * @throws SQLException
     */
    void deleteConsumer(ConsumerDTO consumer) throws SQLException;

    /**
     *
     * @param consumer_name
     * @param consumer_password
     * @return ConsumerDTO
     * @throws SQLException
     */
    ConsumerDTO getConsumerByUsernameAndPassword(String consumer_name, String consumer_password) throws SQLException;

}
