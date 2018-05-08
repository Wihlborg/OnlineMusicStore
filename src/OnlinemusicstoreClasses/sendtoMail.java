package OnlinemusicstoreClasses;



import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class sendtoMail {



    public void hej(URL url, ResourceBundle resourceBundle){


    String tooMail;
    String fromMail="onlinemusicstorev1=gmail.com";
    String subject="Lost Password";
    String message;

        Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");






}
}