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

    //TODO message not possible to delete User when doc
    //TODO interfaces add methods from db
    //TODO check DBOutput et Utils
    public static void main(String[] args) throws Exception {    
        launch(args);
        //? passe par manager ?
        SingletonConnection.close();
    }
}
