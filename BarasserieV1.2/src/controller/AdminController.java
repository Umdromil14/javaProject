package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business.*;
import tools.Utils;
import tools.DBOutput.*;
import tools.enumeration.Execution;
import tools.exception.InvalidInputException;

public class AdminController {
    // rajouter constructeurs
    private static Execution currentExecution;

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
    private Button createButton;

    @FXML
    private Button readButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button topProductButton;

    @FXML
    private Text errorText;

    @FXML
    private TextArea description;

    @FXML
    private DatePicker parameterDate;

    @FXML
    private ComboBox<String> parameterComboBox;

    @FXML
    private TextField parameterText;

    @FXML
    private Button researchButton;

    @FXML
    private TableView<TableEntry> table;

    private void setButtons() {
        setButtons(true, true, true, true, true);
    }

    private void setButtons(boolean isCreateDisabled, boolean isReadDisabled, boolean isUpdateDisabled, boolean isDeleteDisabled, boolean isTopProductDisabled) {
        createButton.setDisable(isCreateDisabled);
        readButton.setDisable(isReadDisabled);
        updateButton.setDisable(isUpdateDisabled);
        deleteButton.setDisable(isDeleteDisabled);
        topProductButton.setDisable(isTopProductDisabled);
    }

    @FXML
    void create(ActionEvent event) {
        try {
            FXMLStage.getInstance().load("/view/signUp.fxml", "Sign up");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void read(ActionEvent event) {
        int id = getSelectedId();

        if (id == -1) {
            Utils.popUp("Please select a row");
            return;
        }

        try {
            User user = new UserManager().getUser(id);

            if (user.isEmpty()) {
                Utils.popUp("No user found for this id");
            } else {
                setTableItems(FXCollections.observableArrayList(user.toTableEntry()));
                setColumnsNames(Execution.READ_USER.getOutputs());
            }
        } catch (SQLException e) {
            Utils.popUp("An error occured while trying to access the database");
        }
    }

    @FXML
    void update(ActionEvent event) {
        currentExecution = Execution.UPDATE_USER;

        setFields(currentExecution);
    }

    @FXML
    void delete(ActionEvent event) {
        currentExecution = Execution.DELETE_USER;

        setFields(currentExecution);
    }

    @FXML
    void researchCities(ActionEvent event) {
        try {
            List<City> cities = new CityManager().getAllCities();
            List<TableEntry> tableEntries = new ArrayList<>();

            for (City city : cities) {
                tableEntries.add(city.toTableEntry());
            }

            setTableItems(FXCollections.observableArrayList(tableEntries));
            setColumnsNames("ID", "city", "postal code", "country");
            setButtons(true, true, true, true, false);

            currentExecution = Execution.TOP_PRODUCT_CITY;
        } catch (SQLException e) {
            Utils.popUp("An error occured while trying to access the database");
            e.printStackTrace();
        }
    }

    @FXML
    void researchUsers(ActionEvent event) throws SQLException {
        try {
            List<User> users = new UserManager().getAllUsers();
            List<TableEntry> tableEntries = new ArrayList<>();

            for (User user : users) {
                tableEntries.add(user.toTableEntry());
            }

            setTableItems(FXCollections.observableArrayList(tableEntries));
            setColumnsNames(Execution.READ_USER.getOutputs());
            setButtons(false, false, false, false, false);

            currentExecution = Execution.TOP_PRODUCT_CLIENT;
        } catch (SQLException e) {
            Utils.popUp("An error occured while trying to access the database");
        }
    }

    @FXML
    void execute(ActionEvent event) {
    //     ResearchManager manager = new ResearchManager();

    //     try {
    //         int id = Integer.parseInt(parameterText.getText());
    //         validateInput(id);

    //         switch(currentExecution) {
    //             case TOP_PRODUCT_CITY:
    //                 TopProductCity topProductCity = new CityManager().getTopProduct(id);

    //                 if (topProductCity.isEmpty()) {
    //                     Utils.popUp("No product found for this city");
    //                 } else {
    //                     addTableItems(FXCollections.observableArrayList(topProductCity.toTableEntry()));
    //                     setColumnsNames(currentExecution.getOutputs());
    //                 }
    //                 break;
    //             case TOP_PRODUCT_CLIENT:
    //                 TopProductClient topProductClient = manager.getTopProductByClient(id);

    //                 if (topProductClient.isEmpty()) {
    //                     Utils.popUp("No product found for this client");
    //                 } else {
    //                     addTableItems(FXCollections.observableArrayList(topProductClient.toTableEntry()));
    //                     setColumnsNames(currentExecution.getOutputs());
    //                 }
    //                 break;
    //             case INVOICE:
    //                 // addTableItems(FXCollections.observableArrayList(manager.getInvoicesByClientId(id)));
    //                 // setColumnsNames("invoiceID", "date", "status", "product", "quantity");
    //                 break;
    //             case READ_USER:
    //                 // addTableItems(FXCollections.observableArrayList(manager.getUserById(id)));
    //                 // setColumnsNames("ID", "firstname", "lastname", "email", "street", "number", "postal", "city", "country");
    //                 break;
    //             case UPDATE_USER:
    //                 // addTableItems(FXCollections.observableArrayList(manager.getUserById(id)));
    //                 // setColumnsNames("ID", "firstname", "lastname", "email", "street", "number", "postal", "city", "country");
    //                 break;
    //             case DELETE_USER:
    //                 // addTableItems(FXCollections.observableArrayList(manager.getUserById(id)));
    //                 // setColumnsNames("ID", "firstname", "lastname", "email", "street", "number", "postal", "city", "country");
    //                 break;
    //         }

    //     } catch (NumberFormatException | InvalidInputException e) {
    //         errorText.setText("The ID must be a positive integer");
    //         errorText.setVisible(true);
    //     } catch (SQLException e) {
    //         Utils.popUp("An error occured while trying to access the database");
    //     }
    }

    public void validateInput(int id) throws InvalidInputException {
        if (id < 0) {
            throw new InvalidInputException("The ID must be a positive integer");
        }
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

    private void setTableItems(ObservableList<TableEntry> items) {
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

    @FXML
    void researchInvoice(ActionEvent event) {
        try {
            List<String> docStatus = new DocStatusManager().getAllDocStatus();

            parameterComboBox.setItems(FXCollections.observableArrayList(docStatus));

            currentExecution = Execution.INVOICE;

            parameterText.setPromptText("Client ID (optional)");
            parameterText.setDisable(false);
            parameterDate.setDisable(false);
            parameterComboBox.setDisable(false);
            researchButton.setDisable(false);
            description.setText(currentExecution.getDescription());
        } catch (SQLException e) {
            Utils.popUp("An error occured while trying to access the database");
        }
    }

    private void setFields(Execution execution) {
        String[] parameters = execution.getInputs();

        initializeFields();

        parameterText.setPromptText(parameters.length > 0 ? parameters[0] : null);
        parameterText.setDisable(1 > parameters.length);
        parameterDate.setDisable(2 > parameters.length);
        parameterComboBox.setDisable(3 > parameters.length);
        researchButton.setDisable(false);
        description.setText(execution.getDescription());
    }

    private void initializeFields() {
        parameterText.setText(null);
        parameterDate.setValue(null);
        errorText.setVisible(false);
    }

    @FXML
    void researchTopProduct(ActionEvent event) {
        int id = getSelectedId();

        if (id == -1) {
            Utils.popUp("Please select a row");
            return;
        }

        try {
            switch(currentExecution) {
                case TOP_PRODUCT_CITY:
                    TopProductCity topProductCity = new CityManager().getTopProduct(id);
        
                    if (topProductCity.isEmpty()) {
                        Utils.popUp("No product found for this city");
                    } else {
                        setTableItems(FXCollections.observableArrayList(topProductCity.toTableEntry()));
                        setColumnsNames(currentExecution.getOutputs());
                        setButtons();
                    }
                    break;
                case TOP_PRODUCT_CLIENT:
                    TopProductClient topProductClient = new UserManager().getTopProduct(id);

                    if (topProductClient.isEmpty()) {
                        Utils.popUp("No product found for this user");
                    } else {
                        setTableItems(FXCollections.observableArrayList(topProductClient.toTableEntry()));
                        setColumnsNames(currentExecution.getOutputs());
                        setButtons();
                    }
                    break;
                default:
                    break;
            }
        } catch(SQLException e) {
            Utils.popUp("An error occured while trying to access the database");
        }
    }

    private int getSelectedId() {
        TableEntry row = table.getSelectionModel().getSelectedItem();

        if (row == null) {
            return -1;
        }

        return Integer.parseInt(row.getColumn1());
    }
}