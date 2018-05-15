package OnlinemusicstoreClasses;

import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
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

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShoppingCartGui implements Initializable{
    ShoppingCart fc = ShoppingCart.getInstance();
    @FXML
    private TableView<Song> table;
    @FXML
    TableColumn<Song, String> columnSong;
    @FXML
    TableColumn<Song, String> columnArtist;
    @FXML
    TableColumn<Song, String> columnAlbum;
    @FXML TableColumn<Song,Double>columnPrice;
    private ObservableList<Song> songs;


@FXML private TextField totalCost;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnSong.setText("Song");
        columnAlbum.setText("Album");
        columnArtist.setText("Artist");
        columnPrice.setText("Price");

        songs = fc.getSongLinkedList();

        columnSong.setCellValueFactory(new PropertyValueFactory<Song, String>("songName"));
        columnArtist.setCellValueFactory(new PropertyValueFactory<Song, String>("artistName"));
        columnAlbum.setCellValueFactory(new PropertyValueFactory<Song, String>("albumName"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Song, Double>("songPrice"));
        table.setItems(songs);

        updateTotalCost();






}





    public void changeToMenu(javafx.event.ActionEvent event){



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
    @FXML
    void checkoutMenu(ActionEvent event) {


        if (songs.isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR,"You dont have items in your shoppingcart");
            alert.show();
        }




        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/Checkout.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (NullPointerException ne) {

            ne.getSuppressed();

        } catch (IOException e) {

            e.printStackTrace();

        }







    }
@FXML
    public void deleteAll(){

            table.requestFocus();
            table.getItems().clear();

   totalCost.clear();
    }

@FXML
    public void remove(){

            Song selectedItem = table.getSelectionModel().getSelectedItem();
            table.requestFocus();
            table.getItems().remove(selectedItem);

            updateTotalCost();
    if(songs.size()<=0){
        totalCost.clear();
    }
    }

        public void updateTotalCost(){

            double total = 0 ;
            for (Song song : table.getItems()) {
                total = total +song.getSongPrice() ;
            }
            totalCost.setText(String.valueOf(total));
        }
    @FXML
    void helpMenuPressed(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Shopping cart");
        alert.setHeaderText("How to use the shopping cart. ");
        alert.setContentText("Here you can see the songs added to your cart. You can remove unwanted songs using the button" +
                "\"Remove\" or you can remove all songs from the cart by pressing \"Clear all\". ");

        alert.showAndWait();

    }
}
