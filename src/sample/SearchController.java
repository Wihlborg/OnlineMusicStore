package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SearchController {
    private ArrayList<Song> songs = new ArrayList<>();
    private ArrayList<Album> albums = new ArrayList<>();
    private DatabaseManager dm = DatabaseManager.getInstance();

    public void search(String search){
        songs = dm.getSongs(search);
        albums = dm.getAlbums(search);
    }

    public void addSongToCart(Song song){

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
