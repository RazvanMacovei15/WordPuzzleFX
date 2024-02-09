package razvan.wordpuzzlefx.WordPuzzleUTILS.Domain;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.List;
import java.util.Objects;

public class MyCircle {
    protected Circle circleNode;
    protected String word;
    protected List<MyCircle> children;
    protected boolean isDrawn;
    private boolean isVisited;
    protected Line lineOne;
    protected Line lineTwo;


    public MyCircle(String word) {
        this.circleNode = new Circle();
        this.word = word;
        circleNode.setUserData(this.word);
        circleNode.setOnMouseClicked(e -> {
            System.out.println("clicked on " + this.word);
        });
        this.isDrawn = false;
        this.isVisited = false;
    }

    public Circle getCircleNode() {
        return circleNode;
    }

    public void setCircleNode(Circle circleNode) {
        this.circleNode = circleNode;
    }

    public String getWord() {
        return word;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public Line getLineOne() {
        return lineOne;
    }

    public void setLineOne(Line lineOne) {
        this.lineOne = lineOne;
    }

    public Line getLineTwo() {
        return lineTwo;
    }

    public void setLineTwo(Line lineTwo) {
        this.lineTwo = lineTwo;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<MyCircle> getChildren() {
        return children;
    }

    public void setChildren(List<MyCircle> children) {
        this.children = children;
    }

    public boolean isDrawn() {
        return isDrawn;
    }

    public void setDrawn(boolean drawn) {
        isDrawn = drawn;
    }

    @Override
    public String toString() {
        return "MyCircle{" +
                "word='" + word + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyCircle myCircle = (MyCircle) o;
        return Objects.equals(word, myCircle.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }
}
