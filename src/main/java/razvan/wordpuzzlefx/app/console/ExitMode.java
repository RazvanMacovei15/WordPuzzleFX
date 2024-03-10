package razvan.wordpuzzlefx.app.console;

import java.io.IOException;

public class ExitMode extends PlayMode{

    public ExitMode() throws IOException {
        super();
    }

    @Override
    void play() {
        System.exit(0);
    }
}
