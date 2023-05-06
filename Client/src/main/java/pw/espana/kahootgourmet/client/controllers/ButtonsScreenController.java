package pw.espana.kahootgourmet.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pw.espana.kahootgourmet.client.ClientApplication;

import java.io.IOException;

public class ButtonsScreenController {
    private Stage stage;
    @FXML
    private Button btnRed;
    @FXML
    private Button btnBlue;
    @FXML
    private Button btnYellow;
    @FXML
    private Button btnGreen;

    public void onRedButtonClick(ActionEvent actionEvent) throws IOException {
        ClientApplication.sendAnswer(0);
        disableButtons();
        btnRed.setOpacity(1);
    }

    public void onBlueButtonClick(ActionEvent actionEvent) throws IOException {
        ClientApplication.sendAnswer(1);
        disableButtons();
        btnBlue.setOpacity(1);
    }

    public void onYellowButtonClick(ActionEvent actionEvent) throws IOException {
        ClientApplication.sendAnswer(2);
        disableButtons();
        btnYellow.setOpacity(1);
    }

    public void onGreenButtonClick(ActionEvent actionEvent) throws IOException {
        ClientApplication.sendAnswer(3);
        disableButtons();
        btnGreen.setOpacity(1);
    }

    private void disableButtons() {
        btnRed.setDisable(true);
        btnBlue.setDisable(true);
        btnYellow.setDisable(true);
        btnGreen.setDisable(true);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}