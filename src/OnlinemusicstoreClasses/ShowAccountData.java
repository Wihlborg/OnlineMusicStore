package OnlinemusicstoreClasses;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ShowAccountData {

    DatabaseManager db = DatabaseManager.getInstance();
    CurrentUser cu = CurrentUser.getInstance();


    @FXML
    private TableView<?> usersTableView;

    @FXML
    private TableColumn<?, ?> password;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private Button changePassword;

    @FXML
    private Button changeEmail;


    Alert wrongFormatAlert = new Alert(Alert.AlertType.ERROR, "Enter a new e-mail");
    Alert fileNotFoundAlert = new Alert(Alert.AlertType.ERROR, "Enter new password");

/*
    @FXML
    private void setPassword(ActionEvent event){
        password password = Password.getSelectionModel().getSelectedItem();
        db.set(password.);

    }


    @FXML
    public void setEmailColumn(ActionEvent event) {

        if (emailColumn.getText().trim().isEmpty() || emailColumn.getText().trim().isEmpty()) {
            emailColumn.show();
            return;
        }
    }
    /*

     */

    @FXML
    private void ChangeToMainMenu(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/sample.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);


    }


    @FXML
    void logOut(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/sample.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (NullPointerException ne) {

            ne.getSuppressed();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}