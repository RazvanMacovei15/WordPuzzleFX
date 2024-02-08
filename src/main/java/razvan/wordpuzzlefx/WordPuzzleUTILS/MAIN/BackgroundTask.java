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

    private void changeColor(Circle myCircle, String color) {
        myCircle.setFill(Color.valueOf(color));
    }

    @Override
    public void highlightNeighbours(List<MyCircle> neighbours) {
        try {
            for (MyCircle myCircle : neighbours) {
                myCircle.getCircleNode().setFill(Color.PURPLE);
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void highlightNode(MyCircle node) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        node.getCircleNode().setFill(Color.ORANGE);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        node.getCircleNode().setFill(Color.GREY);
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
        node.getCircleNode().setFill(Color.RED);
    }

    @Override
    protected void returnToBaseColor(MyCircle node) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        node.getCircleNode().setFill(Color.GREY);
    }

    @Override
    protected void returnNeighboursToBaseColor(List<MyCircle> neighbours) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (MyCircle myCircle : neighbours) {
            myCircle.getCircleNode().setFill(Color.GREY);
        }
    }

    @Override
    protected void highlightPath(List<MyCircle> path, BorderPane canvasBorderPane) {

        for (MyCircle myCircle : path) {
            myCircle.getCircleNode().setFill(Color.GREEN);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
