package razvan.wordpuzzlefx.WordPuzzleUTILS.UIPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import razvan.wordpuzzlefx.WordPuzzleUTILS.Domain.*;
import razvan.wordpuzzlefx.WordPuzzleUTILS.MAIN.BackgroundTask;
import razvan.wordpuzzlefx.WordPuzzleUTILS.ConsoleAPP.WordsDictionary;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Controller {
    @FXML
    protected BorderPane canvasBorderPane;
    @FXML
    private ComboBox<String> dictionaryComboBox;
    private WordsDictionary dictionary;
    private MyGraph myGraph;
    private MyGraphBuilder myGraphBuilder;
    private Canvas canvas;
    @FXML
    TextField textField1;
    @FXML
    TextField textField2;

    public void resetCircleColors(BorderPane canvasBorderPane){
        for (Node node : canvasBorderPane.getChildren()) {
            if (node instanceof Circle circle) {
                circle.setFill(Color.GREY);
            }
        }
    }

    public void onButtonClicked() {
        if (dictionary == null) {
            System.out.println("please generate a dictionary!");
        } else {
            resetCircleColors(canvasBorderPane);
            Map<String, MyCircle> circles = dictionary.getMyCirclesMap();
            BackgroundTask backgroundTask = new BackgroundTask(canvasBorderPane,canvas.getGraphicsContext2D());
            String start = textField1.getText().trim();
            String end = textField2.getText().trim();
            backgroundTask.findShortestPath(myGraph, circles.get(start), circles.get(end), canvasBorderPane);
        }
    }

    public void resetCanvas() {
        canvasBorderPane.getChildren().clear();
        canvas = new Canvas();
        canvas.setWidth(700);
        canvas.setHeight(700);
        canvasBorderPane.getChildren().add(canvas);
    }

    public void initialize() {
        resetCanvas();
        myGraphBuilder = new MyGraphBuilder(canvasBorderPane);

        canvasBorderPane.setMinWidth(700);
        canvasBorderPane.setMinHeight(700);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Initialize ComboBox with dictionary names
        ObservableList<String> dictionaryNames = FXCollections.observableArrayList("Short", "Medium", "Long", "NOTORIOUS B.I.G.");
        dictionaryComboBox.setItems(dictionaryNames);

        // Create a map to store dictionary names and their corresponding file paths
        Map<String, String> dictionaryPaths = new HashMap<>();
        dictionaryPaths.put("Short", "src/main/java/razvan/wordpuzzlefx/WordPuzzleUTILS/dictionaryShort.txt"); // Replace with your actual file path
        dictionaryPaths.put("Medium", "src/main/java/razvan/wordpuzzlefx/WordPuzzleUTILS/dictionaryMedium.txt"); // Replace with your actual file path
        dictionaryPaths.put("Long", "src/main/java/razvan/wordpuzzlefx/WordPuzzleUTILS/dictionaryLong.txt"); // Replace with your actual file path
        dictionaryPaths.put("NOTORIOUS B.I.G.", "src/main/java/razvan/wordpuzzlefx/WordPuzzleUTILS/BIGDICKTIONARY.txt"); // Replace with your actual file path

        //addEvent handler
        dictionaryComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectedDictionary = dictionaryComboBox.getValue();
                if (selectedDictionary != null) {
                    // Load the file based on the selected dictionary
                    File file = loadDictionary(dictionaryPaths, selectedDictionary);
                    if (file != null) {
                        System.out.println("Loading file for dictionary " + selectedDictionary + ": " + file.getAbsolutePath());
                        // Add your logic to handle the loaded file
                    } else {
                        System.out.println("No file found for dictionary: " + selectedDictionary);
                    }
                }
            }
        });
    }

    public File loadDictionary(Map<String, String> dictionaryPaths, String dictionaryName) {
        if (dictionaryName.equals("NOTORIOUS B.I.G.") || dictionaryName.equals("Long")) {
            System.out.println("Too Many Words in the dictionary! ---> WIP");
            String text = "Too Many Words in the dictionary! ---> WIP";
            Label label = new Label(text);
            canvasBorderPane.getChildren().clear();
            canvasBorderPane.setCenter(label);
        } else {
            resetCanvas();

            try {
                dictionary = new WordsDictionary(dictionaryPaths.get(dictionaryName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            myGraph = new MyGraph(dictionary.getWordsGroupedByWildcard2());

            myGraph = myGraphBuilder.buildGraphFromWordsDictionaryCircles(dictionary, canvas);

            return new File(dictionaryPaths.get(dictionaryName));
        }
        return new File(dictionaryPaths.get(dictionaryName));
    }
}
