package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class ControllerTwo {

@FXML TextField Account;
@FXML TextField Password;
@FXML TextField securityq;

DatabaseManager db= DatabaseManager.getInstance();

public void getNewAccount(javafx.event.ActionEvent event){
    if (Account.getText().isEmpty() || Password.getText().isEmpty()){
        Alert emptyAlert = new Alert(Alert.AlertType.ERROR, "Field must not be empty");
        emptyAlert.show();
    } else {
        String username = Account.getText();
        String password = Password.getText();
        String seq = securityq.getText();

        db.addAccount(username, password);
        ControllerTwo c2=new ControllerTwo();
        c2.ChangeToLogin(event);
    }

}

    public void ChangeToLogin(javafx.event.ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
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
