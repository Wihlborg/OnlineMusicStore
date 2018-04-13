package sample;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Password {


   private String generatedPassword = null;




    public  void passwordEncryptor(String Username, String Password){

        try {

            MessageDigest md = MessageDigest.getInstance(Password);
            md.update(Password.getBytes());
            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
System.out.println(generatedPassword);
    }










}











