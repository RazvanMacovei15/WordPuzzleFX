package razvan.wordpuzzlefx.WordPuzzleUTILS.Domain;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import razvan.wordpuzzlefx.WordPuzzleUTILS.ConsoleAPP.WordsDictionary;

import java.io.IOException;
import java.util.*;

public class MyGraphBuilder {

    protected MyGraph myGraph;
    private Set<Circle> addedCircles = new HashSet<>();

    protected BorderPane canvasBorderPane;

    public MyGraphBuilder(BorderPane canvasBorderPane) {
        this.canvasBorderPane = canvasBorderPane;
    }

    public MyGraph buildGraphFromWordsDictionaryCircles(WordsDictionary wordsDictionary, Canvas canvas) {
        //get MyCircleMAP
        Map<String, MyCircle> myCircleMap = wordsDictionary.getMyCirclesMap();
        //get the words grouped by wildcard
        Map<MyCircle, List<MyCircle>> nodesByWildcard = wordsDictionary.getWordsGroupedByWildcard2();

        myGraph = new MyGraph(nodesByWildcard);
        Map<MyCircle, List<MyCircle>> nodesByWildcardCopy = new HashMap<>(nodesByWildcard);
        Collection<List<MyCircle>> wildcardValues = nodesByWildcardCopy.values();

        //get the parameters for the gridPane
        List<String> words = wordsDictionary.getWords();
        int size = words.size();

        List<Double> parameters = Utils.makeGridPaneMatrix(wordsDictionary.getWords().size(), canvasBorderPane);
        double rows = parameters.get(0);
        double circleX = parameters.get(1);
        double circleY = parameters.get(2);
        double squareSize = parameters.get(3);
        int nrOfCircles = 0;
        int index = 0;
        double radius = parameters.get(4);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows && nrOfCircles < size; j++) {
                String word = words.get(index);
                MyCircle circle = myCircleMap.get(word);
                drawMyCircle(circleX, circleY, circle, radius);
                index++;
                circleX += squareSize;
                nrOfCircles++;
                System.out.print("o ");
            }
            System.out.println();
            circleX = parameters.get(1);
            circleY += squareSize;
        }
        for (List<MyCircle> neighbouringWords : wildcardValues) {
            for (MyCircle word : neighbouringWords) {
                myGraph.addNode(word);
                System.out.print(word.getWord() + " ");
            }
            System.out.println();

            for (int i = 0; i < neighbouringWords.size() - 1; i++) {
                MyCircle a = neighbouringWords.get(i);


                for (int j = i + 1; j < neighbouringWords.size(); j++) {
                    MyCircle b = neighbouringWords.get(j);
                    myGraph.addEdge(a, b);

                    Platform.runLater(() -> {
                        drawEdgeBetweenTwoPoints(a.getCircleNode().getCenterX(), a.getCircleNode().getCenterY(), b.getCircleNode().getCenterX(), b.getCircleNode().getCenterY(), canvas.getGraphicsContext2D());
                    });
                }
            }
        }
        return myGraph;
    }

    private void drawEdgeBetweenTwoPoints(double Ax, double Ay, double Bx, double By, GraphicsContext gc) {
        gc.setStroke(Color.RED);
        gc.setLineWidth(1);
        gc.strokeLine(Ax, Ay, Bx, By);
    }

    private void drawMyCircle(double x, double y, MyCircle myCircle, double radius) {

        Circle circle = myCircle.getCircleNode();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(radius);
        circle.setFill(Color.GREY);
        circle.setUserData(myCircle.getWord());
        Platform.runLater(() -> {
            try {
                if (addedCircles.add(circle)) {
                    canvasBorderPane.getChildren().add(circle);
                } else {
                    System.out.println(myCircle.getWord());
                    // Handle the duplicate circle exception here
                    System.out.println("Duplicate circle detected. Skipping addition.");
                }
            } catch (Exception e) {
                // Handle other exceptions here
                e.printStackTrace();
            }
        });
    }

}
