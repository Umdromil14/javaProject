package controller;

import business.UserManager;
import exception.AddUserException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import tools.BussinessEntity;

public class ModificationController {

    private UserManager userManager;
    public ModificationController() {
        userManager = new UserManager();
    }
    //#region FXML
    @FXML
    private Button ConfirmButton;

    @FXML
    private TextField CountryField;

    @FXML
    private TextField PCodeField;

    @FXML
    private Text WrongMail;

    @FXML
    private TextField cityField;

    @FXML
    private TextField eMailField;

    @FXML
    private Text emptyError;

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
    //#endregion

    @FXML
    void ConfirmListener(ActionEvent event) {
        try {
            BussinessEntity user = new BussinessEntity(
            firstnameField.getText().trim(),
            lastnameField.getText().trim(), 
            eMailField.getText().trim(), 
            passwordField.getText().trim(),
            streetField.getText().trim(),
            cityField.getText().trim(), 
            Integer.parseInt(numberField.getText().trim()),
            Integer.parseInt(PCodeField.getText().trim()),
            passwordRepeatField.getText().trim(),
            CountryField.getText().trim()
            );
            userManager.createUser(user);
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        catch (AddUserException e) {
            System.out.println(e.getMessage());
        }

    }

}

