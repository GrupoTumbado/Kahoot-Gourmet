package pw.espana.kahootgourmet.client.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pw.espana.kahootgourmet.client.ClientApplication;
import pw.espana.kahootgourmet.client.ClientThread;

import java.io.IOException;

public class LoginScreenScreenController extends ScoreboardScreenController {
    private Stage stage;
    @FXML
    private Label txtError;
    @FXML
    private TextField txtIP;
    @FXML
    private TextField txtPuerto;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtPIN;
    private ClientThread clientThread;

    @FXML
    protected void onStartButtonClick(ActionEvent actionEvent) throws IOException {
        clientThread = ClientApplication.joinServer(txtIP.getText(), Integer.parseInt(txtPuerto.getText()), txtUsuario.getText(), Integer.parseInt(txtPIN.getText()));
        clientThread.setLoginController(this);
    }

    public void setTxtError(String txtError) {
        Platform.runLater(() -> this.txtError.setText(txtError));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}