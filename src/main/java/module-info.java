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


    opens razvan.wordpuzzlefx.app.main;
    exports razvan.wordpuzzlefx.app.main to javafx.graphics;
    opens razvan.wordpuzzlefx.app.ui;
    exports razvan.wordpuzzlefx.app.ui to javafx.fxml;

    opens razvan.wordpuzzlefx.app.domain;
    exports razvan.wordpuzzlefx.app.domain to javafx.graphics;
    opens razvan.wordpuzzlefx.app.console;
    exports razvan.wordpuzzlefx.app.console to javafx.graphics;
}