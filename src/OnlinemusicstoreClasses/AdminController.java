package OnlinemusicstoreClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    }

    @FXML
    private void banUser(){
        User chosenUser = usersTableView.getSelectionModel().getSelectedItem();
        db.banUser(chosenUser.getUserId());
    }

    @FXML
    private void setAdmin(){
        User chosenUser = usersTableView.getSelectionModel().getSelectedItem();
        db.setAdmin(chosenUser.getUserId());
    }

    @FXML
    private void setArtist(){
        User chosenUser = usersTableView.getSelectionModel().getSelectedItem();
        db.setArtist(chosenUser.getUserId());
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
}
