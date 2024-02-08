package razvan.wordpuzzlefx.WordPuzzleUTILS.MAIN;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import razvan.wordpuzzlefx.WordPuzzleUTILS.Domain.MyCircle;
import razvan.wordpuzzlefx.WordPuzzleUTILS.Domain.MyPathfinder;

import java.util.List;
import java.util.Set;

import static razvan.wordpuzzlefx.WordPuzzleUTILS.Domain.MyGraphBuilder.drawEdgeBetweenTwoPoints;

public class BackgroundTask extends MyPathfinder {
    BorderPane canvasBorderPane;

    public BackgroundTask(BorderPane canvasBorderPane, GraphicsContext gc) {
        this.canvasBorderPane = canvasBorderPane;
    }

    @Override
    public void highlightNeighbours(List<MyCircle> neighbours) {
        for (MyCircle myCircle : neighbours) {
            myCircle.getCircleNode().setFill(Color.PURPLE);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void highlightNode(MyCircle node) {
        Paint color = node.getCircleNode().getFill();
        try {
            node.getCircleNode().setFill(Color.ORANGE);
            Thread.sleep(400);
            node.getCircleNode().setFill(color);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void highlightStartNode(MyCircle node) {
        node.getCircleNode().setFill(Color.DARKGRAY);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void highlightEndNode(MyCircle node) {
        node.getCircleNode().setFill(Color.BLACK);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void highlightCurrentNode(MyCircle node) {
        node.getCircleNode().setFill(Color.RED);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void returnToBaseColor(MyCircle node, Paint color) {
        node.getCircleNode().setFill(color);
    }

    @Override
    protected void returnNeighboursToBaseColor(List<MyCircle> neighbours) {
        for (MyCircle myCircle : neighbours) {
            myCircle.getCircleNode().setFill(Color.GREY);
        }
    }

    @Override
    protected void highlightPath(List<MyCircle> path, GraphicsContext gc, Set<MyCircle> pathSet, BorderPane canvasBorderPane) {
        List<Node> children = canvasBorderPane.getChildren();
        for(Node node: children){
            if(node instanceof Circle circle){
                if(!pathSet.contains(circle.getUserData())){
                    circle.setFill(Color.LIGHTGRAY);
                }
            }
        }
        MyCircle start = path.getFirst();
        double radius = start.getCircleNode().getRadius();
        int number = (int) (radius*2/3);
        MyCircle end = path.getLast();
        //TODO implement a more elegant solution for this
        for (int i = 0; i < path.size() + 1; i++) {
            MyCircle myCircle = path.get(i);
            myCircle.getCircleNode().setRadius(radius * 1.5);
            Text text = new Text(myCircle.getWord());
            text.setX(myCircle.getCircleNode().getCenterX()-radius * 1.5/2);
            text.setY(myCircle.getCircleNode().getCenterY());
            text.setFont(Font.font(number));
            Platform.runLater(() -> {
                canvasBorderPane.getChildren().add(text);
            });
            drawEdgeBetweenTwoPoints(myCircle.getCircleNode().getCenterX(), myCircle.getCircleNode().getCenterY(), path.get(i + 1).getCircleNode().getCenterX(), path.get(i + 1).getCircleNode().getCenterY(), gc, Color.BLUE);
            myCircle.getCircleNode().setFill(Color.GREEN);
            if(path.get(i+1).equals(end)){
                path.get(i+1).getCircleNode().setRadius(radius * 1.5);
                Text newText = new Text(path.get(i+1).getWord());
                newText.setX(path.get(i+1).getCircleNode().getCenterX()-radius * 1.5/2);
                newText.setY(path.get(i+1).getCircleNode().getCenterY());
                newText.setFont(Font.font(number));
                Platform.runLater(() -> {
                    canvasBorderPane.getChildren().add(newText);
                });
                path.get(i+1).getCircleNode().setFill(Color.GREEN);
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
