package main.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import main.model.User;

public class FXMLStage {
    private static final String ICON = "/picture/drakkarys.jpg";

    private static FXMLStage instance;
    private static Stage stage;

    private FXMLStage() { }

    public static FXMLStage getInstance() {
        if (instance == null) {
            instance = new FXMLStage();
            stage = new Stage();
        }
        return instance;
    }
    
    public void load(String FXMLPath, String title) throws IOException {
        load(FXMLPath, title, null);
    }

    public void load(String FXMLPath, String title, User user) throws IOException, IllegalStateException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLPath));
        Parent root = loader.load();

        if (user != null) {
            ModificationController controller = loader.getController();
            controller.displayUser(user);
        }

        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(ICON));
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
