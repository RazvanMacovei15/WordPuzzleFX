package razvan.wordpuzzlefx.WordPuzzleUTILS.UIPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import razvan.wordpuzzlefx.WordPuzzleUTILS.Domain.*;
import razvan.wordpuzzlefx.WordPuzzleUTILS.MAIN.BackgroundTask;
import razvan.wordpuzzlefx.WordPuzzleUTILS.Domain.WordsDictionary;


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
    private File file = null;
    private Map<String, String> dictionaryPaths=null;


    public void resetCircleColors(Map<String, MyCircle> map){
        for (Map.Entry<String, MyCircle> entry : map.entrySet()) {
            entry.getValue().getCircleNode().setFill(Color.GREY);
        }
    }

    public void onButtonClicked() {
        if (dictionary == null) {
            System.out.println("please generate a dictionary!");
        } else {
            new Thread (() -> {
                Map<String, MyCircle> circles = dictionary.getMyCirclesMap();
                resetCircleColors(circles);
                GraphicsContext gc = canvas.getGraphicsContext2D();
                BackgroundTask backgroundTask = new BackgroundTask(canvasBorderPane,canvas.getGraphicsContext2D());
                String start = textField1.getText().trim();
                String end = textField2.getText().trim();
                backgroundTask.findShortestPath(myGraph, circles.get(start), circles.get(end), gc, canvasBorderPane);
            }).start();

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
        // Create a BackgroundFill with the desired background color
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGRAY, null, null);

        // Create a Background with the BackgroundFill
        Background background = new Background(backgroundFill);

        // Set the Background to the BorderPane
        canvasBorderPane.setBackground(background);


        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Initialize ComboBox with dictionary names
        ObservableList<String> dictionaryNames = FXCollections.observableArrayList("Short", "Medium", "Long", "NOTORIOUS B.I.G.");
        dictionaryComboBox.setItems(dictionaryNames);

        // Create a map to store dictionary names and their corresponding file paths
        dictionaryPaths = new HashMap<>();
        dictionaryPaths.put("Short", "src/main/java/razvan/wordpuzzlefx/WordPuzzleUTILS/dictionaryShort.txt"); // Replace with your actual file path
        dictionaryPaths.put("Medium", "src/main/java/razvan/wordpuzzlefx/WordPuzzleUTILS/dictionaryMedium.txt"); // Replace with your actual file path
        dictionaryPaths.put("Long", "src/main/java/razvan/wordpuzzlefx/WordPuzzleUTILS/dictionaryLong.txt"); // Replace with your actual file path
        dictionaryPaths.put("NOTORIOUS B.I.G.", "src/main/java/razvan/wordpuzzlefx/WordPuzzleUTILS/BIGDICKTIONARY.txt"); // Replace with your actual file path

        //addEvent handler
        dictionaryComboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadCurrentlySelectedDictionary();
            }
        });
    }

    public File loadDictionary(Map<String, String> dictionaryPaths, String dictionaryName) {
        if (dictionaryName.equals("NOTORIOUS B.I.G.")) {
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

            myGraph = myGraphBuilder.buildGraphFromWordsDictionaryCircles(dictionary, canvas);

            return new File(dictionaryPaths.get(dictionaryName));
        }
        return new File(dictionaryPaths.get(dictionaryName));
    }

    public void reset(){
        resetCanvas();
        loadCurrentlySelectedDictionary();
    }

    private void loadCurrentlySelectedDictionary() {
        String selectedDictionary = dictionaryComboBox.getValue();
        if (selectedDictionary != null) {
            // Load the file based on the selected dictionary
            file = loadDictionary(dictionaryPaths, selectedDictionary);
            if (file != null) {

                // Add your logic to handle the loaded file
            } else {
                System.out.println("No file found for dictionary: " + selectedDictionary);
            }
        }
    }
}
