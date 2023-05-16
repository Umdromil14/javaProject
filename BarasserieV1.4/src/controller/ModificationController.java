package controller;

import java.io.IOException;
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
    private Button backButton;

    @FXML
    private Button reinitializeButton;

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

    public void displayUser(User user)
    {
        modificationUser = user;
        firstnameField.setText(user.getFirstname());
        lastnameField.setText(user.getLastname());
        eMailField.setText(user.getEmail());
        numberField.setText(user.getAddress().getNumber().toString());
        streetField.setText(user.getAddress().getStreet());
        try {
            comboBoxCity.getItems().addAll(userManager.getCity(user.getAddress().getCity().getCountry()));
            comboBoxPC.getItems().addAll(userManager.getPostalCode(user.getAddress().getCity().getPostalCode().toString()));
            comboxBoxCountry.getItems().addAll(userManager.getCountries());    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        comboBoxCity.setValue(user.getAddress().getCity().getName());
        comboxBoxCountry.setValue(user.getAddress().getCity().getCountry());
        comboBoxPC.setValue(user.getAddress().getCity().getPostalCode());
    }
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
                Utils.popUp("please fill all the fields");
            }
        } 
        catch (SQLException e) {
            Utils.popUp("An error occured while trying to access the database");
        }
        catch (NumberFormatException e) {
            Utils.popUp("The client ID must be a positive integer");
        }
        catch(NullPointerException | IOException e)
        {
            Utils.popUp("Error while loading the page");
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
            Utils.popUp("An error occured while trying to access the database");
        }
    }

    @FXML
    void BackListener(ActionEvent event) {
        try {
            FXMLStage.getInstance().load("/view/adminProfile.fxml","admin view");
        } catch (Exception e) {
            Utils.popUp("Error while loading the page");
        }
    }

    
    @FXML
    void reinitializeListener(ActionEvent event) {
        displayUser(modificationUser);
        passwordField.setText("");
        passwordRepeatField.setText("");
    }
}