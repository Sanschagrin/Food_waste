/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ggreg
 */
public class ConsumerDAOImpl implements ConsumerDAO{
    private Connection connection;
    
    public ConsumerDAOImpl() throws SQLException, ClassNotFoundException {
    this.connection = DBConnection.getConnection();
    }

    private static final String all = "SELECT * FROM Consumers";
    private static final String byID = "SELECT * FROM Consumers WHERE consumer_id = ?";
    private static final String insert = "INSERT INTO Consumers (consumer_name, consumer_email, consumer_password, subscriber) VALUES (?, ?, ?, ?)";
    private static final String update = "UPDATE Consumers SET consumer_name = ?, consumer_email = ?, consumer_password = ?, consumer_description, subscriber = ? WHERE consumer_id = ?";
    private static final String delete = "DELETE FROM Consumers WHERE consumer_id = ?";
    private static final String QUERY = "SELECT * FROM consumers WHERE username = ? AND password = ?";


    @Override
    public List<ConsumerDTO> getAllConsumers() throws SQLException {
        List<ConsumerDTO> consumers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(all)) {
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                consumers.add(new ConsumerDTO(results.getInt("consumer_id"), results.getString("consumer_name"), results.getString("consumer_email"), results.getString("consumer_password"), results.getBoolean("subscriber")));
            }
        }
        return consumers;
    }

    @Override
    public ConsumerDTO getConsumerById(int consumer_id) throws SQLException {
        ConsumerDTO consumers = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(byID)) {
            preparedStatement.setInt(1, consumer_id);
            try(ResultSet results = preparedStatement.executeQuery()){
            if (results.next()) {
                return new ConsumerDTO(results.getInt("consumer_id"), results.getString("consumer_name"), results.getString("consumer_email"), results.getString("consumer_password"), results.getBoolean("subscriber"));
            }
            }
        }
        return consumers;
    }

    @Override
    public void addConsumer(ConsumerDTO consumer) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
            preparedStatement.setString(1, consumer.getConsumerName());
            preparedStatement.setString(2, consumer.getConsumerEmail());
            preparedStatement.setString(3, consumer.getConsumerPassword());
            preparedStatement.setBoolean(4, consumer.getSubscriber());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Added consumer: " + consumer);
            } else {
                System.out.println("Failed to add consumer: " + consumer);
            }
    }
    }

    @Override
    public void updateConsumer(ConsumerDTO consumer) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setString(1, consumer.getConsumerName());
            preparedStatement.setString(2, consumer.getConsumerEmail());
            preparedStatement.setString(3, consumer.getConsumerPassword());
            preparedStatement.setBoolean(4, consumer.getSubscriber());
            preparedStatement.setInt(5, consumer.getConsumerId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteConsumer(ConsumerDTO consumer) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setInt(1, consumer.getConsumerId());
            preparedStatement.executeUpdate();
        }
    }
        @Override
    public ConsumerDTO getConsumerByUsernameAndPassword(String consumer_name, String consumer_password) throws SQLException {
        ConsumerDTO consumer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setString(1, consumer_name);
            preparedStatement.setString(2, consumer_password);
            ResultSet results = preparedStatement.executeQuery();
            if (results.next()) {
                consumer = new ConsumerDTO(
                    results.getInt("consumer_id"),
                    results.getString("consumer_name"),
                    results.getString("consumer_email"),
                    results.getString("consumer_password"),
                    results.getBoolean("subscribed")
                );
            }
        }
        return consumer;
    }

}

