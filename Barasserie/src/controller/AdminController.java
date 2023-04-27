package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AdminController {

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
    void changeStatus1(ActionEvent event) {
        parameterMenu.setText("Status 1");
    }

    @FXML
    void changeStatus2(ActionEvent event) {
        parameterMenu.setText("Status 2");
    }

    @FXML
    void cityResearch(ActionEvent event) {
        initialize(false);

        parameterText.setPromptText("City id");
        parameterText.setDisable(false);
        parameterDate.setDisable(true);
        parameterMenu.setDisable(true);
        researchButton.setDisable(false);
    }

    @FXML
    void clientResearch(ActionEvent event) {
        initialize(false);

        parameterText.setPromptText("Client id");
        parameterText.setDisable(false);
        parameterDate.setDisable(false);
        parameterMenu.setDisable(true);
        researchButton.setDisable(false);
    }

    @FXML
    void research(ActionEvent event) {
        initialize(true);
    }

    @FXML
    void researchInvoice(ActionEvent event) {
        initialize(false);

        parameterText.setPromptText("Client id (optional)");
        parameterText.setDisable(false);
        parameterDate.setDisable(false);
        parameterMenu.setDisable(false);
        researchButton.setDisable(false);
    }

    public void initialize(boolean save) {
        if (!save) {
            parameterText.setText(null);
            parameterDate.setValue(null);
            parameterMenu.setText("Status");
        }

        parameterText.setPromptText(null);
        parameterText.setDisable(true);
        parameterDate.setDisable(true);
        parameterMenu.setDisable(true);
        researchButton.setDisable(true);
    }

}
