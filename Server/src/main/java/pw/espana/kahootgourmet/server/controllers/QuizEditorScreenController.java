package pw.espana.kahootgourmet.server.controllers;

import com.almasb.fxgl.profile.SaveFile;
import javafx.stage.FileChooser;
import pw.espana.kahootgourmet.commons.game.Questionnaire;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pw.espana.kahootgourmet.commons.game.Answer;
import pw.espana.kahootgourmet.commons.game.Question;
import pw.espana.kahootgourmet.commons.game.Questionnaire;
import pw.espana.kahootgourmet.server.ScreenSwitcher;
import pw.espana.kahootgourmet.server.ServerApplication;

import java.io.File;
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
    private TableView<Question> tablePreguntas;
    private Question selectedQuestion;

    Questionnaire questionnaire = new Questionnaire();


    @FXML
    protected void onReturnButtonClick(ActionEvent actionEvent) throws IOException {
        ScreenSwitcher.showMainScene();
    }

    @FXML
    protected void onAddQuestionButtonClick(ActionEvent actionEvent) {
        Answer[] answers = { new Answer(txtRed.getText(), checkRed.isSelected()), new Answer(txtBlue.getText(), checkBlue.isSelected()), new Answer(txtYellow.getText(), checkYellow.isSelected()), new Answer(txtGreen.getText(), checkGreen.isSelected()) };
        Question question = new Question(txtPregunta.getText(), 100, answers);
        questionnaire.addQuestion(question);
    }

    @FXML
    protected void onSaveQuizButtonClick(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Cuestionario");
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            Questionnaire questionnaire = new Questionnaire();
            questionnaire.saveToFile(file.getAbsolutePath());
        }
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        selectedQuestion = tablePreguntas.getSelectionModel().getSelectedItem();
    }

    public void onEraseQuestionButtonClick(ActionEvent actionEvent) {
        questionnaire.eraseQuestion(selectedQuestion);
    }

    public void onSaveQuestionnaireButtonClick(ActionEvent actionEvent) {
    }
}
