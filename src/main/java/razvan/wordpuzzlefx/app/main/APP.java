package razvan.wordpuzzlefx.app.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class APP extends Application {

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        APP.primaryStage = primaryStage;

        URL fxmlLocation = getClass().getResource("/razvan/wordpuzzlefx/MainScreen.fxml");
        assert fxmlLocation != null;
        Parent root = null;
        try {
            root = FXMLLoader.load(fxmlLocation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        double sceneWidth = 1000; // Set your desired width
        double sceneHeight = 700; // Set your desired height

        primaryStage.setScene(new Scene(root, sceneWidth, sceneHeight));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
