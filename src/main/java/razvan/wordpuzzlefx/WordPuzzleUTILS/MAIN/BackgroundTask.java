package razvan.wordpuzzlefx.WordPuzzleUTILS.MAIN;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
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
    public void checkVisitedNodes(Set<MyCircle> visitedNodes) {
        //TODO
        // Create a timeline to check the set every second
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> checkSetAndUpdateLabel(visitedNodes))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void checkSetAndUpdateLabel(Set<MyCircle> visitedNodes) {
        for (MyCircle myCircle : visitedNodes) {
            if (myCircle.isVisited() && !myCircle.isDrawn()) {
                drawXOnCircle(myCircle);
            }
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
        for (Node node : children) {
            if (node instanceof Circle circle) {
                if (!pathSet.contains(circle.getUserData())) {
                    circle.setFill(Color.GRAY);
                    circle.setStroke(Color.GRAY);
                }
            }
        }
        MyCircle start = path.getFirst();
        double radius = start.getCircleNode().getRadius();
        int number = (int) (radius);
        System.out.println(number);
        MyCircle end = path.getLast();
        //TODO implement a more elegant solution for this
        for (int i = 0; i < path.size() + 1; i++) {
            MyCircle myCircle = path.get(i);
            myCircle.getCircleNode().setRadius(radius * 2);
            Text text = new Text(myCircle.getWord());
            text.setX(myCircle.getCircleNode().getCenterX() - (radius * 2) / 2);
            text.setY(myCircle.getCircleNode().getCenterY() + (double) number /4);
            text.setFont(Font.font(number));
            Platform.runLater(() -> {
                canvasBorderPane.getChildren().add(text);
                canvasBorderPane.getChildren().remove(myCircle.getLineTwo());
                canvasBorderPane.getChildren().remove(myCircle.getLineOne());
            });
            drawEdgeBetweenTwoPoints(myCircle.getCircleNode().getCenterX(), myCircle.getCircleNode().getCenterY(), path.get(i + 1).getCircleNode().getCenterX(), path.get(i + 1).getCircleNode().getCenterY(), gc, Color.BLUE);
            myCircle.getCircleNode().setFill(Color.GREEN);
            myCircle.getCircleNode().setStroke(Color.BLUE);
            if (path.get(i + 1).equals(end)) {
                path.get(i + 1).getCircleNode().setRadius(radius * 2);
                Text newText = new Text(path.get(i + 1).getWord());
                newText.setX(path.get(i + 1).getCircleNode().getCenterX() - (radius * 2) / 2);
                newText.setY(path.get(i + 1).getCircleNode().getCenterY() + (double) number /4);
                newText.setFont(Font.font(number));
                int finalI = i;
                Platform.runLater(() -> {
                    canvasBorderPane.getChildren().add(newText);
                    canvasBorderPane.getChildren().remove(path.get(finalI + 1).getLineTwo());
                    canvasBorderPane.getChildren().remove(path.get(finalI + 1).getLineOne());
                });
                path.get(i + 1).getCircleNode().setFill(Color.GREEN);
                path.get(i + 1).getCircleNode().setStroke(Color.BLUE);
                break;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawXOnCircle(MyCircle myCircle) {
        myCircle.setDrawn(true);
        Line line1 = new Line(myCircle.getCircleNode().getCenterX() - myCircle.getCircleNode().getRadius(), myCircle.getCircleNode().getCenterY() - myCircle.getCircleNode().getRadius(), myCircle.getCircleNode().getCenterX() + myCircle.getCircleNode().getRadius(), myCircle.getCircleNode().getCenterY() + myCircle.getCircleNode().getRadius());
        Line line2 = new Line(myCircle.getCircleNode().getCenterX() - myCircle.getCircleNode().getRadius(), myCircle.getCircleNode().getCenterY() + myCircle.getCircleNode().getRadius(), myCircle.getCircleNode().getCenterX() + myCircle.getCircleNode().getRadius(), myCircle.getCircleNode().getCenterY() - myCircle.getCircleNode().getRadius());
        // Draw the X
        line1.setStroke(Color.RED);
        line2.setStroke(Color.RED);

        myCircle.setLineOne(line1);
        myCircle.setLineTwo(line2);

        Platform.runLater(() -> {
            canvasBorderPane.getChildren().add(line1);
            canvasBorderPane.getChildren().add(line2);
        });
    }
}
