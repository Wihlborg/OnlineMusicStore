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

    public boolean passwordCheck(String username, String password){
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM users WHERE username= '" + username +"';");
            while (rs.next()){
                if (rs.getString("password").equalsIgnoreCase(password)){
                    return true;
                }
            }

        } catch (SQLException ex){
            ex.printStackTrace();

        }
        return false;
    }
}
