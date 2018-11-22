package Vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EjecutarAdmin extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUIAdmin.fxml"));
        primaryStage.setScene(new Scene(root, 652 ,580));
        primaryStage.show();       
    }

    public static void main(String[] args) {
        launch(args);
    }
}
