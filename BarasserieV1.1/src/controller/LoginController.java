package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {
    
    @FXML
    private TextField emailField;

    @FXML
    private Text errorText;

    @FXML
    private PasswordField passwordField;

    @FXML
    void createAccount(ActionEvent event) {
        try {
            FXMLStage.getInstance().load("/view/signup.fxml", "Sign Up");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void login(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.equals("admin") && password.equals("admin")) {
            try {
                FXMLStage.getInstance().load("/view/adminProfile.fxml", "Admin Profile");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        try {
            FXMLStage.getInstance().load("/view/userProfile.fxml", "User Profile");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
