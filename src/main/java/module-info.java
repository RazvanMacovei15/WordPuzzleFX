module razvan.wordpuzzlefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens razvan.wordpuzzlefx.WordPuzzleUTILS.MAIN;
    exports razvan.wordpuzzlefx.WordPuzzleUTILS.MAIN to javafx.graphics;
    opens razvan.wordpuzzlefx.WordPuzzleUTILS.UIPackage;
    exports razvan.wordpuzzlefx.WordPuzzleUTILS.UIPackage to javafx.fxml;
}