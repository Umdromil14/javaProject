import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

import controller.FXMLStage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        FXMLStage.setStage(primaryStage);

        try {
            FXMLStage.getInstance().load("/view/login.fxml", "User Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
