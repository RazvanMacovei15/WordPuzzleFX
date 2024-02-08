package razvan.wordpuzzlefx.WordPuzzleUTILS.Domain;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    //method to return a list where list[0] is number of rows, list[1] is circleX and list[2] is circleY nad list[3] is square size
    public static List<Double> makeGridPaneMatrix(int n, Canvas canvas){
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        //find out number of rows and columns for the canvas matrix
        double numberOfRows = Math.round(Math.sqrt(n));

        //calculate the size of each matrix square
        double squareXSize = canvasWidth/numberOfRows;
        double squareYSize = canvasHeight/numberOfRows;

        //calculate the center of these squares
        double circleCenterX = squareXSize/2;
        double circleCenterY = squareYSize/2;

        List<Double> parameters = new ArrayList<>();
        parameters.add(numberOfRows);
        parameters.add(circleCenterX);
        parameters.add(circleCenterY);
        parameters.add(squareXSize);

        return parameters;
    }
}
