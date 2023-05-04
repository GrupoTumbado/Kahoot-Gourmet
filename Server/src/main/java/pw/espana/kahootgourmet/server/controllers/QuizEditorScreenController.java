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
import pw.espana.kahootgourmet.server.ServerApplication;

import java.io.IOException;
import java.util.Objects;

public class QuizEditorScreenController {
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(ServerApplication.class.getResource("main-view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onAddQuestionButtonClick(ActionEvent actionEvent) {
    }
}
