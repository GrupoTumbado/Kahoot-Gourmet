package pw.espana.kahootgourmet.server.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pw.espana.kahootgourmet.server.ScreenSwitcher;
import pw.espana.kahootgourmet.server.ServerApplication;

import java.io.IOException;
import java.util.Objects;

public class QuizEditorScreenController {
    private Stage stage;
    @FXML
    private TextField txtPregunta;
    @FXML
    private TextField txtRed;
    @FXML
    private TextField txtBlue;
    @FXML
    private TextField txtGreen;
    @FXML
    private TextField txtYellow;
    @FXML
    private CheckBox checkRed;
    @FXML
    private CheckBox checkBlue;
    @FXML
    private CheckBox checkGreen;
    @FXML
    private CheckBox checkYellow;

    @FXML
    protected void onReturnButtonClick(ActionEvent actionEvent) throws IOException {
        ScreenSwitcher.showMainScene();
    }

    @FXML
    protected void onAddQuestionButtonClick(ActionEvent actionEvent) {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
