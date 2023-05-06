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

import java.util.concurrent.ThreadLocalRandom;

public class QuestionScreenController {
    private Stage stage;
    @FXML
    private Label lblPregunta;
    @FXML
    private Label lblTimer;
    private int remainingSeconds = 5;


    public void onLoad()  {
        Question currentQuestion = ServerApplication.getQuestionnaire().getCurrentQuestion();
        remainingSeconds = ServerApplication.getQuestionnaireWaitTime();

        lblPregunta.setText(currentQuestion.question());
        lblTimer.setText(String.valueOf(remainingSeconds));

        Timeline timeline = new Timeline();
        timeline.setCycleCount(remainingSeconds);
        timeline.getKeyFrames().add(
            new KeyFrame(Duration.seconds(1), event -> {
                remainingSeconds--;
                if (remainingSeconds <= 0) {
                    timeline.stop();
                    ServerApplication.setState(StateId.SHOWING_QUESTION_AND_ANSWERS);
                } else {
                    lblTimer.setText(String.valueOf(remainingSeconds));
                }
            })
        );
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
