package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import business.ResearchManager;
import tools.Utils;
import tools.DBOutput.TableEntry;
import tools.enumeration.Execution;
import tools.exception.InvalidInputException;

public class AdminController {
    private Execution currentExecution;
    private ResearchManager manager;

    public AdminController() throws SQLException {
        manager = new ResearchManager();
    }

    @FXML
    private TableColumn<TableEntry, String> column1;

    @FXML
    private TableColumn<TableEntry, String> column2;

    @FXML
    private TableColumn<TableEntry, String> column3;

    @FXML
    private TableColumn<TableEntry, String> column4;

    @FXML
    private TableColumn<TableEntry, String> column5;

    @FXML
    private TableColumn<TableEntry, String> column6;

    @FXML
    private TableColumn<TableEntry, String> column7;

    @FXML
    private TableColumn<TableEntry, String> column8;

    @FXML
    private TableColumn<TableEntry, String> column9;

    @FXML
    private TableColumn<TableEntry, String> column10;

    @FXML
    private Text errorText;

    @FXML
    private TextArea description;

    @FXML
    private DatePicker parameterDate;

    @FXML
    private MenuButton parameterMenu;

    @FXML
    private TextField parameterText;

    @FXML
    private Button researchButton;

    @FXML
    private TableView<TableEntry> table;

    @FXML
    void changeStatus1(ActionEvent event) {
        parameterMenu.setText("Status 1");
    }

    @FXML
    void changeStatus2(ActionEvent event) {
        parameterMenu.setText("Status 2");
    }

    @FXML
    void createUser(ActionEvent event) {
        try {
            FXMLStage.getInstance().load("/view/signUp.fxml", "Sign up");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void displayUser(ActionEvent event) {
        setFields("Client ID", false, true, true);

        currentExecution = Execution.READ_USER;
    }

    @FXML
    void updateUser(ActionEvent event) {
        setFields("Client ID", false, true, true);

        currentExecution = Execution.UPDATE_USER;
    }

    @FXML
    void deleteUser(ActionEvent event) {
        setFields("Client ID", false, true, true);

        currentExecution = Execution.DELETE_USER;
    }

    @FXML
    void displayCities(ActionEvent event) {
        try {
            addTableItems(FXCollections.observableArrayList(manager.getCities()));
            setColumnsNames("ID", "name", "postal code", "country");
        } catch (SQLException e) {
            Utils.popUp("An error occured while trying to access the database");
        }
    }

    @FXML
    void displayUsers(ActionEvent event) throws SQLException {
        ResearchManager manager = new ResearchManager();

        try {
            addTableItems(FXCollections.observableArrayList(manager.getUsers()));
            setColumnsNames("ID", "firstname", "lastname", "email", "street", "number", "postal", "city", "country");
        } catch (SQLException e) {
            Utils.popUp("An error occured while trying to access the database");
            e.printStackTrace();
        }
    }

    // corriger
    @FXML
    void execute(ActionEvent event) {
        errorText.setVisible(false);
        
        try {
            int id = Integer.parseInt(parameterText.getText());

            switch(currentExecution) {
                case TOP_PRODUCT_CITY:
                    addTableItems(FXCollections.observableArrayList(manager.getTopProductByCityId(id)));
                    setColumnsNames("city", "product", "quantity");
                    break;
                case TOP_PRODUCT_CLIENT:
                    addTableItems(FXCollections.observableArrayList(manager.getTopProductByClientId(id)));
                    setColumnsNames("firstname", "lastname", "product", "quantity");
                    break;
                case INVOICE:
                    // addTableItems(FXCollections.observableArrayList(manager.getInvoicesByClientId(id)));
                    // setColumnsNames("invoiceID", "date", "status", "product", "quantity");
                    break;
                case READ_USER:
                    addTableItems(FXCollections.observableArrayList(manager.getUserById(id)));
                    setColumnsNames("ID", "firstname", "lastname", "email", "street", "number", "postal", "city", "country");
                    break;
                case UPDATE_USER:
                    System.out.println("update user");
                    // addTableItems(FXCollections.observableArrayList(manager.getUserById(id)));
                    // setColumnsNames("ID", "firstname", "lastname", "email", "street", "number", "postal", "city", "country");
                    break;
                case DELETE_USER:
                    // addTableItems(FXCollections.observableArrayList(manager.getUserById(id)));
                    // setColumnsNames("ID", "firstname", "lastname", "email", "street", "number", "postal", "city", "country");
                    break;
            }

        } catch (NumberFormatException | InvalidInputException e) {
            errorText.setText("The ID must be a positive integer");
            errorText.setVisible(true);
        } catch (SQLException e) {
            errorText.setText("An error occured while trying to access the database");
            errorText.setVisible(true);
        } catch (NoSuchElementException e) {
            Utils.popUp(e.getMessage());
        }
    }

    @FXML
    void researchCity(ActionEvent event) {
        setFields("City ID", false, true, true);

        currentExecution = Execution.TOP_PRODUCT_CITY;
    }

    @FXML
    void researchClient(ActionEvent event) {
        setFields("Client ID", false, true, true);

        currentExecution = Execution.TOP_PRODUCT_CLIENT;
    }

    @FXML
    void researchInvoice(ActionEvent event) {
        setFields("Client ID (optional)", false, false, false);

        currentExecution = Execution.INVOICE;
    }

    private void setColumnsNames(String... columnNames) {
        TableColumn<?, ?>[] columns = {column1, column2, column3, column4, column5, column6, column7, column8, column9, column10};

        for (int i = 0; i < columns.length; i++) {
            if (i < columnNames.length) {
                columns[i].setText(columnNames[i]);
                columns[i].setVisible(true);
            } else {
                columns[i].setVisible(false);
            }
        }
    }

    private void addTableItems(ObservableList<TableEntry> items) {
        column1.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("column1"));
        column2.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("column2"));
        column3.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("column3"));
        column4.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("column4"));
        column5.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("column5"));
        column6.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("column6"));
        column7.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("column7"));
        column8.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("column8"));
        column9.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("column9"));
        column10.setCellValueFactory(new PropertyValueFactory<TableEntry, String>("column10"));

        table.setItems(items);
    }

    private void setFields(String text, boolean isTextDisabled, boolean isDateDisabled, boolean isMenuDisabled) {
        initializeFields();

        parameterText.setPromptText(text);
        parameterText.setDisable(isTextDisabled);
        parameterDate.setDisable(isDateDisabled);
        parameterMenu.setDisable(isMenuDisabled);
        researchButton.setDisable(false);
    }

    private void initializeFields() {
        parameterText.setText(null);
        parameterDate.setValue(null);
        parameterMenu.setText("Status");
        errorText.setVisible(false);
    }
}
