package OnlinemusicstoreClasses;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CheckoutController implements Initializable{

    @FXML
     TextField nameColumn;

    @FXML
     TextField lastname;

    @FXML
     Button creditcardnr1;
    @FXML
     Button creditcardnr2;
    @FXML
     Button creditcardnr3;
    @FXML
     Button date;
    @FXML
     Button cvc;
    @FXML
     Button totalamount;



    ShoppingCart fc = ShoppingCart.getInstance();
    @FXML
    private TableView<Song> table;
    @FXML
    TableColumn<Song, String> columnSong;
    @FXML
    TableColumn<Song, String> columnArtist;
    @FXML
    TableColumn<Song, String> columnAlbum;
    @FXML TableColumn<Song,Double> columnPrice;
    private ObservableList<Song> songs;

    @FXML
    void helpButtonPressed(ActionEvent event) {

    }




    @FXML
    void switchToShop(ActionEvent event) {



            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/ShoppingCart.fxml"));
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



        columnSong.setText("Song");
        columnAlbum.setText("Album");
        columnArtist.setText("Artist");
        columnPrice.setText("Price");

        songs=fc.getSongLinkedList();

        columnSong.setCellValueFactory(new PropertyValueFactory<Song, String>("SongName"));
        columnArtist.setCellValueFactory(new PropertyValueFactory<Song, String>("ArtistName"));
        columnAlbum.setCellValueFactory(new PropertyValueFactory<Song, String>("AlbumName"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Song, Double>("songPrice"));
        table.setItems(songs);


    }
    public void baughtItems(){

        double total = 0 ;
        for (Song song : table.getItems()) {
            total=song.getSongPrice();
            totalamount.setText(String.valueOf(total));
        }


        String name=nameColumn.getText();
        String lastna=lastname.getText();
        int creditnbr1= Integer.parseInt(creditcardnr1.getText());
        int creditnbr2=Integer.parseInt(creditcardnr2.getText());
        int creditnbr3=Integer.parseInt(creditcardnr3.getText());
        int dates=Integer.parseInt(date.getText());
        int cvcnbr=Integer.parseInt(cvc.getText());
        double amountPrice=Integer.parseInt(totalamount.getText());

    }
}
