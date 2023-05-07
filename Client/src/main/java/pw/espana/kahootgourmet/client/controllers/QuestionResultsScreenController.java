package pw.espana.kahootgourmet.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import pw.espana.kahootgourmet.commons.messages.server.requests.QuestionResultsScreenRequest;

import java.util.Random;

public class QuestionResultsScreenController {
    private Stage stage;
    @FXML
    private AnchorPane anchorPaneBackground;
    @FXML
    private SVGPath checkmark;
    @FXML
    private SVGPath cross;
    @FXML
    private Label lblCorrecto;
    @FXML
    private Label lblPuntosObtenidos;
    @FXML
    private Label lblPuesto;
    Random rand = new Random();
    String[] motivationalPhrases = {
            "Sigue intentando",
            "No te rindas",
            "Práctica hace al maestro",
            "Aprendiendo de errores",
            "Siempre hacia adelante",
            "La próxima vez mejor",
            "No te desanimes",
            "¡Adelante!",
            "Continúa aprendiendo",
            "Errar es humano",
            "Persiste y vencerás",
            "Fallar es aprender",
            "No hay rendición",
            "Lo intentaste bien",
            "Aprende de fallos"
    };

    public void onLoad(QuestionResultsScreenRequest questionResultsScreenRequest) {
        if (questionResultsScreenRequest.isCorrect()) {
            lblCorrecto.setText("Correcto!");
            anchorPaneBackground.setStyle("-fx-background-color: #66bf39");
            lblPuntosObtenidos.setText("+" + questionResultsScreenRequest.getPointsGained());
            checkmark.setOpacity(1);
            cross.setOpacity(0);
        } else {
            lblCorrecto.setText("Incorrecto!");
            anchorPaneBackground.setStyle("-fx-background-color: #ff3355");
            lblPuntosObtenidos.setText(motivationalPhrases[rand.nextInt(motivationalPhrases.length)]);
            checkmark.setOpacity(0);
            cross.setOpacity(1);
        }

        lblPuesto.setText("Estás en " + questionResultsScreenRequest.getPlace() + "º");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
