package razvan.wordpuzzlefx.app.domain;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

public class Utils {

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

        double numberOfConcentricCircles = (double) size/ 4;

        double floor = Math.floor(numberOfConcentricCircles);
        double remainder = numberOfConcentricCircles - floor;

        if (remainder != 0) {
            if (remainder >= 0.5) {
                numberOfConcentricCircles = floor + 1;
            }
        }

        double radiusFromCenter = (borderPane.getWidth() / (2 * (numberOfConcentricCircles + 1)));
        double baseRadius = radiusFromCenter;
        double circleRadius = baseRadius/3.5;
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

//                circle.setOnMouseClicked(event -> {
//                    System.out.println("clicked on " + circle.getUserData());
//                });
                index++;
            }
            rotationAngle += Math.PI / 3;
        }
    }
}
