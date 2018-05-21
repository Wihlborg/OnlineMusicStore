package OnlinemusicstoreClasses;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class ShowAccountData  {
    @FXML TextField usernameforChP;
    @FXML TextField emailforChP;
    @FXML TextField usernameForChangeemail;
    @FXML TextField passforChemail;
    @FXML TextField sq;
    @FXML TextField changepass;


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

    }
}

    String username= usernameForChangeemail.getText();
    String password=passforChemail.getText();
    String sqemail=sq.getText();




@FXML
public void changePassword(ActionEvent event) {
    if (usernameforChP.getText().isEmpty() || emailforChP.getText().isEmpty()) {
        Alert emptyAlert = new Alert(Alert.AlertType.ERROR, "Field must not be empty");
        emptyAlert.show();

    }

else{
    boolean checkifcorrect;
    String usernamePass=usernameforChP.getText();
    String email=emailforChP.getText();
    String sqpass=sq.getText();
     checkifcorrect = db.checkUsername(usernamePass,email,sqpass);
if (checkifcorrect==true){
    String changePassword=changepass.getText();
    db.changeUsersPassword(usernamePass,changePassword);
}
else {
    Alert emptyAlert = new Alert(Alert.AlertType.ERROR, "Field must not be empty");
    emptyAlert.show();



}
}

}
}
