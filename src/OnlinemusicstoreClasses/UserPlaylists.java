package OnlinemusicstoreClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class UserPlaylists implements Initializable {

    @FXML
    private TableView<PlaylistName> playlistTableView;

    @FXML
    private TableView<Song> playlistSongsTableView;

    @FXML
    private TableColumn<PlaylistName, Integer> playlistNameColumn;

    @FXML
    private TableColumn<Song, String> songColumn;

    @FXML
    private TableColumn<Song, String> artistColumn;

    @FXML
    private TableColumn<Song, String> albumColumn;

    private ObservableList<Song> songs = FXCollections.observableArrayList();
    private ObservableList<PlaylistName> playlistName = FXCollections.observableArrayList();
    private HashMap<Integer, ObservableList<Song>> playlist = new HashMap<>();
    private List<Song> songss =new ArrayList<>();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PlaylistSingleton pS = PlaylistSingleton.getInstance();
        playlist = pS.getPlaylist();

        playlistNameColumn.setText("Playlists");
        songColumn.setText("Song");
        artistColumn.setText("Artist");
        albumColumn.setText("Album");


        songColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("songName"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("artistName"));
        albumColumn.setCellValueFactory(new PropertyValueFactory<Song, String>("albumName"));


        for (int i = 0; i<playlist.size(); i++){
            songs.addAll(playlist.get(i));

        }

        System.out.println(songss);


     //   for (int i = 0; i<playlist.size(); i++){
     //       Song songss = (Song)playlist.get(i);
     //       songs.add(songss);
       //     playlistTableView.setItems(songs);
    //    }




    }

    @FXML
    void goBack(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/Playlist.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void setPlaylists(){
        PlaylistSingleton pS = PlaylistSingleton.getInstance();
        playlist = pS.getPlaylist();
        for (int i =1; i<=playlist.size(); i++) {
            playlistName.add(new PlaylistName(i));

            playlistTableView.setItems(playlistName);
            playlistNameColumn.setCellValueFactory(new PropertyValueFactory<PlaylistName, Integer>("playlistNumber"));
        }


    }

    @FXML
    void removePlaylist(ActionEvent event) {
        PlaylistSingleton pS = PlaylistSingleton.getInstance();
        playlist = pS.getPlaylist();

        setPlaylists();



    }


}
