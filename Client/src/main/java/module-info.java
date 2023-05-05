module pw.espana.kahootgourmet.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens pw.espana.kahootgourmet.client to javafx.fxml;
    exports pw.espana.kahootgourmet.client;
    exports pw.espana.kahootgourmet.client.controllers;
    opens pw.espana.kahootgourmet.client.controllers to javafx.fxml;
}