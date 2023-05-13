package tools;

import javax.swing.JOptionPane;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static String hashPassword(String password, String salt) {
        MessageDigest msg = null;
        try {
            msg = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte [] hash = msg.digest((password+salt).getBytes());
        StringBuilder string = new StringBuilder();

        for (byte b : hash) {
            string.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return string.toString();
    }

    public static String generateSalt() {
        String salt[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","z"};
        String generatedSalt = "" ;

        for (int i = 0; i < 5; i++) {
            generatedSalt += salt[(int)(Math.random()*25)];
        }
        return generatedSalt;
    }

    public static void popUp(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
    }
}
