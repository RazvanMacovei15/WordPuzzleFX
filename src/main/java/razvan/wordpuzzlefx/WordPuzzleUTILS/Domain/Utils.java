package razvan.wordpuzzlefx.WordPuzzleUTILS.Domain;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    //method to return a list where list[0] is number of rows, list[1] is circleX and list[2] is circleY nad list[3] is square size
    public static List<Double> makeGridPaneMatrix(int n, BorderPane borderPane){
        double canvasWidth = borderPane.getWidth();
        double canvasHeight = borderPane.getHeight();

        //find out number of rows and columns for the canvas matrix
        double numberOfRows = Math.sqrt(n);
        System.out.println(numberOfRows);
        // Calculate remainder using the modulus operator
        double remainder = numberOfRows / 10;
        System.out.println(remainder);
        if(remainder != 0){
            if(remainder > 0.5){
                numberOfRows = Math.round(numberOfRows) + 1;
                System.out.println(numberOfRows);
            }
        }

        //calculate the size of each matrix square
        double squareXSize = canvasWidth/numberOfRows;
        double squareYSize = canvasHeight/numberOfRows;

        //calculate the center of these squares
        double circleCenterX = squareXSize/2;
        double circleCenterY = squareYSize/2;

        double circleRadius = squareYSize/8;

        List<Double> parameters = new ArrayList<>();
        parameters.add(numberOfRows);
        parameters.add(circleCenterX);
        parameters.add(circleCenterY);
        parameters.add(squareXSize);
        parameters.add(circleRadius);

        return parameters;
    }
}
