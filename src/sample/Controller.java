package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class Controller {


    @FXML
    TextField UserName;
@FXML
    Button Signin;

    @FXML TextField PassWord;



DatabaseManager db= DatabaseManager.getInstance();

    public void SigninAction(javafx.event.ActionEvent event){
boolean password=db.passwordCheck(UserName.getText(),PassWord.getText());
        if (password==true){

            System.out.println("login");
        }
       else if (password==false){
            System.out.println("error");
        }

    }

public void ChangeToCreateNewUser (javafx.event.ActionEvent event){
    try {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sampleTwo.fxml"));
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
