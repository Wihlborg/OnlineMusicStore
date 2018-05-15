package OnlinemusicstoreClasses;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class CheckoutController implements Initializable{

    @FXML
     TextField nameField;

    @FXML
     TextField lastnameField;

    @FXML
     TextField creditcardnr1;
    @FXML
     TextField creditcardnr2;
    @FXML
     TextField creditcardnr3;
    @FXML
     TextField date;
    @FXML
     TextField cvc;
    @FXML
     TextField totalAmount;
@FXML
        Button payButton;


    ShoppingCart fc = ShoppingCart.getInstance();
    @FXML
    private TableView<Song> table;
    @FXML
    TableColumn<Song, String> columnSong;
    @FXML
    TableColumn<Song, String> columnArtist;
    @FXML
    TableColumn<Song, String> columnAlbum;
    @FXML TableColumn<Song,Double> columnPrice;
    private ObservableList<Song> songs;



    DatabaseManager db =DatabaseManager.getInstance();
    CurrentUser user=CurrentUser.getInstance();

    @FXML
    void switchToShop(ActionEvent event) {



            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/ShoppingCart.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                stage.setScene(scene);

            } catch (NullPointerException ne){

                ne.getSuppressed();

            } catch (IOException e) {

                e.printStackTrace();

            }


        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String  bajs=user.getUserName();
        System.out.println(bajs);
        System.out.println(db.getEmail(bajs));

        columnSong.setText("Song");
        columnAlbum.setText("Album");
        columnArtist.setText("Artist");
        columnPrice.setText("Price");

        songs=fc.getSongLinkedList();

        columnSong.setCellValueFactory(new PropertyValueFactory<Song, String>("SongName"));
        columnArtist.setCellValueFactory(new PropertyValueFactory<Song, String>("ArtistName"));
        columnAlbum.setCellValueFactory(new PropertyValueFactory<Song, String>("AlbumName"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Song, Double>("songPrice"));
        table.setItems(songs);

        calculateCost();
        if (nameField.getText().isEmpty() || lastnameField.getText().isEmpty()||
                creditcardnr1.getText().isEmpty() || creditcardnr2.getText().isEmpty()|| creditcardnr3.getText().isEmpty()||
                date.getText().isEmpty()|| cvc.getText().isEmpty()
                )  {
            Alert emptyAlert = new Alert(Alert.AlertType.CONFIRMATION, "Welcome To Payment!");
            emptyAlert.show();
        }




        }

    public void buyItems(ActionEvent event){
        String userName = user.getUserName();
        String name = nameField.getText();
        String lastname = lastnameField.getText();
        System.out.println("WORKED 1");
        int creditnbr1 = Integer.parseInt(creditcardnr1.getText());
        int creditnbr2 = Integer.parseInt(creditcardnr2.getText());
        int creditnbr3 = Integer.parseInt(creditcardnr3.getText());
        int dates = Integer.parseInt(date.getText());
        int cvcnbr = Integer.parseInt(cvc.getText());
        double amountPrice = Double.parseDouble(totalAmount.getText());
        System.out.println("WORKED 2");

        String tooMail=  db.getEmail(userName);;
        String fromMail="onlinemusicstorev1@gmail.com";
        String password ="9x828x5w";
        String subject="Purchase Reciet";

        System.out.println("WORKED 3");

        String host = "smtp.gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.user",fromMail);
        props.put("mail.smtp.password",password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(props);
        double price= calculateCost();

        System.out.println("WORKED 4");

        try {
            System.out.println("WORKED 5");

            Message messageCheckout = new MimeMessage(session);
            messageCheckout.setFrom(new InternetAddress(fromMail));
            messageCheckout.setRecipient(Message.RecipientType.TO,new InternetAddress(tooMail));
            messageCheckout.setSubject(subject);

            System.out.println("WORKED 6");


for (Song song : songs){
    String album=song.getAlbumName();
    String artist=song.getArtistName();
   String names= song.getSongName();



    messageCheckout.setText("Dear"+" "+name+" "+lastname
            + "\n\n"+"Thank you for your purchase! "+"\n"+"totalprice: "+price+ "\n\n"+"Album: "+album+"\t"+"Artist: "+artist+"\n"+"Songname: " +
            ""+names+"\n");
}

            Transport transport =session.getTransport("smtp");
            transport.connect(host,fromMail,password);
            transport.sendMessage(messageCheckout,messageCheckout.getAllRecipients());
            System.out.println("EMAIL SENT");
            transport.close();

            Payment payment=new Payment();
            boolean confirmed=true;
            payment.setPaid(confirmed);

            if (confirmed==true){


                Alert correctWorked= new Alert(Alert.AlertType.CONFIRMATION,"SUCCESFUL,Payment ACCEPTED");
                correctWorked.show();




            }

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }


    }

    public double calculateCost(){

            double total = 0 ;
            for (Song song : table.getItems()) {
                total += song.getSongPrice() ;
            }
            totalAmount.setText(String.valueOf(total));
        return total;
    }


    @FXML
    void returnToSearch(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../OnlinemusicstoreFxml/mainMenu.fxml"));
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
    void helpMenuPressed(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Shopping cart");
        alert.setHeaderText("How to use the shopping cart. ");
        alert.setContentText("This is where you buy the songs that you have added to your cart. Fill in all the necessary" +
                "information then press the pay button to confirm the transaction. ");

        alert.showAndWait();
    }

    }
