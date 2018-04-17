package sample;

import java.sql.*;

public class DatabaseManager {
    private static DatabaseManager instance = null;
    Password pw = new Password();
    private String url;
    private Connection c;
    private Statement st;
    private DatabaseManager() {
        try {
            url = "jdbc:mysql://den1.mysql2.gear.host:3306/onlinemusicstore?user=onlinemusicstore&password=OnlineMusicStore!";
            c = DriverManager.getConnection(url);
            st = c.createStatement();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static DatabaseManager getInstance(){
        if (instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }

    public boolean passwordCheck(String username, String password){
        try {
            PreparedStatement checkStatement = c.prepareStatement("SELECT * FROM users WHERE username= ?;");
            checkStatement.setString(1, username);
            ResultSet rs = checkStatement.executeQuery();
            while (rs.next()){
                if (rs.getString("password").equals(pw.passwordEncryptor(username, password))){
                    return true;
                }
            }

        } catch (SQLException ex){
            ex.printStackTrace();

        }
        return false;
    }
    public void addAccount(String username,String password){

        try {
            PreparedStatement createStatement = c.prepareStatement("Insert into users (username, password) values (? , ?);");
            createStatement.setString(1, username);
            createStatement.setString(2, pw.passwordEncryptor(username, password));
            createStatement.executeUpdate();

        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
