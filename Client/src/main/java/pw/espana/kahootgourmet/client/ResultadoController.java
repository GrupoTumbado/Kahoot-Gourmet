package pw.espana.kahootgourmet.client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public class ResultadoController {
    @FXML
    private Label correctoLabel;

    @FXML
    private Circle circuloVerde;

    @FXML
    private Label incorrectoLabel;

    @FXML
    private Circle circuloRojo;

    private boolean esVerdadero = false;

    public void actualizarVisibilidad() {
        correctoLabel.setVisible(esVerdadero);
        circuloVerde.setVisible(esVerdadero);
        incorrectoLabel.setVisible(!esVerdadero);
        circuloRojo.setVisible(!esVerdadero);
    }
}
