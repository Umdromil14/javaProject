import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ConnexionController 
{
    private ApplicationController applicationController = new ApplicationController();
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
    void ConfirmListener(ActionEvent event) 
    {
        String informations[] = 
        {
                firstnameField.getText().trim(), lastnameField.getText().trim(),
                eMailField.getText().trim(),
                passwordField.getText(),passwordRepeatField.getText(),
                streetField.getText().trim(),numberField.getText().trim(),cityField.getText().trim(),PCodeField.getText().trim(),CountryField.getText().trim()
        };
        try 
        {
            // }
            BussinessEntity user = new BussinessEntity(informations[0], informations[1], informations[2], informations[3],
                informations[5], informations[7], Integer.parseInt(informations[6]), Integer.parseInt(informations[8]), informations[4], informations[9]);
            // if the user is created successfully
            System.out.println("User created successfully");
            applicationController.addUser(user);
    
        } catch (AddUserException | SQLException e)// and catch the error of Integer.parseInt
        {
            //catch the exception
        }
    }

}
