package pw.espana.kahootgourmet.server.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import pw.espana.kahootgourmet.server.ServerApplication;
import pw.espana.kahootgourmet.server.ServerUserThread;

public class ScoreboardScreenController {
    private Stage stage;
    @FXML
    private TableView<ServerUserThread> tableJugadores;
    @FXML
    private TableColumn<ServerUserThread, String> columnJugadores;
    @FXML
    private TableColumn<ServerUserThread, String> columnPuntos;

    public void onLoad() {
        tableJugadores.setItems(ServerApplication.getConnectedUsers());
        columnJugadores.setCellValueFactory(data -> data.getValue().usernameProperty());
        columnPuntos.setCellValueFactory(data -> data.getValue().scoreProperty());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
