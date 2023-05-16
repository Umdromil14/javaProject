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

import business.*;
import tools.Utils;
import tools.DBOutput.*;
import tools.enumeration.Execution;
import tools.exception.InvalidInputException;
import tools.exception.NullStageException;


public class AdminController implements Initializable {
    private Execution currentExecution;
    private Execution currentResearch;
    
    private AddressManager cityManager;
    private UserManager userManager;
    private DocStatusManager docStatusManager;
    private InvoiceManager invoiceManager;
    private ProductManager productManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cityManager = new AddressManager();
        userManager = new UserManager();
        docStatusManager = new DocStatusManager();
        invoiceManager = new InvoiceManager();
        productManager = new ProductManager();

        setButtons();
        setComboBox();
    }

    @FXML
    private TableView<TableEntry> table;
    
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
    private Label researchText;

    @FXML
    private TextArea description;

    @FXML
    private DatePicker parameterStartDate;

    @FXML
    private DatePicker parameterEndDate;

    @FXML
    private ComboBox<String> parameterComboBox;

    @FXML
    private TextField parameterText;

    @FXML
    private Button researchButton;
    
    private void setComboBox() {
        try {
            List<String> docStatus = docStatusManager.getAllDocStatus();
            parameterComboBox.setItems(FXCollections.observableArrayList(docStatus));
        } catch (SQLException e) {
            Utils.warningPopUp("An error occurred while getting the document status");
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

    private void setResearchParameters(boolean isTextDisabled, boolean isStartDateDisabled, boolean isEndDateDisabled, boolean isComboBoxDisabled) {
        parameterText.setDisable(isTextDisabled);
        parameterText.clear();

        parameterStartDate.setDisable(isStartDateDisabled);
        parameterStartDate.setValue(null);

        parameterEndDate.setDisable(isEndDateDisabled);
        parameterEndDate.setValue(null);

        parameterComboBox.setDisable(isComboBoxDisabled);
        parameterComboBox.setValue(null);

        researchButton.setDisable(false);
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
            FXMLStage.getInstance().load("/view/signup.fxml", "Sign up");
        } catch (IOException | NullStageException e) {
            Utils.warningPopUp("An error occurred while loading the sign up page");
        }
    }

    @FXML
    void read(ActionEvent event) {
        int id = getSelectedId();
        if (id == -1) {
            Utils.warningPopUp("Please select a row");
            return;
        }

        try {
            User user = userManager.getUser(id);
            if (user.isEmpty()) {
                Utils.warningPopUp("No user found for this id");
            } else {
                setTableItems(FXCollections.observableArrayList(user.toTableEntry()));
                setColumnsNames(Execution.USER.getOutputs());
            }
        } catch (SQLException e) {
            Utils.warningPopUp("An error occured while trying to access the database");
        }
    }

    @FXML
    void update(ActionEvent event) {
        int id = getSelectedId();
        if (id == -1) {
            Utils.warningPopUp("Please select a row");
            return;
        }

        try {
            User user = userManager.getUser(id);
            if (user.isEmpty()) {
                Utils.warningPopUp("No user found for this id");
            } else {
                FXMLStage.getInstance().load("/view/modificationUser.fxml", "Modification", user);
            }
        } catch (SQLException e) {
            Utils.warningPopUp("An error occured while trying to access the database");
        } catch (IOException | NullStageException e) {
            Utils.warningPopUp("An error occured while loading the modification page");
        }
        
    }

    @FXML
    void delete(ActionEvent event) {
        int id = getSelectedId();
        if (id == -1) {
            Utils.warningPopUp("Please select a row");
            return;
        }

        if (Utils.validationPopUp("do you want to delete this user ?", "delete user")) {
            try {
                User user = userManager.getUser(id);
                if (user.isEmpty()) {
                    Utils.warningPopUp("No user found for this id");
                } else {
                    userManager.deleteUser(user);
                    researchUsers(event);
                }
            } catch (SQLException e) {
                Utils.warningPopUp("An error occured while trying to access the database");
            }
        }
    }

    @FXML
    void researchTopProduct(ActionEvent event) {
        int id = getSelectedId();
        if (id == -1) {
            Utils.warningPopUp("Please select a row");
            return;
        }

        try {
            switch(currentExecution) {
                case CITY:
                    TopProductCity topProductCity = cityManager.getTopProduct(id);
        
                    if (topProductCity.isEmpty()) {
                        Utils.warningPopUp("No product found for this city");
                    } else {
                        setTableItems(FXCollections.observableArrayList(topProductCity.toTableEntry()));
                        setColumnsNames(Execution.TOP_PRODUCT_CITY.getOutputs());
                        description.setText(Execution.TOP_PRODUCT_CITY.getDescription());
                        setButtons();
                    }
                    break;
                case USER:
                    TopProductClient topProductClient = userManager.getTopProduct(id);

                    if (topProductClient.isEmpty()) {
                        Utils.warningPopUp("No product found for this user");
                    } else {
                        setTableItems(FXCollections.observableArrayList(topProductClient.toTableEntry()));
                        setColumnsNames(Execution.TOP_PRODUCT_CLIENT.getOutputs());
                        description.setText(Execution.TOP_PRODUCT_CLIENT.getDescription());
                        setButtons();
                    }
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            Utils.warningPopUp("An error occured while trying to access the database");
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

            currentExecution = Execution.CITY;

            setTableItems(FXCollections.observableArrayList(tableEntries));
            setColumnsNames(currentExecution.getOutputs());
            setButtons(true, true, true, true, false);
        } catch (SQLException e) {
            Utils.warningPopUp("An error occured while trying to access the database");
        }
    }

    @FXML
    void researchUsers(ActionEvent event) {
        try {
            List<User> users = userManager.getAllUsers();
            List<TableEntry> tableEntries = new ArrayList<>();

            for (User user : users) {
                tableEntries.add(user.toTableEntry());
            }

            currentExecution = Execution.USER;

            setTableItems(FXCollections.observableArrayList(tableEntries));
            setColumnsNames(currentExecution.getOutputs());
            setButtons(false, false, false, false, false);
        } catch (SQLException e) {
            Utils.warningPopUp("An error occured while trying to access the database");
            
        }
    }

    @FXML
    void researchInvoices(ActionEvent event) {
        researchText.setText("RESEARCH INVOICES");
        setResearchParameters(false, false, false, false);

        currentResearch = Execution.INVOICE;
    }

    @FXML
    void researchProductsProportion(ActionEvent event) {
        researchText.setText("RESEARCH PRODUCTS PROPORTION");
        setResearchParameters(true, false, false, true);

        currentResearch = Execution.PRODUCT_PROPORTION;
    }

    @FXML
    void research(ActionEvent event) {
        switch(currentResearch) {
            case INVOICE:
                researchInvoice();
                break;
            case PRODUCT_PROPORTION:
                researchProductProportion();
                break;
            default:
                break;
        }
    }

    private void validateNotNullInput(boolean hasStatus) throws InvalidInputException {
        if (parameterStartDate.getValue() == null) {
            throw new InvalidInputException("Please select a starting date");
        }

        if (parameterEndDate.getValue() == null) {
            throw new InvalidInputException("Please select an ending date");
        }

        if (hasStatus && parameterComboBox.getValue() == null) {
            throw new InvalidInputException("Please select a document status");
        }
    }

    private void validateIdInput(int id) throws InvalidInputException {
        if (id < 0) {
            throw new InvalidInputException("The ID must be a positive integer");
        }
    }

    private void researchInvoice() {
        try {
            validateNotNullInput(true);
        } catch (InvalidInputException e) {
            Utils.warningPopUp(e.getMessage());
            return;
        }

        String clientId = parameterText.getText().trim();
        Date start = Date.valueOf(parameterStartDate.getValue());
        Date end = Date.valueOf(parameterEndDate.getValue());
        String status = parameterComboBox.getValue();

        Integer id = null;
        if (!clientId.isEmpty()) {
            try {
                id = Integer.parseInt(clientId);
                validateIdInput(id);
            } catch (NumberFormatException | InvalidInputException e) {
                Utils.warningPopUp("The client ID must be a positive integer");
                return;
            }
        }

        try {
            List<Invoice> invoices = invoiceManager.getInvoices(id, start, end, status);
            List<TableEntry> tableEntries = new ArrayList<>();

            for (Invoice invoice : invoices) {
                tableEntries.add(invoice.toTableEntry());
            }
            
            setTableItems(FXCollections.observableArrayList(tableEntries));
            setColumnsNames(Execution.INVOICE.getOutputs());
            description.setText(Execution.INVOICE.getDescription());
            setButtons();
    
            currentExecution = Execution.INVOICE;
        } catch (SQLException e) {
            Utils.warningPopUp("An error occured while trying to access the database");
        }
    }

    private void researchProductProportion() {
        try {
            validateNotNullInput(false);
        } catch (InvalidInputException e) {
            Utils.warningPopUp(e.getMessage());
            return;
        }

        Date start = Date.valueOf(parameterStartDate.getValue());
        Date end = Date.valueOf(parameterEndDate.getValue());

        try {
            List<ProductProportion> productsProportion = productManager.getAllProductsQuantity(start, end);
            if (productsProportion.isEmpty()) {
                Utils.warningPopUp("No product found for this period");
                return;
            }

            FXMLStage.getInstance().setDisable(true);
            new ProductProportionThread(productsProportion, this).start();
        } catch (SQLException e) {
            Utils.warningPopUp("An error occured while trying to access the database");
        }
    }

    public void displayProductsProportion(List<ProductProportion> productsProportion) {
        List<TableEntry> tableEntries = new ArrayList<>();
        for (ProductProportion product : productsProportion) {
            tableEntries.add(product.toTableEntry());
        }

        setTableItems(FXCollections.observableArrayList(tableEntries));
        setColumnsNames(Execution.PRODUCT_PROPORTION.getOutputs());
        description.setText(Execution.PRODUCT_PROPORTION.getDescription());
        setButtons();

        FXMLStage.getInstance().setDisable(false);
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
