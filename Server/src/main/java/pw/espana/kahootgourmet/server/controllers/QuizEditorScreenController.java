package pw.espana.kahootgourmet.server.controllers;

import com.almasb.fxgl.quest.Quest;
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
    private TextField txtDisplayTimer;
    @FXML
    private TextField txtAnswerTimer;
    @FXML
    private TextField txtPregunta;
    @FXML
    private TextField txtValor;
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
    protected void initialize() {
        txtDisplayTimer.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                questionnaire.setWaitTime(Integer.parseInt(newValue));
            } catch (NumberFormatException e) {}
        });

        txtAnswerTimer.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                questionnaire.setAnswerTime(Integer.parseInt(newValue));
            } catch (NumberFormatException e) {}
        });
    }

    public void onLoad() {
        tablePreguntas.setItems(questionnaire.getQuestions());
        columnPreguntas.setCellValueFactory(data -> data.getValue().questionProperty());
    }

    public void onNewQuizButtonClick(ActionEvent actionEvent) {
        questionnaire = new Questionnaire();
        tablePreguntas.setItems(questionnaire.getQuestions());
        columnPreguntas.setCellValueFactory(data -> data.getValue().questionProperty());
        clearFields();
    }

    public void onLoadQuizButtonClick(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar Cuestionario");
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Cuestionario (*.bin)", "*.bin");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            Questionnaire tempQuestionnaire = Questionnaire.loadFromFile(file.getAbsolutePath());
            if (tempQuestionnaire != null) {
                questionnaire = tempQuestionnaire;
                tablePreguntas.setItems(questionnaire.getQuestions());
                clearFields();
            }
        }
    }

    @FXML
    protected void onSaveQuizButtonClick(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Cuestionario");
        Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Cuestionario (*.bin)", "*.bin");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            questionnaire.saveToFile(file.getAbsolutePath());
        }
    }

    @FXML
    protected void onReturnButtonClick(ActionEvent actionEvent) throws IOException {
        ScreenSwitcher.showMainScene();
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        selectedQuestion = tablePreguntas.getSelectionModel().getSelectedItem();
        Answer[] answers = selectedQuestion.getAnswers();
        txtPregunta.setText(selectedQuestion.getQuestion());
        txtValor.setText(String.valueOf(selectedQuestion.getScoreValue()));
        txtRed.setText(answers[0].answer());
        txtBlue.setText(answers[1].answer());
        txtYellow.setText(answers[2].answer());
        txtGreen.setText(answers[3].answer());
        checkRed.setSelected(answers[0].correct());
        checkBlue.setSelected(answers[1].correct());
        checkYellow.setSelected(answers[2].correct());
        checkGreen.setSelected(answers[3].correct());
    }

    @FXML
    protected void onAddQuestionButtonClick(ActionEvent actionEvent) {
        Answer[] answers = { new Answer(txtRed.getText(), checkRed.isSelected()),
                new Answer(txtBlue.getText(), checkBlue.isSelected()),
                new Answer(txtYellow.getText(), checkYellow.isSelected()),
                new Answer(txtGreen.getText(), checkGreen.isSelected()) };
        int value = 0;
        try {
            value = Integer.parseInt(txtValor.getText());
        } catch (NumberFormatException e) {}

        Question question = new Question(txtPregunta.getText(), value, answers);
        questionnaire.addQuestion(question);
        clearFields();
    }

    public void onEraseQuestionButtonClick(ActionEvent actionEvent) {
        questionnaire.eraseQuestion(selectedQuestion);
        clearFields();
    }

    public void clearFields() {
        txtDisplayTimer.setText(String.valueOf(questionnaire.getWaitTime()));
        txtAnswerTimer.setText(String.valueOf(questionnaire.getAnswerTime()));

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

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
