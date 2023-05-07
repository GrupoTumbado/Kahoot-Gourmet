package pw.espana.kahootgourmet.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
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
    @FXML
    private Pane paneRed;
    @FXML
    private Pane paneBlue;
    @FXML
    private Pane paneYellow;
    @FXML
    private Pane paneGreen;

    public void onLoad() {
        enableButtons();
    }

    public void onRedButtonClick(ActionEvent actionEvent) throws IOException {
        ClientApplication.sendAnswer(0);
        disableButtons();
        paneRed.setOpacity(1);
    }

    public void onBlueButtonClick(ActionEvent actionEvent) throws IOException {
        ClientApplication.sendAnswer(1);
        disableButtons();
        paneBlue.setOpacity(1);
    }

    public void onYellowButtonClick(ActionEvent actionEvent) throws IOException {
        ClientApplication.sendAnswer(2);
        disableButtons();
        paneYellow.setOpacity(1);
    }

    public void onGreenButtonClick(ActionEvent actionEvent) throws IOException {
        ClientApplication.sendAnswer(3);
        disableButtons();
        paneGreen.setOpacity(1);
    }

    private void disableButtons() {
        btnRed.setDisable(true);
        btnBlue.setDisable(true);
        btnYellow.setDisable(true);
        btnGreen.setDisable(true);

        paneRed.setOpacity(0.7);
        paneBlue.setOpacity(0.7);
        paneYellow.setOpacity(0.7);
        paneGreen.setOpacity(0.7);

        /*btnRed.setOpacity(0);
        btnBlue.setOpacity(0);
        btnYellow.setOpacity(0);
        btnGreen.setOpacity(0);*/
    }

    private void enableButtons() {
        btnRed.setDisable(false);
        btnBlue.setDisable(false);
        btnYellow.setDisable(false);
        btnGreen.setDisable(false);

        paneRed.setOpacity(1);
        paneBlue.setOpacity(1);
        paneYellow.setOpacity(1);
        paneGreen.setOpacity(1);

        /*btnRed.setOpacity(0);
        btnBlue.setOpacity(0);
        btnYellow.setOpacity(0);
        btnGreen.setOpacity(0);*/
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}