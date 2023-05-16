<<<<<<< HEAD
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

    //à bouger ?
    public static Boolean filterDataUser(User bussinessEntity)
    {
        return isSamePassword(bussinessEntity.getPassword(), bussinessEntity.getRepeatPassword()) && 
        !isFieldEmpty(bussinessEntity) && 
        isValidEmail(bussinessEntity.getEmail());
    }
    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    }
    public static Boolean isSamePassword(String password, String repeatPassword)
    {
        return password.equals(repeatPassword);
    }
    public static Boolean isFieldEmpty(User bussinessEntity)
    {
        return bussinessEntity.getFirstname().isEmpty() || 
        bussinessEntity.getLastname().isEmpty() || 
        bussinessEntity.getEmail().isEmpty() || 
        bussinessEntity.getPassword().isEmpty() ||
        bussinessEntity.getRepeatPassword().isEmpty()||
        bussinessEntity.getAddress().getCity().getName().isEmpty() || 
        bussinessEntity.getAddress().getStreet().isEmpty() || 
        bussinessEntity.getAddress().getNumber() == null || 
        bussinessEntity.getAddress().getCity().getPostalCode() == null;
    }
}
=======
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

    //à bouger ?
    public static Boolean filterDataUser(User bussinessEntity)
    {
        return isSamePassword(bussinessEntity.getPassword(), bussinessEntity.getRepeatPassword()) && 
        !isFieldEmpty(bussinessEntity) && 
        isValidEmail(bussinessEntity.getEmail());
    }
    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    }
    public static Boolean isSamePassword(String password, String repeatPassword)
    {
        return password.equals(repeatPassword);
    }
    public static Boolean isFieldEmpty(User bussinessEntity)
    {
        return bussinessEntity.getFirstname().isEmpty() || 
        bussinessEntity.getLastname().isEmpty() || 
        bussinessEntity.getEmail().isEmpty() || 
        bussinessEntity.getPassword().isEmpty() ||
        bussinessEntity.getRepeatPassword().isEmpty()||
        bussinessEntity.getAddress().getCity().getName().isEmpty() || 
        bussinessEntity.getAddress().getStreet().isEmpty() || 
        bussinessEntity.getAddress().getNumber() == null || 
        bussinessEntity.getAddress().getCity().getPostalCode() == null;
    }
}
>>>>>>> a3552b73418bdd63b5a8a8ec3eefd92e53f2f519
