package OnlinemusicstoreClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    Button adminButton;
    @FXML
    Button addMusicButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CurrentUser cu = CurrentUser.getInstance();
        if (!cu.isAdmin()){
            adminButton.setVisible(false);
            if (!cu.isArtist()){
                addMusicButton.setVisible(false);
            }
        }

    }

    public void changeToShopcart(javafx.event.ActionEvent event){
        try {
            System.out.println("Hello world");
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
    public void changeToAdvancedSearch(javafx.event.ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/Search.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (NullPointerException ne){

            ne.getSuppressed();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
    public void changeToPlaylist(javafx.event.ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/Playlist.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (NullPointerException ne){
            ne.getSuppressed();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void logout(javafx.event.ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/sample.fxml"));
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
    public void changeToAdmin(ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/Admin.fxml"));
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
    public void changeToArtist(ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/Artist.fxml"));
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
    void helpButtonPressed(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Main menu");
        alert.setHeaderText("Information about the main menu. ");
        alert.setContentText("This is the main menu. To the left is the top playlist of the week, which is a " +
                "playlist recommended by the Admin. Use the buttons on the bottom of the screen to navigate around the" +
                "application.");

        alert.showAndWait();

    }


}
