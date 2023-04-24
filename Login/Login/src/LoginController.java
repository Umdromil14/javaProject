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
        System.out.println("Create Account");
    }

    @FXML
    void login(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.equals("admin") && password.equals("bidondon")) {
            System.out.println("Login Successful");
            errorText.setVisible(false);
        } else {
            errorText.setVisible(true);
        }
        System.out.println("email: " + email + " password: " + password);
    }

}
