<<<<<<< HEAD
import javafx.application.Application;
import javafx.stage.Stage;
import controller.FXMLStage;

import data.SingletonConnection;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        FXMLStage.setStage(primaryStage);

        try {
            FXMLStage.getInstance().load("/view/adminProfile.fxml", "Admin Profile");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
        SingletonConnection.close();
    }
}
=======
import javafx.application.Application;
import javafx.stage.Stage;
import controller.FXMLStage;

import data.SingletonConnection;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        FXMLStage.setStage(primaryStage);

        try {
            FXMLStage.getInstance().load("/view/adminProfile.fxml", "Admin Profile");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
        SingletonConnection.close();
    }
}
>>>>>>> a3552b73418bdd63b5a8a8ec3eefd92e53f2f519
