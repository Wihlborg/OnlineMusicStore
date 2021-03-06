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
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Orderhistorik implements Initializable {


    @FXML
    private Button backBtn;

    @FXML
    private Button showHistoryBtn;

    @FXML
    private TextArea orderhistoryTA;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DatabaseManager db = DatabaseManager.getInstance();


    }

    public void showHistory(ActionEvent event) {

        try {
            ArrayList<String> history = DatabaseManager.getInstance().getOrderHistory();

            for (String s : history) {
                orderhistoryTA.appendText(s + "\n\r");
            }

        } catch (NullPointerException ne) {

            ne.getSuppressed();

        } catch (SQLException sqlEX) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("History");
            alert.setHeaderText("Information about your history");
            alert.setContentText("Something went wrong please restart the application");
            alert.showAndWait();
        }
    }


    public void backBtn(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/Playlist.fxml"));
            Parent root = loader.load();
            /*SearchController searchController = loader.getController();
            searchController.search("hello");   task 3 exam*/
            Scene scene = new Scene(root);
            stage.setScene(scene);


        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}


