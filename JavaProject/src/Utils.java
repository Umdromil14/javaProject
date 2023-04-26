

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {
    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException
    {
        MessageDigest msg = MessageDigest.getInstance("SHA-256");
        byte [] hash = msg.digest((password+salt).getBytes());
        StringBuilder s = new StringBuilder();
        for (byte b : hash) {
            s.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return s.toString();
    }
    public static String generateSalt()
    {
        String salt[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","z"};
        String generateSalt="" ;
        for (int i = 0; i < 5; i++) {
            generateSalt += salt[(int)(Math.random()*25)];
        }
        return generateSalt;
    }
    public static ResultSet checkingResultSet(ResultSet rs) throws SQLException
    {
        while(rs.next())
        {
            if (!rs.wasNull())
            {
                return rs;
            }
        }
        return null;
    }
    public static String isValidEmail(String email)
    {
        if (email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"))
        {
            return email;
        }
        return null;
    }
    public static Integer isValidInteger(String integer)
    {
        if (integer.matches("[0-9]+"))
        {
            return Integer.parseInt(integer);
        }
        return null;
    }
}
