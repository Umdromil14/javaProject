package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import business.AddressManager;
import business.CommunicationManager;
import business.UserManager;
import tools.Utils;
import tools.DBOutput.User;
import tools.exception.NullStageException;


public class SignUpController implements Initializable {
    private UserManager userManager;
    private AddressManager addressManager;
    private CommunicationManager communicationManager;
    private List<String> countries;
    private List<String> cities;
    private List<Integer> postalCodes;

    public SignUpController() throws SQLException {
        userManager = new UserManager();
        addressManager = new AddressManager();
        communicationManager = new CommunicationManager();
        this.countries = addressManager.getCountries();
        this.cities = addressManager.getCity(countries.get(0));
        this.postalCodes = addressManager.getPostalCode(cities.get(0));
    }

    @FXML
    private ComboBox<String> comboBoxCity;

    @FXML
    private ComboBox<String> comboBoxCountry;

    @FXML
    private ComboBox<Integer> comboBoxPostalCode;

    @FXML
    private TextField emailField;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBoxCountry.getItems().addAll(countries);
        comboBoxCountry.setValue(countries.get(0));
        comboBoxCity.getItems().addAll(cities);
        comboBoxCity.setValue(cities.get(0));
        comboBoxPostalCode.getItems().addAll(postalCodes);
        comboBoxPostalCode.setValue(postalCodes.get(0));
    }

    private boolean hasEmptyField() {
        return lastnameField.getText().trim().isEmpty() ||
            emailField.getText().trim().isEmpty() ||
            passwordField.getText().trim().isEmpty() ||
            passwordRepeatField.getText().trim().isEmpty() ||
            streetField.getText().trim().isEmpty() ||
            numberField.getText().trim().isEmpty() ||
            comboBoxCity.getValue() == null ||
            comboBoxPostalCode.getValue() == null ||
            comboBoxCountry.getValue() == null;
    }

    @FXML
    public void ConfirmListener(ActionEvent event) {
        if (hasEmptyField()) {
            Utils.warningPopUp("Please fill all the fields");
            return;
        }

        String password = passwordField.getText().trim();
        String passwordRepeat = passwordRepeatField.getText().trim();
        if (!password.equals(passwordRepeat)) {
            Utils.warningPopUp("Please enter the same password");
            return;
        }

        String email = emailField.getText().trim();
        if (!communicationManager.isValidEmail(email)) {
            Utils.warningPopUp("Please enter a valid email");
            return;
        }

        try {
            if (communicationManager.isMailAlreadyUsed(email)) {
                Utils.warningPopUp("This email is already used");
                return;
            }

            User user = new User(
                firstnameField.getText().trim(),
                lastnameField.getText().trim(), 
                emailField.getText().trim(), 
                passwordField.getText().trim(),
                streetField.getText().trim(),
                comboBoxCity.getValue(), 
                Integer.parseInt(numberField.getText().trim()),
                comboBoxPostalCode.getValue(),
                passwordRepeatField.getText().trim(),
                comboBoxCountry.getValue()
            );
            if (firstnameField.getText().trim().isEmpty()) {
                user.setFirstname(null);
            }

            userManager.createUser(user);
            Utils.successPopUp("User successfully created");
            FXMLStage.getInstance().load("/view/adminProfile.fxml", "admin view");
        } catch (SQLException e) {
            Utils.warningPopUp("An error occured while trying to access the database");
        } catch (NullStageException | IOException e) {
            Utils.warningPopUp("Error while loading the page");
        } catch (NumberFormatException e) {
            Utils.warningPopUp("Please enter a valid number");
        }
    }

    @FXML
    void CountryListener(ActionEvent event) {
        comboBoxCity.getItems().clear();
        
        try {
            cities = addressManager.getCity(comboBoxCountry.getValue());
            comboBoxCity.getItems().addAll(cities);
            comboBoxCity.setValue(cities.get(0));
        } catch (SQLException e) {
            Utils.warningPopUp("An error occured while trying to access the database");
        }
    }

    @FXML
    void CityListener(ActionEvent event) {
        comboBoxPostalCode.getItems().clear();

        try {
            postalCodes = addressManager.getPostalCode(comboBoxCity.getValue());
            comboBoxPostalCode.getItems().addAll(postalCodes);
        } catch (SQLException e) {
            Utils.warningPopUp("An error occured while trying to access the database");
        }
    }

    @FXML
    void reinitialiseListener(ActionEvent event) {
        firstnameField.clear();
        lastnameField.clear();
        emailField.clear();
        passwordField.clear();
        passwordRepeatField.clear();
        streetField.clear();
        numberField.clear();
        comboBoxCountry.setValue(countries.get(0));
        comboBoxCity.setValue(cities.get(0));
        comboBoxPostalCode.setValue(postalCodes.get(0));
    }

    @FXML
    void BackListener(ActionEvent event) {
        try {
            FXMLStage.getInstance().load("/view/adminProfile.fxml", "Admin Profile");
        } catch (NullStageException | IOException e) {
            Utils.warningPopUp("Error while loading the page");
        }
    }
}