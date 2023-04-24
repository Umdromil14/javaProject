package View;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController {
    
    @FXML
    private TextField emailField;

    @FXML
    private Text errorText;

    @FXML
    private PasswordField passwordField;

    @FXML
    void createAccount(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        try {
            stage = (Stage) emailField.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("inscription.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Inscription");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }

    @FXML
    void login(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();
        //send to the Controller
        System.out.println(email + " " + password);
    }

}
