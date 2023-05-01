package pw.espana.kahootgourmet.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController extends PosicionController {
    @FXML
    private Button btn_empezar;
    @FXML
    private void initialize() {
        btn_empezar.setOnAction(new EventHandler<ActionEvent>() {

            @Override

            public void handle(ActionEvent event) {

                try {
                    Stage thisstage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    thisstage.close();

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("posicion_final.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }

}