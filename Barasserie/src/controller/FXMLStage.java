package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FXMLStage {
    private static final String ICON = "/picture/drakkarys.jpg";

    private static FXMLStage instance;

    private static Stage stage;

    private FXMLStage() { }

    public static FXMLStage getInstance() {
        if (instance == null) {
            instance = new FXMLStage();
        }
        return instance;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        FXMLStage.stage = stage;
    }

    public void load(String FXMLPath, String title) throws IOException, NullPointerException {
        if (stage == null) {
            throw new NullPointerException("Stage is null");
        }

        Parent root = FXMLLoader.load(getClass().getResource(FXMLPath));
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(ICON));
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
