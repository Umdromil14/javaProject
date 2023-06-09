package main.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import main.model.*;
import main.business.*;
import main.exception.*;
import main.tools.enumeration.Execution;
import main.view.PopUp;

public class AdminController implements Initializable {
    private Execution currentExecution;
    private Execution currentResearch;
    
    private PopUp popUp;
    private AddressManager cityManager;
    private UserManager userManager;
    private DocStatusManager docStatusManager;
    private InvoiceManager invoiceManager;
    private ProductManager productManager;
    private LogoAnimationThread logoAnimationThread;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        popUp = new PopUp();
        cityManager = new AddressManager();
        userManager = new UserManager();
        docStatusManager = new DocStatusManager();
        invoiceManager = new InvoiceManager();
        productManager = new ProductManager();

        setButtons();
        setComboBox();

        logoAnimationThread = new LogoAnimationThread(this);
        logoAnimationThread.start();
    }

    @FXML
    private Menu researchMenu;

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
    private Text startMessage;

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

    @FXML
    private ImageView logo;

    public void setLogoOpacity(double opacity) {
        logo.setOpacity(opacity);
    }

    public void setStartMessageOpacity(double opacity) {
        startMessage.setOpacity(opacity);
    }
    
    private void setComboBox() {
        try {
            List<String> docStatus = docStatusManager.getAllDocStatus();
            parameterComboBox.setItems(FXCollections.observableArrayList(docStatus));
        } catch (DataAccessException e) {
            popUp.warning(e.getMessage());
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

    @FXML
    void start(MouseEvent event) {
        logoAnimationThread.interrupt();

        setLogoOpacity(1);
        startMessage.setVisible(false);
        researchMenu.setDisable(false);
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
            FXMLStage.getInstance().load("/main/view/signup.fxml", "Sign up");
        } catch (IOException | IllegalStateException e) {
            popUp.warning("An error occurred while loading the sign up page");
        }
    }

    @FXML
    void read(ActionEvent event) {
        int id = getSelectedId();
        if (id == -1) {
            popUp.warning("Please select a row");
            return;
        }

        try {
            User user = userManager.getUser(id);
            if (user.isEmpty()) {
                popUp.warning("No user found for this id");
            } else {
                setTableItems(FXCollections.observableArrayList(user.toTableEntry()));
                setColumnsNames(Execution.USER.getOutputs());
            }
        } catch (DataAccessException e) {
            popUp.warning(e.getMessage());
        }
    }

    @FXML
    void update(ActionEvent event) {
        int id = getSelectedId();
        if (id == -1) {
            popUp.warning("Please select a row");
            return;
        }

        try {
            User user = userManager.getUser(id);
            if (user.isEmpty()) {
                popUp.warning("No user found for this id");
            } else {
                FXMLStage.getInstance().load("/main/view/modificationUser.fxml", "Modification", user);
            }
        } catch (DataAccessException e) {
            popUp.warning(e.getMessage());
        } catch (IOException | IllegalStateException e) {
            popUp.warning("An error occured while loading the modification page");
        }
        
    }

    @FXML
    void delete(ActionEvent event) {
        int id = getSelectedId();
        if (id == -1) {
            popUp.warning("Please select a row");
            return;
        }

        if (popUp.validation("Do you really want to delete this user ?")) {
            try {
                User user = userManager.getUser(id);
                if (user.isEmpty()) {
                    popUp.warning("No user found for this id");
                } else {
                    userManager.deleteUser(user);
                    researchUsers(event);
                }
            } catch (DataAccessException e) {
                popUp.warning(e.getMessage());
            } catch (UserRestrictedException e){
                popUp.warning(e.getMessage());
            }
        }
    }

    @FXML
    void researchTopProduct(ActionEvent event) {
        int id = getSelectedId();
        if (id == -1) {
            popUp.warning("Please select a row");
            return;
        }

        try {
            switch(currentExecution) {
                case CITY:
                    TopProductCity topProductCity = cityManager.getTopProduct(id);
        
                    if (topProductCity.isEmpty()) {
                        popUp.warning("No product found for this city");
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
                        popUp.warning("No product found for this user");
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
        } catch (DataAccessException e) {
            popUp.warning(e.getMessage());
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
        } catch (DataAccessException e) {
            popUp.warning(e.getMessage());
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
        } catch (DataAccessException e) {
            popUp.warning(e.getMessage());
            
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
            popUp.warning(e.getMessage());
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
                popUp.warning("The client ID must be a positive integer");
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
        } catch (DataAccessException e) {
            popUp.warning(e.getMessage());
        }
    }

    private void researchProductProportion() {
        try {
            validateNotNullInput(false);
        } catch (InvalidInputException e) {
            popUp.warning(e.getMessage());
            return;
        }

        Date start = Date.valueOf(parameterStartDate.getValue());
        Date end = Date.valueOf(parameterEndDate.getValue());

        try {
            List<ProductProportion> productsProportion = productManager.getAllProductsQuantity(start, end);
            List<TableEntry> tableEntries = new ArrayList<>();

            for (ProductProportion product : productsProportion) {
                tableEntries.add(product.toTableEntry());
            }

            setTableItems(FXCollections.observableArrayList(tableEntries));
            setColumnsNames(Execution.PRODUCT_PROPORTION.getOutputs());
            description.setText(Execution.PRODUCT_PROPORTION.getDescription());
            setButtons();
        } catch (DataAccessException e) {
            popUp.warning("An error occured while trying to access the database");
        } catch (NegativeNumberException e) {
            popUp.warning(e.getMessage());
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
}
