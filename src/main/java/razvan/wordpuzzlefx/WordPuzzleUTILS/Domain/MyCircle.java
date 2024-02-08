package razvan.wordpuzzlefx.WordPuzzleUTILS.Domain;

import javafx.scene.shape.Circle;

import java.util.List;
import java.util.Objects;

public class MyCircle {
    protected Circle circleNode;
    protected String word;
    protected List<MyCircle> children;
    protected boolean isDrawn;

    public MyCircle(String word) {
        this.circleNode = new Circle();
        this.word = word;
        circleNode.setUserData(this.word);
        circleNode.setOnMouseClicked(e -> {
            System.out.println("clicked on " + this.word);
        });
        this.isDrawn = false;
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
