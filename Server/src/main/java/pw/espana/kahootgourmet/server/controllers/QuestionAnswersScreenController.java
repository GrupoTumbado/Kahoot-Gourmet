package pw.espana.kahootgourmet.server.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import pw.espana.kahootgourmet.commons.game.Question;
import pw.espana.kahootgourmet.server.ServerApplication;
import pw.espana.kahootgourmet.server.StateId;

public class QuestionAnswersScreenController {
    private Stage stage;

    @FXML
    private Label lblPregunta;
    @FXML
    private Label lblTimer;
    @FXML
    private Label answerRed;
    @FXML
    private Label answerBlue;
    @FXML
    private Label answerYellow;
    @FXML
    private Label answerGreen;
    private int remainingSeconds = 60;

    public void onLoad() {
        Question currentQuestion = ServerApplication.getQuestionnaire().getCurrentQuestion();
        remainingSeconds = ServerApplication.getQuestionnaireAnswerTime();

        lblPregunta.setText(currentQuestion.getQuestion());
        lblTimer.setText(String.valueOf(remainingSeconds));

        answerRed.setText(currentQuestion.getAnswers()[0].answer());
        answerBlue.setText(currentQuestion.getAnswers()[1].answer());
        answerYellow.setText(currentQuestion.getAnswers()[2].answer());
        answerGreen.setText(currentQuestion.getAnswers()[3].answer());

        Timeline timeline = new Timeline();
        timeline.setCycleCount(remainingSeconds);
        timeline.getKeyFrames().add(
            new KeyFrame(Duration.seconds(1), event -> {
                remainingSeconds--;
                if (remainingSeconds == 0) {
                    ServerApplication.setState(StateId.SHOWING_ANSWER);
                }
                lblTimer.setText(String.valueOf(remainingSeconds));
            })
        );
        timeline.play();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
