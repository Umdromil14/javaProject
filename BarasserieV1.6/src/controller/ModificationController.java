package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import business.AddressManager;
import business.CommunicationManager;
import business.UserManager;
import tools.Utils;
import tools.DBOutput.User;
import tools.exception.NullStageException;

public class ModificationController {
    private User modificationUser;
    private UserManager userManager;
    private AddressManager addressManager;
    private CommunicationManager communicationManager;
    private List<String> cities;
    private List<Integer> postalCodes;

    public ModificationController() {
        userManager = new UserManager();
        addressManager = new AddressManager();
        communicationManager = new CommunicationManager();

        this.cities = new ArrayList<>();
        this.postalCodes = new ArrayList<>();
    }

    @FXML
    private TextField CountryField;

    @FXML
    private ComboBox<Integer> comboBoxPostalCode;

    @FXML
    private ComboBox<String> comboBoxCity;

    @FXML
    private ComboBox<String> comboBoxCountry;

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

    public void displayUser(User user)
    {
        modificationUser = user;
        firstnameField.setText(user.getFirstname());
        lastnameField.setText(user.getLastname());
        emailField.setText(user.getEmail());
        numberField.setText(user.getAddress().getNumber().toString());
        streetField.setText(user.getAddress().getStreet());
        try {
            comboBoxCity.getItems().addAll(addressManager.getCity(user.getAddress().getCity().getCountry()));
            comboBoxPostalCode.getItems().addAll(addressManager.getPostalCode(user.getAddress().getCity().getPostalCode().toString()));
            comboBoxCountry.getItems().addAll(addressManager.getCountries());    
        } catch (SQLException e) {
            Utils.warningPopUp("An error occured while trying to access the database");
        }
        comboBoxCity.setValue(user.getAddress().getCity().getName());
        comboBoxCountry.setValue(user.getAddress().getCity().getCountry());
        comboBoxPostalCode.setValue(user.getAddress().getCity().getPostalCode());
    }

    private boolean hasEmptyField() {
        return firstnameField.getText().trim().isEmpty() ||
            lastnameField.getText().trim().isEmpty() ||
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
    void ConfirmListener(ActionEvent event) {
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
            if (!email.equals(modificationUser.getEmail()) && communicationManager.isMailAlreadyUsed(email)) {
                Utils.warningPopUp("This email is already used");
                return;
            }

            modificationUser.setUser(
                modificationUser.getId(),
                firstnameField.getText().trim(),
                lastnameField.getText().trim(),
                email,
                password,
                modificationUser.getSalt(),
                streetField.getText().trim(),
                Integer.parseInt(numberField.getText().trim()),
                comboBoxPostalCode.getValue(),
                comboBoxCity.getValue().trim(),
                comboBoxCountry.getValue().trim(),
                modificationUser.getIdAddress()
            );
            userManager.updateUser(modificationUser);

            Utils.successPopUp("User successfully updated");
            FXMLStage.getInstance().load("/view/adminProfile.fxml","admin view");
        } catch (SQLException e) {
            Utils.warningPopUp("An error occured while trying to access the database");
        } catch (NumberFormatException e) {
            Utils.warningPopUp("Please enter a valid number");
        } catch(NullStageException | IOException e) {
            Utils.warningPopUp("Error while loading the page");
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
    void countryListener(ActionEvent event) {
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
    void BackListener(ActionEvent event) {
        try {
            FXMLStage.getInstance().load("/view/adminProfile.fxml","Admin Profile");
        } catch (IOException | NullStageException e) {
            Utils.warningPopUp("Error while loading the page");
        }
    }
    
    @FXML
    void reinitializeListener(ActionEvent event) {
        displayUser(modificationUser);
        passwordField.clear();
        passwordRepeatField.clear();
    }
}