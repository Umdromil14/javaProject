package controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.User;
import business.AddressManager;
import business.CommunicationManager;
import business.UserManager;
import exception.DataAccessException;
import exception.InvalidInputException;
import view.PopUp;

public class ModificationController {
    private PopUp popUp;
    private User modificationUser;
    private UserManager userManager;
    private AddressManager addressManager;
    private CommunicationManager communicationManager;
    private List<String> cities;
    private List<Integer> postalCodes;

    public ModificationController() {
        popUp = new PopUp();
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

    public void displayUser(User user) {
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
        } catch (DataAccessException e) {
            popUp.warning(e.getMessage());
        }
        comboBoxCity.setValue(user.getAddress().getCity().getName());
        comboBoxCountry.setValue(user.getAddress().getCity().getCountry());
        comboBoxPostalCode.setValue(user.getAddress().getCity().getPostalCode());
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

    private void validateNumberInput(int id) throws InvalidInputException {
        if (id < 0) {
            throw new InvalidInputException("The number must be a positive integer");
        }
    }

    @FXML
    void ConfirmListener(ActionEvent event) {
        if (hasEmptyField()) {
            popUp.warning("Please fill all the fields");
            return;
        }
        String password = passwordField.getText().trim();
        String passwordRepeat = passwordRepeatField.getText().trim();
        if (!password.equals(passwordRepeat)) {
            popUp.warning("Please enter the same password");
            return;
        }
        String email = emailField.getText().trim();
        if (!communicationManager.isValidEmail(email)) {
            popUp.warning("Please enter a valid email");
            return;
        }

        try {
            if (!email.equals(modificationUser.getEmail()) && communicationManager.isMailAlreadyUsed(email)) {
                popUp.warning("This email is already used");
                return;
            }
            int number = Integer.parseInt(numberField.getText().trim());
            validateNumberInput(number);

            modificationUser.setUser(
                modificationUser.getId(),
                firstnameField.getText().trim(),
                lastnameField.getText().trim(),
                email,
                password,
                modificationUser.getSalt(),
                streetField.getText().trim(),
                number,
                comboBoxPostalCode.getValue(),
                comboBoxCity.getValue().trim(),
                comboBoxCountry.getValue().trim(),
                modificationUser.getIdAddress()
            );
            if (firstnameField.getText().trim().isEmpty()) {
                modificationUser.setFirstname(null);
            }
            userManager.updateUser(modificationUser);

            popUp.success("User successfully updated");
            FXMLStage.getInstance().load("/view/adminProfile.fxml","admin view");
        } catch (DataAccessException e) {
            popUp.warning(e.getMessage());
        } catch (NumberFormatException | InvalidInputException e) {
            popUp.warning("The number must be a positive integer");
        } catch(IOException e) {
            popUp.warning("Error while loading the page");
        }

    }

    @FXML
    void CityListener(ActionEvent event) {
        comboBoxPostalCode.getItems().clear();

        try {
            postalCodes = addressManager.getPostalCode(comboBoxCity.getValue());
            comboBoxPostalCode.getItems().addAll(postalCodes);
        } catch (DataAccessException e) {
            popUp.warning(e.getMessage());
        }
    }

    @FXML
    void countryListener(ActionEvent event) {
        comboBoxCity.getItems().clear();

        try {
            cities = addressManager.getCity(comboBoxCountry.getValue());
            comboBoxCity.getItems().addAll(cities);
            comboBoxCity.setValue(cities.get(0));
        } catch (DataAccessException e) {
            popUp.warning(e.getMessage());
        }
    }

    @FXML
    void BackListener(ActionEvent event) {
        try {
            FXMLStage.getInstance().load("/view/adminProfile.fxml","Admin Profile");
        } catch (IOException e) {
            popUp.warning("Error while loading the page");
        }
    }
    
    @FXML
    void reinitializeListener(ActionEvent event) {
        displayUser(modificationUser);
        passwordField.clear();
        passwordRepeatField.clear();
    }
}