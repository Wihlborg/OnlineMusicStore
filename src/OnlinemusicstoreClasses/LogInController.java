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

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;


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
public void lostPassword(ActionEvent event,String username,String email,String sequrityQ){

    boolean userName=db.checkUsername(username);
    boolean seq=false;

        if (userName==true &&seq==true ){
        event.getSource();
        String tooMail=email;
        String fromMail="onlinemusicstorev1@gmail.com";
        String password ="9x828x5w";
        String subject="Lost Password";
        String newPassword=UUID.randomUUID().toString();
        //linka message så databasen uppdateras med nya passet.
            db.changeUsersPassword(username,newPassword);
        String host = "smtp.gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.user",fromMail);
        props.put("mail.smtp.password",password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(props);
        try {
            Message message1 = new MimeMessage(session);
            message1.setFrom(new InternetAddress(fromMail));
            message1.setRecipient(Message.RecipientType.TO,new InternetAddress(tooMail));
            message1.setSubject(subject);
            message1.setText("Dear Customer,"
                    + "\n\n"+newPassword);
            Transport transport =session.getTransport("smtp");
            transport.connect(host,fromMail,password);
            transport.sendMessage(message1,message1.getAllRecipients());
            transport.close();
            System.out.println("Done");

        } catch (MessagingException e) {

        }
    }
    else if (userName==false){
        Alert wrongPasswordAlert = new Alert(Alert.AlertType.ERROR, "Username Dont Exist");
    wrongPasswordAlert.show();
    }
        else if (seq==false){
            Alert wrongPasswordAlert = new Alert(Alert.AlertType.ERROR, "Sequrity Question Dont Match");
            wrongPasswordAlert.show();
        }

}
@FXML
    public void changeToLostPassword(javafx.event.ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/LostPasswordScene.fxml"));
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


