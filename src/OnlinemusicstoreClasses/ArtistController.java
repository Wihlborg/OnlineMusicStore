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
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ArtistController implements Initializable{
    @FXML
    ChoiceBox<Artist> artistChoiceBox;
    @FXML
    ChoiceBox<Album> albumChoiceBox;
    @FXML
    TextField songNameTextField;
    @FXML
    TextField priceField;


    CurrentUser cu = CurrentUser.getInstance();
    DatabaseManager db = DatabaseManager.getInstance();
    ObservableList<Artist> artists;
    ObservableList<Album> albums;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateArtistBox();
        artistChoiceBox.setOnAction(event -> updateAlbumBox());
    }

    @FXML
    public void addNewAlbum(){}

    @FXML
    public void addNewArtist(){}

    @FXML
    public void addNewSong(){
        if (songNameTextField.getText().trim().isEmpty() || priceField.getText().trim().isEmpty()){
            Alert emptyAlert = new Alert(Alert.AlertType.ERROR, "Fields must not be empty");
            emptyAlert.show();
            return;
        }

        String name;
        double price;
        try {
            price = Double.parseDouble(priceField.getText());
        } catch (NumberFormatException ex){
            Alert wrongFormatAlert = new Alert(Alert.AlertType.ERROR, "Price must be a numerical value");
            wrongFormatAlert.show();
        }
    }

    @FXML
    public void changeToMainMenu(ActionEvent event){
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

    public void updateArtistBox(){
        artistChoiceBox.getItems().clear();
        artists = FXCollections.observableArrayList(db.getUsersArtists(cu.getUserId()));
        artistChoiceBox.getItems().addAll(artists);
    }

    public void updateAlbumBox(){
        albumChoiceBox.getItems().clear();
        Artist chosenArtist = artistChoiceBox.getValue();
        albums = FXCollections.observableArrayList(db.getArtistsAlbums(chosenArtist.getArtistId()));
        albumChoiceBox.getItems().addAll(albums);
    }
}
