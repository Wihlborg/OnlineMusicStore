package OnlinemusicstoreClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
/*
public class LostPasswordScene {


    @FXML
    private TextField UserNameLostPassword;

    @FXML
    private TextField emailLostPassword;


    @FXML
    void OkLostpassword(ActionEvent event) {


        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/LostPasswordScene.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (NullPointerException ne) {

            ne.getSuppressed();

        } catch (IOException e) {

            e.printStackTrace();

        }

        if (UserNameLostPassword.getText().isEmpty() || UserNameLostPassword.getText().isEmpty()) {
            Alert emptyAlert = new Alert(Alert.AlertType.ERROR, "Field must not be empty");
            emptyAlert.show();
        } else {
            String username = UserNameLostPassword.getText();
            String password = emailLostPassword.getText();


            LostPasswordScene. (username, password);
            SignUpController c2 = new SignUpController();
            c2.changeToLogin(event);
        }

    }

*/

