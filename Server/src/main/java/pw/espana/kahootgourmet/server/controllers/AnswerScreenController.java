package pw.espana.kahootgourmet.server.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import pw.espana.kahootgourmet.commons.game.Answer;
import pw.espana.kahootgourmet.commons.game.Question;
import pw.espana.kahootgourmet.server.ServerApplication;
import pw.espana.kahootgourmet.server.StateId;

public class AnswerScreenController {
    private Stage stage;
    @FXML
    private Label lblPregunta;
    @FXML
    private Pane paneRed;
    @FXML
    private Pane paneBlue;
    @FXML
    private Pane paneYellow;
    @FXML
    private Pane paneGreen;
    @FXML
    private Label answerRed;
    @FXML
    private Label answerBlue;
    @FXML
    private Label answerYellow;
    @FXML
    private Label answerGreen;

    public void onLoad()  {
        Question currentQuestion = ServerApplication.getQuestionnaire().getCurrentQuestion();
        lblPregunta.setText(currentQuestion.question());

        Answer redAnswer = currentQuestion.answers()[0];
        Answer blueAnswer = currentQuestion.answers()[1];
        Answer yellowAnswer = currentQuestion.answers()[2];
        Answer greenAnswer = currentQuestion.answers()[3];

        answerRed.setText(redAnswer.answer());
        answerBlue.setText(blueAnswer.answer());
        answerYellow.setText(yellowAnswer.answer());
        answerGreen.setText(greenAnswer.answer());

        paneRed.setOpacity(redAnswer.correct() ? 1 : 0.7);
        paneBlue.setOpacity(blueAnswer.correct() ? 1 : 0.7);
        paneYellow.setOpacity(yellowAnswer.correct() ? 1 : 0.7);
        paneGreen.setOpacity(greenAnswer.correct() ? 1 : 0.7);
    }

    public void onNextButtonClick(ActionEvent actionEvent) {
        ServerApplication.setState(StateId.HANDLE_NEXT_QUESTION);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
