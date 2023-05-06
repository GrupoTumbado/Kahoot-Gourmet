package pw.espana.kahootgourmet.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import pw.espana.kahootgourmet.commons.messages.server.requests.QuestionResultsScreenRequest;

public class QuestionResultsScreenController {
    private Stage stage;
    @FXML
    private Label lblCorrecto;
    @FXML
    private Label lblPuntosObtenidos;
    @FXML
    private Label lblPuesto;
    @FXML
    private Circle circleResultado;

    public void onLoad(QuestionResultsScreenRequest questionResultsScreenRequest) {
        if (questionResultsScreenRequest.isCorrect()) {
            lblCorrecto.setText("Correcto!");
        } else {
            lblCorrecto.setText("Incorrecto!");
            circleResultado.setFill(Paint.valueOf("#FF5F4E"));
        }

        lblPuntosObtenidos.setText("Puntos obtenidos: " + questionResultsScreenRequest.getPointsGained());
        lblPuesto.setText("Puesto:" + questionResultsScreenRequest.getPlace());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
