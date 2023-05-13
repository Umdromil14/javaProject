package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import business.ModificationUser;
import business.UserManager;
import tools.BusinessEntity;

public class SignUpController implements Initializable {
    private UserManager userManager;
    private ModificationUser modificationUser;
    private ArrayList<String> countries;
    private ArrayList<String> cities;
    private ArrayList<Integer> postalCodes;


    public SignUpController() throws SQLException {
        userManager = new UserManager();
        modificationUser = new ModificationUser();
        this.countries = modificationUser.getCountries();
        this.cities = modificationUser.getCity(countries.get(0));
        this.postalCodes = modificationUser.getPostalCode(cities.get(0));
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
        //pre filled the postal code, city, country
        ComboBoxCountry.getItems().addAll(countries);
        ComboBoxCountry.setValue(countries.get(0));
        ComboBoxCity.getItems().addAll(cities);
        ComboBoxCity.setValue(cities.get(0));
        ComboBoxPC.getItems().addAll(postalCodes);
        ComboBoxPC.setValue(postalCodes.get(0));
        
    }

    @FXML
    public void ConfirmListener(ActionEvent event) throws NullPointerException, IOException {
        try {
            BusinessEntity user = new BusinessEntity(
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
            userManager.createUser(user);
            //send a message (user created)
            FXMLStage.getInstance().load("/view/adminProfile.fxml", "admin view");
    
        } catch (tools.exception.AddUserException | SQLException e)// and catch the error of Integer.parseInt
        {
            e.printStackTrace();
            //catch the exception
        }
    }

    @FXML
    void CountryListener(ActionEvent event) {
        String country = ComboBoxCountry.getValue();
        ComboBoxCity.getItems().clear();
        try {
            ComboBoxCity.getItems().addAll(modificationUser.getCity(country));
            //array list to get the city and to set to the first one?
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void CityListener(ActionEvent event) {
        String city = ComboBoxCity.getValue();
        ComboBoxPC.getItems().clear();
        try {
            ComboBoxPC.getItems().addAll(modificationUser.getPostalCode(city));
            //array list to get the postal code and to set to the first one?
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  

}