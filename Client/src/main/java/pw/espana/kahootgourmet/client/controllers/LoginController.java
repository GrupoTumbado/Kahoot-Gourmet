package pw.espana.kahootgourmet.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pw.espana.kahootgourmet.client.ClientApplication;

import java.io.IOException;
import java.util.Objects;

public class LoginController extends PosicionController {
    @FXML
    private TextField txtIP;
    @FXML
    private TextField txtPuerto;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtPIN;

    @FXML
    protected void onStartButtonClick(ActionEvent actionEvent) throws IOException {
        ClientApplication.joinServer(txtIP.getText(), Integer.parseInt(txtPuerto.getText()), txtUsuario.getText(), Integer.parseInt(txtPIN.getText()));
    }
}