package razvan.wordpuzzlefx.WordPuzzleUTILS;

public class InfinityKey {
    private static int counter = 0;
    private int id;

    public InfinityKey() {
        id = counter++;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InfinityKey other = (InfinityKey) obj;
        return id == other.id;
    }
}
