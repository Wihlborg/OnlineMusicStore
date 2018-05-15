package OnlinemusicstoreClasses;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

public class LostPasswordScene {
DatabaseManager db=DatabaseManager.getInstance();
    Random r = new Random();
    @FXML
    private TextField UserNameLostPassword;

    @FXML
    private TextField emailLostPassword;

    @FXML TextField sequrityQ;

    @FXML
    public void OkLostpassword(ActionEvent event) {
        if (UserNameLostPassword.getText().isEmpty() || UserNameLostPassword.getText().isEmpty())  {
            Alert emptyAlert = new Alert(Alert.AlertType.ERROR, "Field must not be empty");
            emptyAlert.show();
        } else {
            String userNameLostPassword =UserNameLostPassword.getText();
            String emailLostPasswordText = emailLostPassword.getText();
            String seq = sequrityQ.getText();

            lostPassword(event,userNameLostPassword,emailLostPasswordText,seq);
        }



    }
    @FXML
    public void lostPassword(ActionEvent event,String username,String email,String sequrityQ){

        boolean confimation=db.checkUsername(username,email,sequrityQ);


        if (confimation==true ){
            event.getSource();
            String tooMail=email;
            String fromMail="onlinemusicstorev1@gmail.com";
            String password ="9x828x5w";
            String subject="Lost Password";
<<<<<<< HEAD
            String newPassword= UUID.randomUUID().toString();
            System.out.println(UUID.randomUUID().toString());
=======
            String alphabet="123xyz";
String newPassword="";

            for (int i = 0; i < 5; i++) {
            newPassword += String.valueOf(alphabet.charAt(r.nextInt(alphabet.length())));

            }

>>>>>>> 0a633b602d8216331e04143321af680f8adf05ee
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
                        + "\n\n"+"Ur new Password is: "+newPassword);
                Transport transport =session.getTransport("smtp");
                transport.connect(host,fromMail,password);
                transport.sendMessage(message1,message1.getAllRecipients());
                transport.close();

                Alert correctWorked= new Alert(Alert.AlertType.CONFIRMATION,"SUCCESFUL,Check ur Email");
                correctWorked.show();

            } catch (MessagingException e) {

            }


        if (confimation==false){
            Alert wrongPasswordAlert = new Alert(Alert.AlertType.ERROR, "Wrong Input!");
            wrongPasswordAlert.show();
        }
        }

    }
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
    public void changeToLogin(ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/sample.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clearUserInput(ActionEvent event) {
        UserNameLostPassword.clear();
        emailLostPassword.clear();
        sequrityQ.clear();


    }


        @FXML private void handlePressButton(ActionEvent event) throws IOException {
            Node node = (Node)event.getSource();
            Stage stage = (Stage)node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/sample.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);



        }


}

