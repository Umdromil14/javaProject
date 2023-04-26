


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Parent root;
        URL url = getClass().getResource("login.fxml");
        try {
            root = FXMLLoader.load(url);
            primaryStage.getIcons().add(new Image("/drakkarys.jpg"));
            primaryStage.setTitle("User Login");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("----------------------");
            System.out.println("Error: " + e.getMessage());
            System.out.println("----------------------");
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
