package pw.espana.kahootgourmet.server.controllers;

import javafx.stage.FileChooser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pw.espana.kahootgourmet.commons.game.Answer;
import pw.espana.kahootgourmet.commons.game.Question;
import pw.espana.kahootgourmet.commons.game.Questionnaire;
import pw.espana.kahootgourmet.server.ScreenSwitcher;

import java.io.File;
import java.io.IOException;

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
    @FXML
    private TableColumn<Question, String> columnPreguntas;
    private Question selectedQuestion;

    Questionnaire questionnaire = new Questionnaire();

    @FXML
    protected void onReturnButtonClick(ActionEvent actionEvent) throws IOException {
        ScreenSwitcher.showMainScene();
    }

    @FXML
    protected void onAddQuestionButtonClick(ActionEvent actionEvent) {
        Answer[] answers = { new Answer(txtRed.getText(), checkRed.isSelected()),
                new Answer(txtBlue.getText(), checkBlue.isSelected()),
                new Answer(txtYellow.getText(), checkYellow.isSelected()),
                new Answer(txtGreen.getText(), checkGreen.isSelected()) };
        Question question = new Question(txtPregunta.getText(), 100, answers);
        questionnaire.addQuestion(question);
        clearFields();
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
        Answer[] answers = selectedQuestion.getAnswers();
        txtPregunta.setText(selectedQuestion.getQuestion());
        txtRed.setText(answers[0].answer());
        txtBlue.setText(answers[1].answer());
        txtGreen.setText(answers[2].answer());
        txtYellow.setText(answers[3].answer());
        checkRed.setSelected(answers[0].correct());
        checkBlue.setSelected(answers[1].correct());
        checkGreen.setSelected(answers[2].correct());
        checkYellow.setSelected(answers[3].correct());
    }

    public void onEraseQuestionButtonClick(ActionEvent actionEvent) {
        questionnaire.eraseQuestion(selectedQuestion);
        clearFields();
    }

    public void onSaveQuestionnaireButtonClick(ActionEvent actionEvent) {
    }

    public void clearFields() {
        txtPregunta.clear();
        txtRed.clear();
        txtBlue.clear();
        txtGreen.clear();
        txtYellow.clear();
        checkRed.setSelected(false);
        checkBlue.setSelected(false);
        checkGreen.setSelected(false);
        checkYellow.setSelected(false);
    }

    public void onLoad() {
        tablePreguntas.setItems(questionnaire.getQuestions());
        columnPreguntas.setCellValueFactory(data -> data.getValue().questionProperty());
    }
}
