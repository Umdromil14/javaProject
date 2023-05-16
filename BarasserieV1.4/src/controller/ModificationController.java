<<<<<<< HEAD
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

    private User modificationUser;
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
        modificationUser = user;
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
            modificationUser.setFirstname(firstnameField.getText().trim());
            modificationUser.setLastname(lastnameField.getText().trim());
            modificationUser.setEMail(eMailField.getText().trim());
            modificationUser.setPassword(passwordField.getText().trim());
            modificationUser.setRepeatPassword(passwordRepeatField.getText().trim());
            modificationUser.setStreet(streetField.getText().trim());
            modificationUser.setCity(comboBoxCity.getValue().trim());
            modificationUser.setNumber(Integer.parseInt(numberField.getText().trim()));
            modificationUser.setPostalCode(Integer.parseInt(comboBoxPC.getValue().toString()));
            modificationUser.setCountry(comboxBoxCountry.getValue().trim());
            if(Utils.filterDataUser(modificationUser))
            {
                userManager.updateUser(modificationUser);
                Utils.popUp("Modification effectuée");
                FXMLStage.getInstance().load("/view/adminProfile.fxml","admin view");
            }
            else
            {
                Utils.popUp("Veuillez remplir tous les champs");
            }
            
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
=======
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

    private User modificationUser;
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
        modificationUser = user;
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
            modificationUser.setFirstname(firstnameField.getText().trim());
            modificationUser.setLastname(lastnameField.getText().trim());
            modificationUser.setEMail(eMailField.getText().trim());
            modificationUser.setPassword(passwordField.getText().trim());
            modificationUser.setRepeatPassword(passwordRepeatField.getText().trim());
            modificationUser.setStreet(streetField.getText().trim());
            modificationUser.setCity(comboBoxCity.getValue().trim());
            modificationUser.setNumber(Integer.parseInt(numberField.getText().trim()));
            modificationUser.setPostalCode(Integer.parseInt(comboBoxPC.getValue().toString()));
            modificationUser.setCountry(comboxBoxCountry.getValue().trim());
            if(Utils.filterDataUser(modificationUser))
            {
                userManager.updateUser(modificationUser);
                Utils.popUp("Modification effectuée");
                FXMLStage.getInstance().load("/view/adminProfile.fxml","admin view");
            }
            else
            {
                Utils.popUp("Veuillez remplir tous les champs");
            }
            
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
>>>>>>> a3552b73418bdd63b5a8a8ec3eefd92e53f2f519
}