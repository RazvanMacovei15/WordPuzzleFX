package razvan.wordpuzzlefx.WordPuzzleUTILS;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ExitMode extends PlayMode{

    public ExitMode() throws IOException {
        super();
    }

    @Override
    void play() {
        System.exit(0);
    }
}
