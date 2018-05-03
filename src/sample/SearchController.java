package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.text.TabableView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class SearchController{
    private ObservableList<Song> songs;
    private ArrayList<Album> albums;
    private DatabaseManager dm = DatabaseManager.getInstance();


@FXML  TextField searchtextField;
@FXML
private TableView<Song> table;
@FXML
    TableColumn<Song, String> columnSong;
    @FXML
    TableColumn<Song, String> columnArtist;
    @FXML
    TableColumn<Song, String> columnAlbum;

    ShoppingCart sc = ShoppingCart.getInstance();


public void search(String search){
        songs = FXCollections.observableArrayList(dm.getSongs(search));
        albums = dm.getAlbums(search);
        table.setItems(songs);
        columnSong.setCellValueFactory(new PropertyValueFactory<Song, String>("songName"));
        columnArtist.setCellValueFactory(new PropertyValueFactory<Song, String>("artistName"));
        columnAlbum.setCellValueFactory(new PropertyValueFactory<Song, String>("albumName"));

    table.setRowFactory( tv -> {
        TableRow<Song> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                Song rowData = row.getItem();
                sc.addSong(rowData);
            }
        });
        return row ;
    });

}



    public void changeToMenu(javafx.event.ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (NullPointerException ne){

            ne.getSuppressed();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
    @FXML
    public void searchFunction(ActionEvent event){

        String searchD = searchtextField.getText().toString();

                search(searchD);


    }



}
