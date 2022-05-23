import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class hashtest {
	 private static String Username = "";
     private static String password = "";
     private static boolean passwordStrength = false;
     private static int count = 0;
     static Scanner in = new Scanner(System.in);
     public static boolean passwordTest(String pending) {
         boolean strength = false;
         if(pending.length() < 8) {
             return strength;
         }
         for(int i = 0; i < pending.length(); i++) {
             String current = pending.substring(i,i+1);
             try {
                 if(Integer.parseInt(((String) current)) == i) {
                     strength = true;
                 }
             } catch (NumberFormatException e) {
                 if (current == current.toUpperCase()) {
                     strength = true;
                 }
             }
         }
         return strength;
     }
   public static void main(String args[]) throws Exception{
	  String messaging = "Demo 3 testing encrypting and deycrypting";
      Cipher CipherAes128 = Cipher.getInstance("AES");
      
      KeyGenerator AES_key = KeyGenerator.getInstance("AES");
      
      AES_key.init(128);
      //Key that will need to be assigned for each user
      SecretKey userSecretKey = AES_key.generateKey();
      //Sets the do final method to the encryption mode
      CipherAes128.init(Cipher.ENCRYPT_MODE, userSecretKey);
      
      System.out.println(messaging);
      /* Converts the message into bytes so it is ready for the encryption process */
      byte[] toEncrypt = messaging.getBytes();
      byte[] encryption = CipherAes128.doFinal(toEncrypt);
      
      System.out.println(encryption);
      System.out.println(userSecretKey);
      
      
   }
}