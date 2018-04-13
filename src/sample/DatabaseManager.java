package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    public DatabaseManager() {
        try {
            String url = "jdbc:mysql://den1.mysql2.gear.host:3306/onlinemusicstore?user=onlinemusicstore&password=OnlineMusicStore!";
            Connection c = DriverManager.getConnection(url);
            Statement st = c.createStatement();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
