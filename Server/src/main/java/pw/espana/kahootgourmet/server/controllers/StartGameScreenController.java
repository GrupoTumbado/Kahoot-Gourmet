package pw.espana.kahootgourmet.server.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pw.espana.kahootgourmet.server.ScreenSwitcher;
import pw.espana.kahootgourmet.server.ServerApplication;
import pw.espana.kahootgourmet.server.ServerUserThread;
import pw.espana.kahootgourmet.server.StateId;

import java.io.IOException;

public class StartGameScreenController {
    private Stage stage;
    @FXML
    private TextField txtPIN;
    @FXML
    private TableView<ServerUserThread> tableJugadores;
    @FXML
    private TableColumn<ServerUserThread, String> columnJugadores;

    public void onLoad() {
        txtPIN.setText(String.valueOf(ServerApplication.getPin()));
        tableJugadores.setItems(ServerApplication.getConnectedUsers());
        columnJugadores.setCellValueFactory(data -> data.getValue().usernameProperty());
    }

    @FXML
    protected void onReturnButtonClick(ActionEvent actionEvent) throws Exception {
        ServerApplication.closeServer();
        ScreenSwitcher.showMainScene();
    }

    @FXML
    protected void onStartButtonClick(ActionEvent actionEvent) throws IOException {
        ServerApplication.setState(StateId.SHOWING_QUESTION);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
