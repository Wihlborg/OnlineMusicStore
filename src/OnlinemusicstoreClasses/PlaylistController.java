package OnlinemusicstoreClasses;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PlaylistController implements Initializable {
    ShoppingCart gc = ShoppingCart.getInstance();
    @FXML
    private TableView<Song> table,table1;
    @FXML
    TableColumn<Song, String> Song;
    @FXML
    TableColumn<Song, String> Artist;
    @FXML
    TableColumn<Song, String> Album;
    @FXML
    TableColumn<Song, String> Song1;
    @FXML
    TableColumn<Song, String> Artist1;
    @FXML
    TableColumn<Song, String> Album1;


    private ObservableList<Song> songs;
    public void showPlaylist(){



        songs = gc.getSongLinkedList();

        table.setItems(songs);
    }
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
        Album.setText("Album");
        Artist.setText("Artist");

        Song1.setText("Song");
        Album1.setText("Album");
        Artist1.setText("Artist");

        Song.setCellValueFactory(new PropertyValueFactory<Song, String>("songName"));
        Artist.setCellValueFactory(new PropertyValueFactory<Song, String>("artistName"));
        Album.setCellValueFactory(new PropertyValueFactory<Song, String>("albumName"));
        Song1.setCellValueFactory(new PropertyValueFactory<Song, String>("songName"));
        Artist1.setCellValueFactory(new PropertyValueFactory<Song, String>("artistName"));
        Album1.setCellValueFactory(new PropertyValueFactory<Song, String>("albumName"));




    }
}
