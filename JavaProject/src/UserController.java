


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

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
        Stage stage;
        Parent root;
        try {
            stage = (Stage) modifcationButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("modification.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("modification");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
