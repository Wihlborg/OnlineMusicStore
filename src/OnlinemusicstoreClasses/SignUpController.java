package OnlinemusicstoreClasses;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class SignUpController {

@FXML TextField Account;
@FXML TextField Password;
@FXML TextField securityq;

DatabaseManager db= DatabaseManager.getInstance();

public void getNewAccount(javafx.event.ActionEvent event){
    /*If a field is empty, display an error message
    Else we call the database to create a new account
    TODO: use the security question
     */

    if (Account.getText().isEmpty() || Password.getText().isEmpty()){
        Alert emptyAlert = new Alert(Alert.AlertType.ERROR, "Field must not be empty");
        emptyAlert.show();
    } else {
        String username = Account.getText();
        String password = Password.getText();
        String seq = securityq.getText();

        db.addAccount(username, password);
        SignUpController c2=new SignUpController();
        c2.changeToLogin(event);
    }

}
@FXML
    public void changeToLogin(javafx.event.ActionEvent event){

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

