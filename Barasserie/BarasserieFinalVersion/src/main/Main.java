package main;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import main.controller.FXMLStage;
import main.view.PopUp;
import main.data.SingletonConnection;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLStage.getInstance().load("/main/view/adminProfile.fxml", "Admin Profile");
        } catch (IOException | IllegalStateException e) {
            new PopUp().warning("Error while loading the admin profile");
        }
    }

    public static void main(String[] args) throws Exception {    
        launch(args);
        SingletonConnection.close();
    }
}
