package sample;

import java.sql.*;

public class DatabaseManager {
    private String url;
    private Connection c;
    private Statement st;
    public DatabaseManager() {
        try {
            url = "jdbc:mysql://den1.mysql2.gear.host:3306/onlinemusicstore?user=onlinemusicstore&password=OnlineMusicStore!";
            c = DriverManager.getConnection(url);
            st = c.createStatement();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public boolean passwordCheck(String username, String password){ //TODO Hash and salt password
        try {
            PreparedStatement checkStatement = c.prepareStatement("SELECT * FROM users WHERE username= ?;");
            checkStatement.setString(1, username);
            ResultSet rs = checkStatement.executeQuery();
            while (rs.next()){
                if (rs.getString("password").equals(password)){
                    return true;
                }
            }

        } catch (SQLException ex){
            ex.printStackTrace();

        }
        return false;
    }
    public void addAccount(String username,String password){ //TODO Hash and salt password

        try {
            PreparedStatement createStatement = c.prepareStatement("Insert into users (username, password) values (? , ?);");
            createStatement.setString(1, username);
            createStatement.setString(2, password);
            createStatement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
