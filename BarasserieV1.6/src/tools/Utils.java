package tools;

import javax.swing.JOptionPane;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import tools.DBOutput.User;


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
        String salt[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String generatedSalt = "" ;

        for (int i = 0; i < 5; i++) {
            generatedSalt += salt[(int)(Math.random()*25)];
        }
        return generatedSalt;
    }

    //TODO more popUp
    public static void warningPopUp(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
    }

    public static void successPopUp(String message) {
        JOptionPane.showMessageDialog(null , message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean validationPopUp(String message,String title)
    {
        int option = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION;
    }

    //TODO dans controller ?
    public static Boolean filterDataUser(User bussinessEntity)
    {
        return true;
        // return !isFieldEmpty(bussinessEntity) && isValidEmail(bussinessEntity.getEmail());
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    }

    public static Boolean isSamePassword(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }

    public static Boolean isFieldEmpty(User bussinessEntity) {
        return bussinessEntity.getFirstname().isEmpty() ||
            bussinessEntity.getLastname().isEmpty() || 
            bussinessEntity.getEmail().isEmpty() ||
            bussinessEntity.getAddress().getCity().getName().isEmpty() || 
            bussinessEntity.getAddress().getStreet().isEmpty() || 
            bussinessEntity.getAddress().getNumber() == null || 
            bussinessEntity.getAddress().getCity().getPostalCode() == null;
    }
}
