package pw.espana.kahootgourmet.client;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class PosicionController implements Initializable {
    @FXML
    private Label label_usuario;
    public static Label static_label_usuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        static_label_usuario   = label_usuario;


    }


}