/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Gregory Mah
 * 041114855
 * Project
 * Teddy Yap
 * 
 * DBConnection is in charge of managing the JDBC, it uses the configuration method to read the database
 * data from the db.properties file establishing a connection to SQL. It uses the singleton pattern, with a private constructor
 * to ensure only one instance of the connection can be connected. 
 */
public class DBConnection {
	
	private static Connection connection = null;
	
	private static String url;
	private static String user;
	private static String password;
	private static String driver;
        
        
        static {
            configuration();
        }
                
        /**
         * To ensure class is in singleton the constructor is private, ensuring only one instance can be created.
         * This method initializes a connection to the database using the connection variables.
         * 
         * @throws ClassNotFoundException if the database driver class is not found
         */
        private DBConnection() throws ClassNotFoundException {
            try { Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e){
                e.printStackTrace();
            }
            
        }
        
    /**
     *
     * This method returns a database connection object.
     * 
     * @return connection to sql database.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
            synchronized (DBConnection.class){
                if (connection == null){
                    new DBConnection();
                }
            } return connection;
        } 
        
    /**
     * Loads the database configuration from the db.properties file found in the resources folder of the netbeans project..
     * This method configures the connection properties (db.user, db.password, db.url, db.driver) and passes them into 
     * corresponding variables. It finds this data in the provided path.
     */
        private static void configuration(){
            Properties properties = new Properties();
            try (InputStream database = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
                if (database == null) {
                    throw new IOException("Properties file not found");
                }
            properties.load(database);
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");
            url = properties.getProperty("db.url");
            driver = properties.getProperty("db.driver");
            System.out.println("Database Properties Loaded:");
            System.out.println("URL: " + url);
            System.out.println("User: " + user);
            System.out.println("Password: " + password);
            System.out.println("Driver: " + driver);
             
            } catch (IOException i){
                i.printStackTrace();
            }
        }
}
