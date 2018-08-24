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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;


public class PlaylistController implements Initializable {
    DatabaseManager db = DatabaseManager.getInstance();

    @FXML
    private Button showHistory;

    @FXML
    private TableView<Song> StartTable;
     @FXML
    private  TableView<Song> tableAdded;
    @FXML
    TableColumn<Song, String> Song;
    @FXML
    TableColumn<Song, String> Artist;
    @FXML
    TableColumn<Song, String> Album;
    @FXML
    TableColumn<Song, String> SongInPlaylist;
    @FXML
    TableColumn<Song, String> ArtistInPlaylist;
    @FXML
    TableColumn<Song, String> AlbumInPlaylist;
    ObservableList<Song>playlistSongs=FXCollections.observableArrayList();
    CurrentUser cu = CurrentUser.getInstance();
    private ObservableList<Song> boughtSongs = FXCollections.observableArrayList(db.getBoughtSongs(cu.getUserId()));;

    public void changetoMainmenu(javafx.event.ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/mainMenu.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (NullPointerException ne) {

            ne.getSuppressed();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

        public void showHistory(ActionEvent event) {
            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/orderhistory.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (Exception e){
                e.printStackTrace();
            }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Song.setText("Song");
        Artist.setText("Artist");
        Album.setText("Album");


        SongInPlaylist.setText("Song");
        ArtistInPlaylist.setText("Artist");
        AlbumInPlaylist.setText("Album");


        StartTable.setItems(boughtSongs);

        Song.setCellValueFactory(new PropertyValueFactory<Song, String>("songName"));
        Artist.setCellValueFactory(new PropertyValueFactory<Song, String>("artistName"));
        Album.setCellValueFactory(new PropertyValueFactory<Song, String>("albumName"));
        addToPlaylist();
        tableAdded.setItems(playlistSongs);

        SongInPlaylist.setCellValueFactory(new PropertyValueFactory<Song, String>("songName"));
        ArtistInPlaylist.setCellValueFactory(new PropertyValueFactory<Song, String>("albumName"));
        AlbumInPlaylist.setCellValueFactory(new PropertyValueFactory<Song, String>("artistName"));




    }
    //checks if the selected song is in the playlist table. If it is dont add to playlistSongs. If it itsnt add to playlistSongs.
    @FXML
    public void addToPlaylist(){
        StartTable.setRowFactory( tableView -> {
            TableRow<Song> row = new TableRow<>();

            row.setOnMouseClicked(event -> {

                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Song rowData = row.getItem();
                    System.out.println(rowData);

                    boolean songIsInList = false;


                    for (int i = 0; i < playlistSongs.size(); i++){
                        if (playlistSongs.get(i).equals(rowData)){
                            songIsInList = true;
                            break;
                        }
                    }
                    if (!songIsInList){
                        playlistSongs.add(rowData);
                    }
                }

            });
            return row;
            });


}

    public void playSong(ActionEvent event){
        Song songToPlay = tableAdded.getSelectionModel().getSelectedItem();
        if (songToPlay != null) {
            try {
                db.getMusicFile(songToPlay.getSongId());
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("song.wav"));
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } catch (UnsupportedAudioFileException ex) {
                System.out.println("File not found");
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (LineUnavailableException lex) {
                lex.printStackTrace();
            }
        }
    }
public void deletePlaylist(ActionEvent event){
    Song selectedItem = tableAdded.getSelectionModel().getSelectedItem();
    tableAdded.requestFocus();
    tableAdded.getItems().remove(selectedItem);
}
public void clearPlaylist(ActionEvent event){
    tableAdded.requestFocus();
    tableAdded.getItems().clear();


    }
    @FXML
    void helpMenuPressed(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information about searching");
        alert.setHeaderText("Searching");
        alert.setContentText("Here you can search for songs which you can add to your cart. Simply double-click" +
                "on whichever song you want to add to your cart.");

        alert.showAndWait();

    }
    //created by jimmie
    @FXML
    void createPlaylist(ActionEvent event) {

        ObservableList<Song> songsObs = FXCollections.observableArrayList();
        songsObs = tableAdded.getItems();
        PlaylistSingleton pS = PlaylistSingleton.getInstance();



        //This array was neccessary because otherwise the songs would get lost when clearing the playlistTableView.
        ArrayList<Song> sonsss = new ArrayList<>(tableAdded.getItems());

        //a conversion from the sonss arraylist was also necessary as an observable arraylist is sent as an argument
        //to the setItems() method in the playlistSingleton.
        ObservableList<Song> ss = FXCollections.observableArrayList(sonsss);

        if (sonsss.size()==0) {
            System.out.println("Observable: " + ss);
            Alert alertBox = new Alert(Alert.AlertType.INFORMATION);
            alertBox.setHeaderText("Error");
            alertBox.setContentText("You must choose some songs before creating a playlist");
            alertBox.showAndWait();
        }
        //if arraylist is not empty (there are items in the playlistTableView) send the observable arraylist to playlist singleton
        if (sonsss.size()>0) {

            pS.setPlaylist(ss);
            System.out.println("Song added to playlist"+sonsss);
            System.out.println("ss Observable list before deletion: "+songsObs);
            sonsss.clear();

            tableAdded.getItems().clear();
            System.out.println("ss Observable list after deletion deletion: "+songsObs);



        }

    }

    @FXML
    void viewPlaylists(ActionEvent event) {
        PlaylistSingleton playlistSingleton = PlaylistSingleton.getInstance();

        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/userPlaylists.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (Exception e){
            e.printStackTrace();
        }

        //System.out.println(playlistSingleton.getPlaylist());
    }

}