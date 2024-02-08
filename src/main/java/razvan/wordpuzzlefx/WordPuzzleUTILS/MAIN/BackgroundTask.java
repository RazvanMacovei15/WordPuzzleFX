package razvan.wordpuzzlefx.WordPuzzleUTILS.MAIN;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import razvan.wordpuzzlefx.WordPuzzleUTILS.Domain.MyCircle;
import razvan.wordpuzzlefx.WordPuzzleUTILS.Domain.MyPathfinder;

import java.util.List;

public class BackgroundTask extends MyPathfinder {
    BorderPane canvasBorderPane;
    private final GraphicsContext gc;

    public BackgroundTask(BorderPane canvasBorderPane, GraphicsContext gc) {
        this.canvasBorderPane = canvasBorderPane;
        this.gc = gc;
    }

    public void drawMyCircle(MyCircle myCircle, GraphicsContext gc) {

    }

    private void changeColor(Circle myCircle, String color) {
        myCircle.setFill(Color.valueOf(color));
    }

    @Override
    public void highlightNeighbours(List<MyCircle> neighbours) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (MyCircle myCircle : neighbours) {
            myCircle.getCircleNode().setFill(Color.PURPLE);
        }
    }

    @Override
    public void highlightNode(MyCircle node) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void highlightStartNode(MyCircle node) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        node.getCircleNode().setFill(Color.DARKGRAY);
    }

    @Override
    public void highlightEndNode(MyCircle node) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        node.getCircleNode().setFill(Color.BLACK);
    }

    @Override
    protected void highlightCurrentNode(MyCircle node) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        node.getCircleNode().setFill(Color.BLACK);
    }

    @Override
    protected void returnToBaseColor(MyCircle node) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void returnNeighboursToBaseColor(List<MyCircle> neighbours) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void highlightPath(List<MyCircle> path, BorderPane canvasBorderPane) {
        new Thread(() -> {
            for (MyCircle myCircle : path) {
                ObservableList<Node> list = canvasBorderPane.getChildren();
                for (Node node : list) {
                    if (node instanceof Circle circle) {
                        if (circle.getUserData().equals(myCircle.getWord())) {
                            circle.setFill(Color.GREEN);
                            circle.setOnMouseClicked(event -> {
                                System.out.println("clicked on " + circle.getUserData());
                            });
                        }
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
