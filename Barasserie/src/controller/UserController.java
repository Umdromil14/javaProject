package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserController {

    @FXML
    private Button DeleteButton;

    @FXML
    private Button modifcationButton;

    @FXML
    void DeleteListener(ActionEvent event) {
        //need a confirmation option pane with a password field to confirm the deletion
    }

    @FXML
    void ModificationListener(ActionEvent event) {
        try {
            FXMLStage.getInstance().load("/view/signup.fxml", "Modification");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
