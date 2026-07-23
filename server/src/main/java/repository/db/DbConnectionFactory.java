/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import konfiguracija.Konfiguracija;

/**
 *
 * @author Una
 */
public class DbConnectionFactory {
    private Connection connection;
    private static DbConnectionFactory instance;

    public static DbConnectionFactory getInstance(){
        if(instance == null) instance = new DbConnectionFactory();
        return instance;
    }
     
    public Connection getConnection() throws SQLException,IOException{
        if (connection == null || connection.isClosed()) {
            try {
                String url = Konfiguracija.getInstance().getProperty("url");
                String username = Konfiguracija.getInstance().getProperty("username");
                String password = Konfiguracija.getInstance().getProperty("password");
                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
            } catch (SQLException ex) {
                System.out.println("Neuspesno uspostavljanje konekcije! " + ex.getMessage());
                throw ex;
            }
        }
        return connection;
    }
}
