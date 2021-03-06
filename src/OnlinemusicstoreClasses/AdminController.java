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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    ObservableList<User> users;
    DatabaseManager db = DatabaseManager.getInstance();

    @FXML
    TableView<User> usersTableView;

    @FXML
    TableColumn<User, Integer> idColumn;

    @FXML
    TableColumn<User, String> usernameColumn;

    @FXML
    TableColumn<User, Integer> adminColumn;

    @FXML
    TableColumn<User, Integer> artistColumn;

    @FXML
    TableColumn<User, String> emailColumn;

    @FXML
    TableColumn<User, String> answerColumn;

    @FXML
    Button banButton;

    @FXML
    Button setArtistButton;

    @FXML
    Button setAdminButton;

    @FXML
    Button searchButton;

    @FXML
    TextField searchField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchForUsers();
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

    @FXML
    private void banUser(){
        User chosenUser = usersTableView.getSelectionModel().getSelectedItem();
        db.banUser(chosenUser.getUserId());
        searchForUsers();
    }

    @FXML
    private void setAdmin(){
        User chosenUser = usersTableView.getSelectionModel().getSelectedItem();
        db.setAdmin(chosenUser.getUserId());
        searchForUsers();
    }

    @FXML
    private void setArtist(){
        User chosenUser = usersTableView.getSelectionModel().getSelectedItem();
        db.setArtist(chosenUser.getUserId());
        searchForUsers();
    }


    @FXML
    private void searchForUsers(){
        String searchTerm = searchField.getText().trim();
        this.users = FXCollections.observableArrayList(db.getUsers(searchTerm));

        usersTableView.setItems(users);
        idColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("userId"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        adminColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("admin"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("artist"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        answerColumn.setCellValueFactory(new PropertyValueFactory<User, String>("securityAnswer"));
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
