


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ConnexionController {

    @FXML
    private Text emptyError;
    
    @FXML
    private TextField CountryField;

    @FXML
    private Button ConfirmButton;

    @FXML
    private TextField PCodeField;

    @FXML
    private Text WrongMail;

    @FXML
    private TextField cityField;

    @FXML
    private TextField eMailField;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField numberField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField passwordRepeatField;

    @FXML
    private TextField streetField;

    @FXML
    private Text wrongPassword;

    @FXML
    void ConfirmListener(ActionEvent event) {
        String informations [] = 
        {
            firstnameField.getText().trim(),
            lastnameField.getText().trim(), 
            eMailField.getText().trim(), 
            passwordField.getText(), 
            passwordRepeatField.getText(), 
            streetField.getText().trim(), 
            numberField.getText().trim(), 
            cityField.getText().trim(), 
            PCodeField.getText().trim()
        };
        //send all values to the second layers with this code before the DB
        for (String infoUser : informations) 
        {
            if (infoUser.equals(""))
            {
                emptyError.setVisible(true);
                return;
            }
        }
        if (!informations[3].equals(informations[4])) 
        {
            wrongPassword.setVisible(true);
        }

    }

}
