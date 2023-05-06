module pw.espana.kahootgourmet.server {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens pw.espana.kahootgourmet.server to javafx.fxml;
    exports pw.espana.kahootgourmet.server;
    opens pw.espana.kahootgourmet.server.controllers to javafx.fxml;
    exports pw.espana.kahootgourmet.server.controllers;
    exports pw.espana.kahootgourmet.commons.game;
    exports pw.espana.kahootgourmet.commons.messages;
}