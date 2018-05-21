package OnlinemusicstoreClasses;
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


public class ShowAccountData  {
    @FXML TextField usernameforChP;
    @FXML TextField emailforChP;
    @FXML TextField usernameForChangeemail;
    @FXML TextField passforChemail;
    @FXML TextField sq;
    @FXML TextField changepass;
    @FXML TextField changeEmail;


    DatabaseManager db = DatabaseManager.getInstance();


@FXML
public void changeEmail(ActionEvent event){

if (usernameForChangeemail.getText().isEmpty() || passforChemail.getText().isEmpty()){
        Alert emptyAlert = new Alert(Alert.AlertType.ERROR, "Field must not be empty");
        emptyAlert.show();

    } else {
        String userNameLostPassword =usernameForChangeemail.getText();
        String emailLostPasswordText = emailforChP.getText();
        String seq = sq.getText();
    boolean check=db.emailCheck(userNameLostPassword,emailLostPasswordText,seq);
        if(check==true){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"You have now changed ur Email");
            alert.show();

       String email=changeEmail.getText();
db.changeUsersEmail(userNameLostPassword,email);
            changetologin(event);

    }

    }


}
@FXML
public void changePassword(ActionEvent event) {
    if (usernameforChP.getText().isEmpty() || emailforChP.getText().isEmpty()) {
        Alert emptyAlert = new Alert(Alert.AlertType.ERROR, "Field must not be empty");
        emptyAlert.show();

    } else {
        boolean checkifcorrect;
        String usernamePass = usernameforChP.getText();
        String email = emailforChP.getText();
        String sqpass = sq.getText();
        checkifcorrect = db.checkUsername(usernamePass, email, sqpass);
        if (checkifcorrect == true) {
            String changePassword = changepass.getText();
            db.changeUsersPassword(usernamePass, changePassword);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"You have now changed ur password");
            alert.show();
            changetologin(event);
        } else {
            Alert emptyAlert = new Alert(Alert.AlertType.ERROR, "Field must not be empty");
            emptyAlert.show();


        }
    }
}
    public void changetomainmenu(javafx.event.ActionEvent event){
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
    public void changetologin(javafx.event.ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/sample.fxml"));
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







