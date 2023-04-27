package exception;

import javax.swing.JOptionPane;

public class ConnectUserException extends Throwable{
    private String string;
    public ConnectUserException(String string) {
        this.string = string;
        JOptionPane.showMessageDialog(null, string, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
