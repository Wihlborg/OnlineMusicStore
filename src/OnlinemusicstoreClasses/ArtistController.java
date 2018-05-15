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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    FileChooser fileChooser = new FileChooser();

    Alert emptyAlert = new Alert(Alert.AlertType.ERROR, "Fields must not be empty");
    Alert wrongFormatAlert = new Alert(Alert.AlertType.ERROR, "Price must be a numerical value");
    Alert fileNotFoundAlert = new Alert(Alert.AlertType.ERROR, "File not found");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateArtistBox();
        artistChoiceBox.setOnAction(event -> updateAlbumBox());
        fileChooser.setTitle("Choose music file");
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
    public void addNewSong(ActionEvent event){
        //Om något av fälten är tomma visas felmeddelande och inget händer
        if (songNameTextField.getText().trim().isEmpty() || priceField.getText().trim().isEmpty() || artistChoiceBox.getValue() == null || albumChoiceBox.getValue() == null){
            emptyAlert.show();
            clearAll();
            return;
        }
        //Ta in data från fälten. Felmeddelande om priceField inte har en double.
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

        //Öppna en filechooser där användaren får välja fil (går det att kolla filtyp? vet ej)
        //Om ingen fil vals blir det felmeddelande, annars skickas datan till DBn.
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file == null){
            fileNotFoundAlert.show();
            return;
        }
        try {
            InputStream is = new FileInputStream(file);
            db.addSong(name, album.getAlbumId(), price, is);
            clearAll();
            informationLabel.setText("Song successfully added!");
        } catch (IOException ioex){
            fileNotFoundAlert.show();
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

    public void clearAll(){
        songNameTextField.clear();
        priceField.clear();
        albumTextField.clear();
        albumPriceField.clear();
        artistTextField.clear();
        informationLabel.setText("");
    }
    @FXML
    void helpMenuPressed(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!");

        alert.showAndWait();

    }
}
