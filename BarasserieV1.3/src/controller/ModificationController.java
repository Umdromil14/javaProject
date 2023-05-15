package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import business.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import tools.Utils;
import tools.DBOutput.User;


//add all the methods in the controller
public class ModificationController {

    private UserManager userManager;
    private ArrayList<String> cities;
    private ArrayList<Integer> postalCodes;
    public ModificationController() {
        userManager = new UserManager();
        this.cities = new ArrayList<>();
        this.postalCodes = new ArrayList<>();
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

    public void displayUser(User user) throws SQLException
    {
        firstnameField.setText(user.getFirstname());
        lastnameField.setText(user.getLastname());
        eMailField.setText(user.getEmail());
        numberField.setText(user.getAddress().getNumber().toString());
        streetField.setText(user.getAddress().getStreet());
        comboBoxCity.getItems().addAll(userManager.getCity(user.getAddress().getCity().getCountry()));
        comboBoxPC.getItems().addAll(userManager.getPostalCode(user.getAddress().getCity().getPostalCode().toString()));
        comboxBoxCountry.getItems().addAll(userManager.getCountries());
        comboBoxCity.setValue(user.getAddress().getCity().getName());
        comboxBoxCountry.setValue(user.getAddress().getCity().getCountry());
        comboBoxPC.setValue(user.getAddress().getCity().getPostalCode());
    }
    //encore a check
    @FXML
    void ConfirmListener(ActionEvent event) {
        try {
            User user = new User(
            firstnameField.getText().trim(),
            lastnameField.getText().trim(), 
            eMailField.getText().trim(), 
            passwordField.getText().trim(),
            streetField.getText().trim(),
            comboBoxCity.getValue().trim(), 
            Integer.parseInt(numberField.getText().trim()),
            Integer.parseInt(comboBoxPC.getValue().toString()),
            passwordRepeatField.getText().trim(),
            comboxBoxCountry.getValue().trim()
            );
            Utils.filterDataUser(user);
            //encore à modifier
            //comment modifier l'adresse ?
            // get user ?
            //userManager.updateUser(user);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

    }
      @FXML
    void CityListener(ActionEvent event) {
        String city = comboBoxCity.getValue();
        comboBoxPC.getItems().clear();
        try {
            postalCodes = userManager.getPostalCode(city);
            comboBoxPC.getItems().addAll(postalCodes);
            comboBoxPC.setValue(postalCodes.get(0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void countryListener(ActionEvent event) {
        String country = comboxBoxCountry.getValue();
        comboBoxCity.getItems().clear();
        try {
            cities = userManager.getCity(country);
            comboBoxCity.getItems().addAll(cities);
            comboBoxCity.setValue(cities.get(0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}