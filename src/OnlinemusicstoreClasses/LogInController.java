package OnlinemusicstoreClasses;

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

    @FXML
    TextField PassWord;





DatabaseManager db = DatabaseManager.getInstance();

    public void signInAction(javafx.event.ActionEvent event){
        boolean password=db.passwordCheck(UserName.getText().trim() , PassWord.getText().trim()); //Get a boolean from the DB to check if password is correct for the username


        if (password==true){ //if it is, go to main menu and save the current users data in a singleton class
            db.updateCurrentUser(UserName.getText().trim());
            LogInController l=new LogInController();
            l.changeToMainMenu(event);


        }
        else if (password==false){ //else we show an error message
            Alert wrongPasswordAlert = new Alert(Alert.AlertType.ERROR, "Wrong username or password");
            wrongPasswordAlert.show();
            PassWord.clear();
        }
   }

    @FXML
public void changeToCreateNewUser(javafx.event.ActionEvent event){
    try {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/sampleTwo.fxml"));
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
public void changeToMainMenu(ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/mainMenu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (Exception e){
            e.printStackTrace();
        }
}
@FXML
    public void changeToLostPassword(ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/LostPasswordScene.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onEnter(ActionEvent ae){
        signInAction(ae);
    }

    @FXML
    void helpMenuPressed(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText("Information about the login screen. ");
        alert.setContentText("If you don't already have an account you can create one by pressing the \"Sign up\" button." +
                "This is necessary for logging in. If you can't remember your password you can get a new one by pressing the " +
                "\"Forgot password\" button and then follow the instructions. ");

        alert.showAndWait();

    }


}




