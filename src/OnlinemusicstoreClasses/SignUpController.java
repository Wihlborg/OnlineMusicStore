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

public class SignUpController {

    @FXML
    TextField Account;
    @FXML
    TextField Password;
    @FXML
    TextField securityq;
    @FXML
    TextField emailField;

    DatabaseManager db = DatabaseManager.getInstance();

    public void getNewAccount(ActionEvent event) {
    /*If a field is empty, display an error message
    Else we call the database to create a new account
    TODO: use the security question
     */

        if (Account.getText().isEmpty() || Password.getText().isEmpty() || Password.getLength() > 9) {
            Alert emptyAlert = new Alert(Alert.AlertType.ERROR, "Field must not be empty");
            emptyAlert.show();
            if (Password.getLength() > 9) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Your password is longer than 9 digtis");
                alert.show();
                Password.clear();

            } else {
                String username = Account.getText();
                String password = Password.getText();
                String seq = securityq.getText();
                String email = emailField.getText();

                db.addAccount(username, password, email, seq);
                SignUpController c2 = new SignUpController();
                c2.changeToLogin(event);
            }

        }
    }
        @FXML
        public void changeToLogin (ActionEvent event){

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
        @FXML
        void helpMenuPressed (ActionEvent event){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Signing up");
            alert.setHeaderText("Information about signing up");
            alert.setContentText("Here you create your account by filling in the forms. When you're done click the button" +
                    "\"Save user\". After this you can log in on the login menu.");

            alert.showAndWait();

        }
    }

