package tools.exception;

import javax.swing.JOptionPane;

public class AddUserException extends Throwable {

    private String string;
    public AddUserException(String string) {
        this.string = string;
        JOptionPane.showMessageDialog(null, string, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
