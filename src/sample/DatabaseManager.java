package sample;

import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<Song> getSongs(String searchTerm) {
        ArrayList<Song> songs = new ArrayList<>();
        try {
            PreparedStatement songStatement = c.prepareStatement("SELECT songs.name, albums.name, artists.name FROM songs, albums, artists WHERE songs.name LIKE ? && songs.albums_idalbums = albums.idalbums && albums.artists_idartists = artists.idartists");


                    songStatement.setString(1, "%" + searchTerm + "%");

            ResultSet rs = songStatement.executeQuery();

            while (rs.next()){
                songs.add(new Song(rs.getString("artists.name"), rs.getString("albums.name"), rs.getString("songs.name"), new int[]{0, 0}));
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
}
