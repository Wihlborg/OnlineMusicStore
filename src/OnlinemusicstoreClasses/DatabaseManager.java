
package OnlinemusicstoreClasses;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.*;
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public boolean passwordCheck(String username, String password) {

        try {
            PreparedStatement checkStatement = c.prepareStatement("SELECT * FROM users WHERE username= ?;");
            checkStatement.setString(1, username);
            ResultSet rs = checkStatement.executeQuery();
            while (rs.next()) {
                if (rs.getString("password").equals(pw.passwordEncryptor(username, password))) {
                    return true;

                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return false;
    }

    public void addAccount(String username, String password, String email, String securityanswer) {

        try {
            PreparedStatement createStatement = c.prepareStatement("Insert into users (username, password, email, securityanswer) values (? , ?, ?, ?);");
            createStatement.setString(1, username);
            createStatement.setString(2, pw.passwordEncryptor(username, password));
            createStatement.setString(3, email);
            createStatement.setString(4, securityanswer);
            createStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Song> getSongs(String searchTerm) {
        ArrayList<Song> songs = new ArrayList<>();
        try {
            PreparedStatement songStatement = c.prepareStatement("SELECT songs.idsongs, songs.songname, albums.name, artists.name , songs.price FROM songs, albums, " +
                    "artists WHERE songs.songname LIKE ? " +
                    "&& songs.albums_idalbums = albums.idalbums " +
                    "&& albums.artists_idartists = artists.idartists");


            songStatement.setString(1, "%" + searchTerm + "%");

            ResultSet rs = songStatement.executeQuery();

            while (rs.next()) {
                songs.add(new Song(rs.getString("artists.name"),
                        rs.getString("songs.songname"),
                        rs.getString("albums.name"),
                        rs.getInt("songs.idsongs"),
                        rs.getDouble("songs.price")));
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return songs;
    }

    public ArrayList<Album> getAlbums(String searchTerm) {
        ArrayList<Album> albums = new ArrayList<>();
        try {
            PreparedStatement albumStatement = c.prepareStatement("SELECT *" +
                    " FROM albums" +
                    " WHERE albums.name LIKE ?");
            albumStatement.setString(1, "%" + searchTerm + "%");

            ResultSet rs = albumStatement.executeQuery();
            while (rs.next()) {
                albums.add(new Album(rs.getString("albums.name"), rs.getInt("albums.idalbums")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return albums;
    }

    public ArrayList<User> getUsers(String searchTerm) {
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement userStatement = c.prepareStatement("SELECT * FROM users WHERE users.username LIKE ?");
            userStatement.setString(1, "%" + searchTerm + "%");
            ResultSet rs = userStatement.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt("idusers"), rs.getString("username"), rs.getString("email"), rs.getString("securityanswer"), rs.getInt("is_admin"), rs.getInt("is_artist")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }

    public void updateCurrentUser(String username) {
        currentUser = CurrentUser.getInstance();

        try {
            PreparedStatement getUserInfoStatement = c.prepareStatement("SELECT idusers, is_artist, is_admin  FROM users WHERE username = ?");
            getUserInfoStatement.setString(1, username);

            ResultSet rs = getUserInfoStatement.executeQuery();

            while (rs.next()) {
                currentUser.setUserName(username);
                currentUser.setUserId(rs.getInt("idusers"));
                currentUser.setArtist(rs.getBoolean("is_artist"));
                currentUser.setAdmin(rs.getBoolean("is_admin"));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public boolean checkUsername(String username, String email, String seq) {
        try {
            PreparedStatement checkStatement = c.prepareStatement("SELECT username,email,securityanswer " +
                    "FROM users WHERE username= ? and email= ? and securityanswer=?");
            checkStatement.setString(1, username);
            checkStatement.setString(2, email);
            checkStatement.setString(3, seq);
            ResultSet rs = checkStatement.executeQuery();
            while (rs.next()) {
                if (rs.getString("username").equals(username) && rs.getString("email").
                        equals(email) && rs.getString("securityanswer").equals(seq)) {
                    return true;
                }

            }

        } catch (SQLException ex) {
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

    public File getMusicFile(int songId) throws IOException {
        File fileToReturn = new File("song.wav");
        try {
            PreparedStatement getBlobStatement = c.prepareStatement("SELECT musicfile FROM songs WHERE idsongs = ?");
            getBlobStatement.setInt(1, songId);
            ResultSet rs = getBlobStatement.executeQuery();
            while (rs.next()) {
                //download the musicfile and convert it into a File if it isn't null
                Blob ablob = rs.getBlob(1);
                if (ablob == null) {
                    throw new IOException();
                }
                InputStream is = ablob.getBinaryStream();
                FileOutputStream fos = new FileOutputStream(fileToReturn);

                byte[] buff = new byte[16777215]; //max size of BLOB in DB
                for (int b = is.read(buff); b != -1; b = is.read(buff)) {
                    fos.write(buff, 0, b);
                }

                is.close();
                fos.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return fileToReturn;
    }
    //ARTIST METHODS START

    public ArrayList<Artist> getUsersArtists(int userId) {
        ArrayList<Artist> artists = new ArrayList<>();
        try {
            PreparedStatement uaStatement = c.prepareStatement("SELECT * FROM artists WHERE users_idusers = ?");
            uaStatement.setInt(1, userId);
            ResultSet rs = uaStatement.executeQuery();
            while (rs.next()) {
                artists.add(new Artist(rs.getInt("idartists"), rs.getString("name")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return artists;
    }

    public ArrayList<Album> getArtistsAlbums(int userId) {
        ArrayList<Album> albums = new ArrayList<>();
        try {
            PreparedStatement aaStatement = c.prepareStatement("SELECT * FROM albums WHERE artists_idartists = ?");
            aaStatement.setInt(1, userId);
            ResultSet rs = aaStatement.executeQuery();
            while (rs.next()) {
                albums.add(new Album(rs.getString("name"), rs.getInt("idalbums")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return albums;
    }

    public void addArtist(String name, int userId) {
        try {
            PreparedStatement addArtistStatement = c.prepareStatement("INSERT INTO artists (name, users_idusers) VALUES (?, ?)");
            addArtistStatement.setString(1, name);
            addArtistStatement.setInt(2, userId);
            addArtistStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addAlbum(String albumName, int artistId, double albumPrice) {
        try {
            PreparedStatement addSongStatement = c.prepareStatement("INSERT INTO albums (name, artists_idartists, price) VALUES (?, ? ,?)");
            addSongStatement.setString(1, albumName);
            addSongStatement.setInt(2, artistId);
            addSongStatement.setDouble(3, albumPrice);
            addSongStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addSong(String songName, int albumId, double songPrice, InputStream inputData) {
        try {
            PreparedStatement addSongStatement = c.prepareStatement("INSERT INTO songs (songname, albums_idalbums, price, musicfile) VALUES (?, ? , ?, ?)");
            addSongStatement.setString(1, songName);
            addSongStatement.setInt(2, albumId);
            addSongStatement.setDouble(3, songPrice);
            addSongStatement.setBlob(4, inputData);
            addSongStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //ARTIST METHODS END

    //ADMIN METHODS START
    public void banUser(int userId) {
        try {
            PreparedStatement banStatement = c.prepareStatement("DELETE FROM users WHERE idusers = ?");
            banStatement.setInt(1, userId);
            banStatement.executeUpdate();
        } catch (SQLException ex) {

        }
    }

    public void setAdmin(int userId) {
        try {
            PreparedStatement setAdminStatement = c.prepareStatement("UPDATE users SET is_admin = ? WHERE idusers = ?");
            setAdminStatement.setInt(2, userId);
            //Check if the user is admin. If TRUE, set FALSE, and vice versa.
            PreparedStatement getAdminStatement = c.prepareStatement("SELECT is_admin FROM users WHERE idusers = ?");
            getAdminStatement.setInt(1, userId);
            ResultSet rs = getAdminStatement.executeQuery();
            while (rs.next()) {
                if (rs.getBoolean("is_admin")) {
                    setAdminStatement.setBoolean(1, false);
                } else {
                    setAdminStatement.setBoolean(1, true);
                }
            }
            setAdminStatement.executeUpdate();

        } catch (SQLException ex) {

        }
    }

    public void setArtist(int userId) {
        try {
            PreparedStatement setArtistStatement = c.prepareStatement("UPDATE users SET is_artist = ? WHERE idusers = ?");
            setArtistStatement.setInt(2, userId);
            //Check if the user is already artist. If FALSE, set to TRUE and vice versa.
            PreparedStatement getArtistStatement = c.prepareStatement("SELECT is_artist FROM users WHERE idusers = ? ");
            getArtistStatement.setInt(1, userId);
            ResultSet rs = getArtistStatement.executeQuery();
            while (rs.next()) {
                if (rs.getBoolean("is_artist")) {
                    setArtistStatement.setBoolean(1, false);
                } else {
                    setArtistStatement.setBoolean(1, true);
                }
            }
            setArtistStatement.executeUpdate();

        } catch (SQLException ex) {

        }
    }

    //ADMIN METHODS END

    public String getEmail(String username) {
        ArrayList<String> onlineusers = new ArrayList<>();
        try {
            PreparedStatement userStatement = c.prepareStatement("SELECT email FROM users WHERE users.username = ?");
            userStatement.setString(1, username);
            ResultSet rs = userStatement.executeQuery();
            while (rs.next()) {
                onlineusers.add(rs.getString("email"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return String.valueOf(onlineusers.get(0).substring(0, onlineusers.get(0).length()));
    }

    public ArrayList<Song> getBoughtSongs(int userID) {
        ArrayList<Song> boughtSongs = new ArrayList<>();
        try {
            PreparedStatement boughtSongsStatement = c.prepareStatement("select * " +
                    " FROM songs, artists, albums, users_has_songs" +
                    " WHERE users_has_songs.users_idusers = ?" +
                    " AND users_has_songs.songs_idsongs = songs.idsongs" +
                    " AND albums.idalbums = songs.albums_idalbums" +
                    " AND artists.idartists = albums.artists_idartists;");
            boughtSongsStatement.setInt(1, userID);
            ResultSet rs = boughtSongsStatement.executeQuery();
            while (rs.next()) {
                boughtSongs.add(new Song(rs.getString(7), rs.getString(2), rs.getString(10), rs.getInt(1), rs.getDouble(4)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return boughtSongs;
    }

    public void userBoughtSongs(int userId, ObservableList<Song> songs) {
        //Using batch to execute several statements without waiting for network delay
        try {
            Statement statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            for (Song s : songs) {
                String inString = String.format("INSERT INTO users_has_songs VALUES ( %d , %d )", userId, s.getSongId());

                statement.addBatch(inString);
            }

            statement.executeBatch();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Song> weekPlaylist() {
        ArrayList<Song> lastTenSongs = new ArrayList<>();

        try {
            PreparedStatement lastTenSongsStatement = c.prepareStatement("SELECT songs.idsongs, songs.songname, albums.name, artists.name , songs.price FROM songs, albums, artists" +
                    " WHERE songs.albums_idalbums = albums.idalbums" +
                    "&& albums.artists_idartists = artists.idartists ORDER BY idsongs DESC LIMIT 10");

            ResultSet rs = lastTenSongsStatement.executeQuery();
            while (rs.next()) {
                lastTenSongs.add((new Song(rs.getString("artists.name"),
                        rs.getString("songs.songname"),
                        rs.getString("albums.name"),
                        rs.getInt("songs.idsongs"),
                        rs.getDouble("songs.price"))));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lastTenSongs;

    }

    public void changeEmail(String username) {
        try {
            PreparedStatement createStatement = c.prepareStatement("SELECT email FROM users WHERE users.username = ?;");
            createStatement.setString(1, username);
            createStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean emailCheck(String username, String email, String seq) {

        try {
            PreparedStatement checkStatement = c.prepareStatement("SELECT * FROM users WHERE username= ?;");
            checkStatement.setString(1, username);
            ResultSet rs = checkStatement.executeQuery();
            while (rs.next()) {
                if (rs.getString("email").equals(email) && rs.getString("securityanswer").equals(seq)) {
                    return true;

                }

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }
        return false;
    }

    public void changeUsersEmail(String user, String email) {
        try {
            PreparedStatement createStatement = c.prepareStatement("UPDATE users SET email = ? WHERE username = ?;");
            createStatement.setString(1, email);
            createStatement.setString(2, user);
            createStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public ArrayList<String> getOrderHistory() throws SQLException {
        ArrayList<String> history = new ArrayList<>();
            Statement statement = c.createStatement(); // kommer ut : dateoforder, username, idreciept, songname
            String sql = "select  dateoforder,users.username,idreciept,songs.songname from orders inner join users on users_idusers=users.idusers \n" +
                    "inner join orders_has_songs on reciept_idreciept=orders.idreciept inner join songs on songs_idsongs=songs.idsongs;";
            ResultSet rs = statement.executeQuery(sql);


            while (rs.next()) {
                System.out.println("add row");
                history.add("Date: " + rs.getDate("dateoforder") +
                        " username: " + rs.getString("username") +
                        " Kvittonummer: " + rs.getInt("idreciept") +
                        " song: " + rs.getString("songname"));
            }



        for (String s : history) {

            System.out.println(s);

        }

        return history;


    }
}