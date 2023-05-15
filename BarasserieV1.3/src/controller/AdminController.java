package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import business.*;
import tools.Utils;
import tools.DBOutput.*;
import tools.enumeration.Execution;
import tools.exception.InvalidInputException;


public class AdminController implements Initializable {

    private static Execution currentExecution;
    
    private CityManager cityManager;
    private UserManager userManager;
    private DocStatusManager docStatusManager;
    private InvoiceManager invoiceManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cityManager = new CityManager();
        userManager = new UserManager();
        docStatusManager = new DocStatusManager();
        invoiceManager = new InvoiceManager();
        setButtons();
        setComboBox();
    }
    //#region FXML
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
    private TextArea description;

    @FXML
    private DatePicker parameterStartingDate;

    @FXML
    private DatePicker parameterEndingDate;

    @FXML
    private ComboBox<String> parameterComboBox;

    @FXML
    private TextField parameterText;

    @FXML
    private Button researchButton;

    @FXML
    private TableView<TableEntry> table;
    //#endregion
    
    private void setComboBox() {
        try {
            List<String> docStatus = docStatusManager.getAllDocStatus();

            parameterComboBox.setItems(FXCollections.observableArrayList(docStatus));
        } catch (SQLException e) {
            Utils.popUp("An error occurred while getting the document status");
        }
    }

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

    private int getSelectedId() {
        TableEntry row = table.getSelectionModel().getSelectedItem();
        if (row == null) {
            return -1;
        }

        return Integer.parseInt(row.getColumn1());
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
            User user = userManager.getUser(id);

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
        int id = getSelectedId();
        if (id == -1) {
            Utils.popUp("Please select a row");
            return;
        }
        try {
            User user = userManager.getUser(id);
            if (user.isEmpty()) {
                Utils.popUp("No user found for this id");
            } else {
                FXMLStage.getInstance().load("/view/modificationUser.fxml", "Modification", user);
            }
        }
        catch(NullPointerException |IOException|SQLException e) {
            Utils.popUp("An error occured while trying to access the database");
            e.printStackTrace();
        }
        
    }

    //encore a check
    @FXML
    void delete(ActionEvent event) {
        int id = getSelectedId();
        if (id == -1) {
            Utils.popUp("Please select a row");
            return;
        }
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this user?", "Delete user", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION)
        {

        }
        // try {
        //     User user = userManager.getUser(id);
        //     System.out.println(user);
        //     if (user.isEmpty()) {
        //         Utils.popUp("No user found for this id");
        //     } else {
        //         userManager.deleteUser(user);
        //         Utils.popUp("User deleted");
        //     }
        // }
        // catch(NullPointerException |SQLException e) {
        //     Utils.popUp("An error occured while trying to access the database");
        //     e.printStackTrace();
        // }
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
                    TopProductCity topProductCity = cityManager.getTopProduct(id);
        
                    if (topProductCity.isEmpty()) {
                        Utils.popUp("No product found for this city");
                    } else {
                        setTableItems(FXCollections.observableArrayList(topProductCity.toTableEntry()));
                        setColumnsNames(currentExecution.getOutputs());
                        setButtons();
                    }
                    break;
                case TOP_PRODUCT_CLIENT:
                    TopProductClient topProductClient = userManager.getTopProduct(id);

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

    @FXML
    void researchCities(ActionEvent event) {
        try {
            List<City> cities = cityManager.getAllCities();
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
        }
    }

    @FXML
    void researchUsers(ActionEvent event) throws SQLException {
        try {
            List<User> users = userManager.getAllUsers();
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

    private void validateNotNullInput() throws InvalidInputException {
        if (parameterStartingDate.getValue() == null) {
            throw new InvalidInputException("Please select a starting date");
        }

        if (parameterEndingDate.getValue() == null) {
            throw new InvalidInputException("Please select an ending date");
        }

        if (parameterComboBox.getValue() == null) {
            throw new InvalidInputException("Please select a document status");
        }
    }

    private void validateIdInput(int id) throws InvalidInputException {
        if (id < 0) {
            throw new InvalidInputException("The ID must be a positive integer");
        }
    }


    @FXML
    void researchInvoice(ActionEvent event) throws SQLException {
        try {
            validateNotNullInput();
        } catch (InvalidInputException e) {
            Utils.popUp(e.getMessage());
            return;
        }
        List<Invoice> invoices = new ArrayList<>();
        List<TableEntry> tableEntries = new ArrayList<>();
        Integer id;
        String clientId = parameterText.getText().trim();
        Date start = Date.valueOf(parameterStartingDate.getValue());
        Date end = Date.valueOf(parameterEndingDate.getValue());
        String status = parameterComboBox.getValue();

        if (!clientId.isEmpty()) {
            try {
                id = Integer.parseInt(clientId);
                validateIdInput(id);
                invoices = invoiceManager.getInvoices(id, start, end, status);
            } catch (NumberFormatException | InvalidInputException e) {
                
                return;
            }     
        } 
        else {
            try {
                invoices = invoiceManager.getInvoices(null ,start, end, status);
            } catch (SQLException e) {
                Utils.popUp("The client ID must be a positive integer");
                return;
            }
        }

        for (Invoice invoice : invoices) {
            tableEntries.add(invoice.toTableEntry());
        }
        setTableItems(FXCollections.observableArrayList(tableEntries));
        setColumnsNames(Execution.INVOICE.getOutputs());
        setButtons();

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
}
