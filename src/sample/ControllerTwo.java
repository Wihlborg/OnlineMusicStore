package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
public class ControllerTwo {

@FXML TextField Account;
@FXML TextField Password;
@FXML TextField securityq;

public void getNewAccount(javafx.event.ActionEvent event){
String accoount=Account.getText();
String password=Password.getText();
String seq=securityq.getText();
Password password1=new Password();
password1.passwordEncryptor(accoount,password);
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
