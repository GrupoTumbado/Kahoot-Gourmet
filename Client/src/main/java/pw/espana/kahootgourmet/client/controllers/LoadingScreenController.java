package pw.espana.kahootgourmet.client.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoadingScreenController {
    private Stage stage;
    @FXML
    private Label lblTimer;
    private int remainingSeconds = 0;

    public void onLoad() {
        lblTimer.setText("");
    }

    public void setTime(int time) {
        remainingSeconds = time;

        Timeline timeline = new Timeline();
        timeline.setCycleCount(remainingSeconds);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    remainingSeconds--;
                    lblTimer.setText(String.valueOf(remainingSeconds));
                })
        );
        timeline.play();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}