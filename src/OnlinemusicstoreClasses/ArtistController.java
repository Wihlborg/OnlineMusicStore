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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ArtistController implements Initializable{
    @FXML ChoiceBox<Artist> artistChoiceBox;
    @FXML ChoiceBox<Album> albumChoiceBox;
    @FXML TextField songNameTextField;
    @FXML TextField priceField;
    @FXML TextField albumTextField;
    @FXML TextField albumPriceField;
    @FXML TextField artistTextField;
    @FXML Label informationLabel;


    CurrentUser cu = CurrentUser.getInstance();
    DatabaseManager db = DatabaseManager.getInstance();
    ObservableList<Artist> artists;
    ObservableList<Album> albums;

    Alert emptyAlert = new Alert(Alert.AlertType.ERROR, "Fields must not be empty");
    Alert wrongFormatAlert = new Alert(Alert.AlertType.ERROR, "Price must be a numerical value");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateArtistBox();
        artistChoiceBox.setOnAction(event -> updateAlbumBox());
    }

    @FXML
    public void addNewArtist(){
        if (artistTextField.getText().trim().isEmpty()){
            emptyAlert.show();
            clearAll();
            return;
        }
        String artistName = artistTextField.getText();
        int userId = cu.getUserId();
        db.addArtist(artistName, userId);
        clearAll();
        informationLabel.setText("Artist added successfully!");
        updateArtistBox();
    }

    @FXML
    public void addNewAlbum(){
        if (albumTextField.getText().trim().isEmpty() || albumPriceField.getText().trim().isEmpty() || artistChoiceBox.getValue() == null){
            emptyAlert.show();
            clearAll();
            return;
        }
        Artist artist = artistChoiceBox.getValue();
        String albumName = albumTextField.getText();
        double price;
        try {
            price = Double.parseDouble(albumPriceField.getText());
        } catch (NumberFormatException ex){
            wrongFormatAlert.show();
            clearAll();
            return;
        }

        db.addAlbum(albumName, artist.getArtistId(), price);
        clearAll();
        informationLabel.setText("Album added successfully!");
        updateAlbumBox();
    }



    @FXML
    public void addNewSong(){
        if (songNameTextField.getText().trim().isEmpty() || priceField.getText().trim().isEmpty() || artistChoiceBox.getValue() == null || albumChoiceBox.getValue() == null){
            emptyAlert.show();
            clearAll();
            return;
        }
        Album album = albumChoiceBox.getValue();
        String name = songNameTextField.getText();
        double price;
        try {
            price = Double.parseDouble(priceField.getText());
        } catch (NumberFormatException ex){
            wrongFormatAlert.show();
            clearAll();
            return;
        }

        db.addSong(name, album.getAlbumId(), price);
        clearAll();
        informationLabel.setText("Song successfully added!");
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

    public void clearAll(){
        songNameTextField.clear();
        priceField.clear();
        albumTextField.clear();
        albumPriceField.clear();
        artistTextField.clear();
        informationLabel.setText("");
    }
}
