package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Stack;


public class SearchController implements Initializable{
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        searchtextField.setOnKeyTyped(event -> {
            searchtextField.requestFocus();
            String searchValue = searchtextField.getText();
            search(searchValue);
        });

        table.setRowFactory( tableView -> {
            TableRow<Song> row = new TableRow<>();

                row.setOnMouseClicked(event -> {

                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {

                                Song rowData = row.getItem();
                                ObservableList scList = sc.getSongLinkedList();
                                boolean songIsInList = false;

                                //Go through shoppingcart to see if we alreade have the song
                                for (int i = 0; i < scList.size(); i++){
                                    if (scList.get(i) == rowData){
                                        songIsInList = true;
                                    }
                                }
                                //If it doesn't exist in the cart we add it, else nothing happens
                                if (!songIsInList){
                                    sc.addSong(rowData);
                                }
                }



            });
            return row ;
        });
    }


    public void search(String search){
        songs = FXCollections.observableArrayList(dm.getSongs(search));
        albums = dm.getAlbums(search);
        table.setItems(songs);
        columnSong.setCellValueFactory(new PropertyValueFactory<Song, String>("songName"));
        columnArtist.setCellValueFactory(new PropertyValueFactory<Song, String>("artistName"));
        columnAlbum.setCellValueFactory(new PropertyValueFactory<Song, String>("albumName"));
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



}






