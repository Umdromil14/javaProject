package view;

import javax.swing.JOptionPane;

public class PopUp {
    public void warning(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
    }

    public void success(String message) {
        JOptionPane.showMessageDialog(null , message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean validation(String message) {
        int option = JOptionPane.showConfirmDialog(null, message, "Validation", JOptionPane.YES_NO_OPTION);
        return option == JOptionPane.YES_OPTION;
    }
}
