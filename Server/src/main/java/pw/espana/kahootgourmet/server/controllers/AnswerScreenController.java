package pw.espana.kahootgourmet.server.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pw.espana.kahootgourmet.commons.game.Answer;
import pw.espana.kahootgourmet.commons.game.Question;
import pw.espana.kahootgourmet.commons.game.User;
import pw.espana.kahootgourmet.server.ServerApplication;
import pw.espana.kahootgourmet.server.ServerUserThread;
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
    @FXML
    private Label lbl1erLugar;
    @FXML
    private Label lbl2doLugar;
    @FXML
    private Label lbl3erLugar;
    @FXML
    private ImageView img1erLugar;
    @FXML
    private ImageView img2doLugar;
    @FXML
    private ImageView img3erLugar;



    public void onLoad() {

        int connectedUsers = ServerApplication.getConnectedUsers().size();

        lbl1erLugar.setText(connectedUsers >= 1 ? ServerApplication.getConnectedUsers().get(0).getUsername() : "");
        img1erLugar.setVisible(connectedUsers >= 1);

        lbl2doLugar.setText(connectedUsers >= 2 ? ServerApplication.getConnectedUsers().get(1).getUsername() : "");
        img2doLugar.setVisible(connectedUsers >= 2);

        lbl3erLugar.setText(connectedUsers >= 3 ? ServerApplication.getConnectedUsers().get(2).getUsername() : "");
        img3erLugar.setVisible(connectedUsers >= 3);

        lbl2doLugar.setVisible(connectedUsers >= 2);
        img2doLugar.setVisible(connectedUsers >= 2);

        lbl3erLugar.setVisible(connectedUsers >= 3);
        img3erLugar.setVisible(connectedUsers >= 3);

        Question currentQuestion = ServerApplication.getQuestionnaire().getCurrentQuestion();
        lblPregunta.setText(currentQuestion.getQuestion());

        Answer redAnswer = currentQuestion.getAnswers()[0];
        Answer blueAnswer = currentQuestion.getAnswers()[1];
        Answer yellowAnswer = currentQuestion.getAnswers()[2];
        Answer greenAnswer = currentQuestion.getAnswers()[3];

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
