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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PlaylistController implements Initializable {
    DatabaseManager db = DatabaseManager.getInstance();
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

    public void changetoMainmenu(javafx.event.ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/mainMenu.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (NullPointerException ne){

            ne.getSuppressed();

        } catch (IOException e) {

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
public void deletePlaylist(ActionEvent event){
    Song selectedItem = tableAdded.getSelectionModel().getSelectedItem();
    tableAdded.requestFocus();
    tableAdded.getItems().remove(selectedItem);
}
public void clearPlaylist(ActionEvent event){
    tableAdded.requestFocus();
    tableAdded.getItems().clear();


    }
}