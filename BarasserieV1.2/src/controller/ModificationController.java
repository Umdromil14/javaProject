package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import business.ModificationUser;
import business.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tools.DBOutput.User;

public class ModificationController implements Initializable {

    private UserManager userManager;
    private ModificationUser modificationUser;
    public ModificationController() {
        userManager = new UserManager();
        modificationUser = new ModificationUser();
    }
    
    //#region FXML
    @FXML
    private Button ConfirmButton;

    @FXML
    private TextField CountryField;

    @FXML
    private ComboBox<Integer> comboBoxPC;

    @FXML
    private ComboBox<String> comboBoxCity;

    @FXML
    private ComboBox<String> comboxBoxCountry;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            //send the id
            
            Stage stage = (Stage) streetField.getScene().getWindow();
            //stage.setuserData(1);
            User user = modificationUser.getUser(1); 
            comboxBoxCountry.getItems().addAll(modificationUser.getCountries());
            comboxBoxCountry.setValue(user.getAddress().getCity().getCountry());
            comboBoxCity.setValue(user.getAddress().getCity().getName());
            comboBoxPC.setValue(user.getAddress().getCity().getPostalCode());
            firstnameField.setText(user.getFirstname());
            lastnameField.setText(user.getLastname());
            eMailField.setText(user.getEmail());
            numberField.setText(user.getAddress().getNumber().toString());
            streetField.setText(user.getAddress().getStreet());
            comboBoxCity.getItems().addAll(modificationUser.getCity(user.getAddress().getCity().getCountry()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ConfirmListener(ActionEvent event) {
        try {
            User user = new User(
            firstnameField.getText().trim(),
            lastnameField.getText().trim(), 
            eMailField.getText().trim(), 
            passwordField.getText().trim(),
            streetField.getText().trim(),
            cityField.getText().trim(), 
            Integer.parseInt(numberField.getText().trim()),
            Integer.parseInt(comboxBoxCountry.getValue().toString()),
            passwordRepeatField.getText().trim(),
            CountryField.getText().trim()
            );
            userManager.createUser(user);
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
      @FXML
    void CityListener(ActionEvent event) {
        String city = comboBoxCity.getValue();
        comboBoxPC.getItems().clear();
        try {
            comboBoxPC.getItems().addAll(modificationUser.getPostalCode(city));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void countryListener(ActionEvent event) {
        String country = comboxBoxCountry.getValue();
        comboBoxCity.getItems().clear();
        try {
            comboBoxCity.getItems().addAll(modificationUser.getCity(country));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}