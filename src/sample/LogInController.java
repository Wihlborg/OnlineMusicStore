package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;


public class LogInController {


    @FXML
    TextField UserName;
@FXML
    Button Signin;

    @FXML TextField PassWord;





DatabaseManager db = DatabaseManager.getInstance();

    public void signInAction(javafx.event.ActionEvent event){
        boolean password=db.passwordCheck(UserName.getText().trim() , PassWord.getText().trim());
        if (password==true){
            db.updateCurrentUser(UserName.getText().trim());
            LogInController l=new LogInController();
            l.changeToMainMenu(event);
        }
        else if (password==false){
            Alert wrongPasswordAlert = new Alert(Alert.AlertType.ERROR, "Wrong username or password");
            wrongPasswordAlert.show();
            PassWord.clear();
        }

    }

public void changeToCreateNewUser(javafx.event.ActionEvent event){
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

public void changeToMainMenu(ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (Exception e){
            e.printStackTrace();
        }
}


}
