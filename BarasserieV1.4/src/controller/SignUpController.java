<<<<<<< HEAD
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import business.UserManager;
import tools.Utils;
import tools.DBOutput.User;


public class SignUpController implements Initializable {
    private UserManager userManager;
    private ArrayList<String> countries;
    private ArrayList<String> cities;
    private ArrayList<Integer> postalCodes;


    public SignUpController() throws SQLException {
        userManager = new UserManager();
        this.countries = userManager.getCountries();
        this.cities = userManager.getCity(countries.get(0));
        this.postalCodes = userManager.getPostalCode(cities.get(0));
    }

    @FXML
    private Text emptyError;
    @FXML
    private ComboBox<String> ComboBoxCity;

    @FXML
    private ComboBox<String> ComboBoxCountry;

    @FXML
    private ComboBox<Integer> ComboBoxPC;
    
    @FXML
    private Button ConfirmButton;


    @FXML
    private Text WrongMail;

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ComboBoxCountry.getItems().addAll(countries);
        ComboBoxCountry.setValue(countries.get(0));
        ComboBoxCity.getItems().addAll(cities);
        ComboBoxCity.setValue(cities.get(0));
        ComboBoxPC.getItems().addAll(postalCodes);
        ComboBoxPC.setValue(postalCodes.get(0));
    }

    //check if the email is already used
    //add a random UUID to the user
    //reset button
    //set visible the error message with his referenced text
    @FXML
    public void ConfirmListener(ActionEvent event) throws NullPointerException, IOException {
        try {
            User user = new User(
                firstnameField.getText().trim(),
                lastnameField.getText().trim(), 
                eMailField.getText().trim(), 
                passwordField.getText().trim(),
                streetField.getText().trim(),
                ComboBoxCity.getValue(), 
                Integer.parseInt(numberField.getText().trim()),
                ComboBoxPC.getValue(),
                passwordRepeatField.getText().trim(),
                ComboBoxCountry.getValue()
            );
            Utils.filterDataUser(user);
            userManager.createUser(user);
            JOptionPane.showMessageDialog(null, "User created");
            FXMLStage.getInstance().load("/view/adminProfile.fxml", "admin view");
    
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void CountryListener(ActionEvent event) {
        String country = ComboBoxCountry.getValue();
        ComboBoxCity.getItems().clear();
        try {
            cities = userManager.getCity(country);
            ComboBoxCity.getItems().addAll(cities);
            ComboBoxCity.setValue(cities.get(0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void CityListener(ActionEvent event) {
        String city = ComboBoxCity.getValue();
        ComboBoxPC.getItems().clear();
        try {
            postalCodes = userManager.getPostalCode(city);
            ComboBoxPC.getItems().addAll(postalCodes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  

=======
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import business.UserManager;
import tools.Utils;
import tools.DBOutput.User;


public class SignUpController implements Initializable {
    private UserManager userManager;
    private ArrayList<String> countries;
    private ArrayList<String> cities;
    private ArrayList<Integer> postalCodes;


    public SignUpController() throws SQLException {
        userManager = new UserManager();
        this.countries = userManager.getCountries();
        this.cities = userManager.getCity(countries.get(0));
        this.postalCodes = userManager.getPostalCode(cities.get(0));
    }

    @FXML
    private Text emptyError;
    @FXML
    private ComboBox<String> ComboBoxCity;

    @FXML
    private ComboBox<String> ComboBoxCountry;

    @FXML
    private ComboBox<Integer> ComboBoxPC;
    
    @FXML
    private Button ConfirmButton;


    @FXML
    private Text WrongMail;

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ComboBoxCountry.getItems().addAll(countries);
        ComboBoxCountry.setValue(countries.get(0));
        ComboBoxCity.getItems().addAll(cities);
        ComboBoxCity.setValue(cities.get(0));
        ComboBoxPC.getItems().addAll(postalCodes);
        ComboBoxPC.setValue(postalCodes.get(0));
    }

    //check if the email is already used
    //add a random UUID to the user
    //reset button
    //set visible the error message with his referenced text
    @FXML
    public void ConfirmListener(ActionEvent event) throws NullPointerException, IOException {
        try {
            User user = new User(
                firstnameField.getText().trim(),
                lastnameField.getText().trim(), 
                eMailField.getText().trim(), 
                passwordField.getText().trim(),
                streetField.getText().trim(),
                ComboBoxCity.getValue(), 
                Integer.parseInt(numberField.getText().trim()),
                ComboBoxPC.getValue(),
                passwordRepeatField.getText().trim(),
                ComboBoxCountry.getValue()
            );
            Utils.filterDataUser(user);
            userManager.createUser(user);
            JOptionPane.showMessageDialog(null, "User created");
            FXMLStage.getInstance().load("/view/adminProfile.fxml", "admin view");
    
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void CountryListener(ActionEvent event) {
        String country = ComboBoxCountry.getValue();
        ComboBoxCity.getItems().clear();
        try {
            cities = userManager.getCity(country);
            ComboBoxCity.getItems().addAll(cities);
            ComboBoxCity.setValue(cities.get(0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void CityListener(ActionEvent event) {
        String city = ComboBoxCity.getValue();
        ComboBoxPC.getItems().clear();
        try {
            postalCodes = userManager.getPostalCode(city);
            ComboBoxPC.getItems().addAll(postalCodes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  

>>>>>>> a3552b73418bdd63b5a8a8ec3eefd92e53f2f519
}