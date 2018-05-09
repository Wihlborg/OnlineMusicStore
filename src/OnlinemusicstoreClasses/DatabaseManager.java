package OnlinemusicstoreClasses;

import java.sql.*;
import java.util.ArrayList;


/*
Singleton class to handle all database matters and SQL querys
 */


public class DatabaseManager {
    private static DatabaseManager instance = null;
    Password pw = new Password();
    private String url;
    private Connection c;
    private Statement st;
    private CurrentUser currentUser;


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

    public ArrayList<Song> getSongs(String searchTerm) {
        ArrayList<Song> songs = new ArrayList<>();
        try {
            PreparedStatement songStatement = c.prepareStatement("SELECT songs.name, albums.name, artists.name , songs.price FROM songs, albums, " +
                    "artists WHERE songs.name LIKE ? " +
                    "&& songs.albums_idalbums = albums.idalbums " +
                    "&& albums.artists_idartists = artists.idartists");


                    songStatement.setString(1, "%" + searchTerm + "%");

            ResultSet rs = songStatement.executeQuery();

            while (rs.next()){
                songs.add(new Song(rs.getString("artists.name"),
                        rs.getString("albums.name"),
                        rs.getString("songs.name"),
                        new int[]{0, 0},rs.getDouble("songs.price")));
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return songs;
    }

    public ArrayList<Album> getAlbums(String searchTerm){
        ArrayList<Album> albums = new ArrayList<>();
        try {
            PreparedStatement albumStatement = c.prepareStatement("SELECT albums.name" +
                    " FROM albums" +
                    " WHERE albums.name LIKE ?");
            albumStatement.setString(1, "%" + searchTerm + "%");

            ResultSet rs = albumStatement.executeQuery();
            while (rs.next()){
                albums.add(new Album(rs.getString("albums.name"), new ArrayList<>()));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return albums;
    }

    public void updateCurrentUser(String username){
        currentUser = CurrentUser.getInstance();

        try {
            PreparedStatement getUserInfoStatement = c.prepareStatement("SELECT idusers, is_artist, is_admin  FROM users WHERE username = ?");
            getUserInfoStatement.setString(1, username);

            ResultSet rs = getUserInfoStatement.executeQuery();

            while (rs.next()){
                currentUser.setUserName(username);
                currentUser.setUserId(rs.getInt("idusers"));
                currentUser.setArtist(rs.getBoolean("is_artist"));
                currentUser.setAdmin(rs.getBoolean("is_admin"));
            }

        } catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
    }

    public boolean checkUsername(String username,String email,String seq) {
        try {
            PreparedStatement checkStatement = c.prepareStatement("SELECT username,email,securityanswer " +
                    "FROM users WHERE username= ? and email= ? and securityanswer=?");
            checkStatement.setString(1, username);
            checkStatement.setString(2,email);
            checkStatement.setString(3,seq);
            ResultSet rs = checkStatement.executeQuery();
            while (rs.next()){
                if (rs.getString("username").equals(username) && rs.getString("email").
                        equals(email)&& rs.getString("securityanswer").equals(seq)){
                    return true;
                }

            }

        } catch (SQLException ex){
            ex.printStackTrace();

        }
        return false;
}
    public void changeUsersPassword(String username, String newPassword) {
        try {
            PreparedStatement createStatement = c.prepareStatement("UPDATE users SET password = ? WHERE username = ?;");
            createStatement.setString(1, pw.passwordEncryptor(username, newPassword));
            createStatement.setString(2, username);
            createStatement.executeUpdate();




            } catch (SQLException e) {
            e.printStackTrace();
        }

        }

    }
