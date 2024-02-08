package razvan.wordpuzzlefx.WordPuzzleUTILS.MAIN;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Example extends Application {

    private static final int ITERATIONS = 10;
    private static final int SLEEP_DURATION = 1000; // in milliseconds

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Initial Text");

        StackPane root = new StackPane();
        root.getChildren().add(label);

        primaryStage.setTitle("Simple JavaFX App");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();

        new Thread(() -> {
            for (int i = 1; i <= ITERATIONS; i++) {
                // Update label text on the JavaFX application thread
                int finalI = i;
                Platform.runLater(() -> label.setText("Iteration " + finalI));

                try {
                    // Simulate a delay outside the JavaFX application thread
                    Thread.sleep(SLEEP_DURATION);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
