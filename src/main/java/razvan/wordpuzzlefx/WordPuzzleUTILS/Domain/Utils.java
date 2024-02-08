package razvan.wordpuzzlefx.WordPuzzleUTILS.Domain;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Utils {

    //method to return a list where list[0] is number of rows, list[1] is circleX and list[2] is circleY nad list[3] is square size
    public static List<Double> makeGridPaneMatrix(int n, BorderPane borderPane) {
        double canvasWidth = borderPane.getWidth();
        double canvasHeight = borderPane.getHeight();

        //find out number of rows and columns for the canvas matrix
        double numberOfRows = Math.sqrt(n);
        System.out.println(numberOfRows);
        // Calculate remainder using the modulus operator
        double remainder = numberOfRows / 10;
        System.out.println(remainder);
        if (remainder != 0) {
            if (remainder > 0.5) {
                numberOfRows = Math.round(numberOfRows) + 1;
                System.out.println(numberOfRows);
            }
        }

        //calculate the size of each matrix square
        double squareXSize = canvasWidth / numberOfRows;
        double squareYSize = canvasHeight / numberOfRows;

        //calculate the center of these squares
        double circleCenterX = squareXSize / 2;
        double circleCenterY = squareYSize / 2;

        double circleRadius = squareYSize / 8;

        List<Double> parameters = new ArrayList<>();
        parameters.add(numberOfRows);
        parameters.add(circleCenterX);
        parameters.add(circleCenterY);
        parameters.add(squareXSize);
        parameters.add(circleRadius);

        return parameters;
    }

    //fastest path instead of shortest path
    public static void showFastestPath(List<MyCircle> path, Canvas canvas) {
        //TODO
    }
    //show small canvas where the currently shortest path is displayed
    public static void showShortestPath(List<MyCircle> path, Canvas canvas) {
        //TODO
    }



    public static void organiseGraphInConcentricCircles(List<MyCircle> circleList, BorderPane borderPane) {
        double centerX = borderPane.getWidth() / 2;
        double centerY = borderPane.getHeight() / 2;
        int size = circleList.size();
        System.out.println("size: " + size);



        double numberOfConcentricCircles = (double) size/ 4;
        System.out.println("number of concentric circles: " + numberOfConcentricCircles);

        double remainder = numberOfConcentricCircles / 10;
        System.out.println(remainder);
        if (remainder != 0) {
            if (remainder > 0.5) {
                numberOfConcentricCircles = Math.round(numberOfConcentricCircles) + 1;
                System.out.println(numberOfConcentricCircles);
            }
        }
        double radiusFromCenter = borderPane.getWidth() / (2 * (numberOfConcentricCircles + 2));
        double baseRadius = radiusFromCenter;
        double circleRadius = baseRadius/3;
        int index = 0;

        double angle = 0;
        double rotationAngle = 0;

        for (int k = 0; k < numberOfConcentricCircles; k++) {

            radiusFromCenter = baseRadius + k * baseRadius;
            for (int i = 0; i < 4 && index < size; i++) {

                angle = 2 * Math.PI * i / 4 + rotationAngle;

                double circleX = centerX + radiusFromCenter * Math.cos(angle);
                double circleY = centerY + radiusFromCenter * Math.sin(angle);

                Circle circle = circleList.get(index).getCircleNode();
                circle.setCenterX(circleX);
                circle.setCenterY(circleY);
                circle.setRadius(circleRadius);
                circle.setFill(Color.GREY);
                circle.setStroke(Color.BLACK);
                borderPane.getChildren().add(circle);
                circle.setUserData(circleList.get(index).getWord());

                System.out.println("circle " + circle.getUserData() + " " + circle.getCenterX() + " " + circle.getCenterY());

                circle.setOnMouseClicked(event -> {
                    System.out.println("clicked on " + circle.getUserData());
                });
                index++;
            }
            rotationAngle += Math.PI / 3;
        }
    }
}
