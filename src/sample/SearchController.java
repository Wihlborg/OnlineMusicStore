package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class SearchController {
    private ArrayList<Song> songs = new ArrayList<>();
    private ArrayList<Album> albums = new ArrayList<>();
    private DatabaseManager dm = DatabaseManager.getInstance();


@FXML  TextField searchtextField;
@FXML  TextArea textsearchShow;



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
    @FXML
    public void searchFunction(ActionEvent event){

        String searchD = searchtextField.getText().toString();

                search(searchD);
    }

    @FXML
    public void showSearch(){

        

    }

}
