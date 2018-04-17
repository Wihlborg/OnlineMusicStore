package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    String username=Account.getText();
    String password=Password.getText();
    String seq=securityq.getText();
    db.addAccount(username,password);


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
