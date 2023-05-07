package pw.espana.kahootgourmet.server.controllers;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pw.espana.kahootgourmet.commons.game.Answer;
import pw.espana.kahootgourmet.commons.game.Question;
import pw.espana.kahootgourmet.commons.game.Questionnaire;
import pw.espana.kahootgourmet.server.ScreenSwitcher;
import pw.espana.kahootgourmet.server.ServerApplication;

public class MainScreenController {
    private Stage stage;
    @FXML
    private TextField txtPIN;
    @FXML
    private TextField txtPuerto;

    @FXML
    protected void initialize()  {
        txtPIN.setText(String.valueOf(ThreadLocalRandom.current().nextInt(1000000, 9999999 + 1)));
        txtPuerto.setText("60420");
    }

    public void onLoad() {
        txtPIN.setText(String.valueOf(ThreadLocalRandom.current().nextInt(1000000, 9999999 + 1)));
    }

    @FXML
    protected void onLoadButtonClick(ActionEvent actionEvent) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar Cuestionario");
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Cuestionario (*.bin)", "*.bin");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            if (!ServerApplication.startServer(Integer.parseInt(txtPuerto.getText()), Integer.parseInt(txtPIN.getText()), file.getAbsolutePath())) return;
            ScreenSwitcher.showStartGameScene();
        }
    }

    @FXML
    protected void onEditorButtonClick(ActionEvent actionEvent) throws IOException {
        ScreenSwitcher.showQuizEditorScene();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
