package pw.espana.kahootgourmet.server.controllers;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pw.espana.kahootgourmet.server.ServerApplication;
import pw.espana.kahootgourmet.server.game.Answer;
import pw.espana.kahootgourmet.server.game.Question;
import pw.espana.kahootgourmet.server.game.Questionnaire;

public class MainScreenController {
    @FXML
    private TextField txtPIN;
    @FXML
    private TextField txtPuerto;

    @FXML
    protected void initialize()  {
        txtPIN.setText(String.valueOf(ThreadLocalRandom.current().nextInt(1000000, 9999999 + 1)));
        txtPuerto.setText("60420");
    }

    @FXML
    protected void onLoadButtonClick(ActionEvent actionEvent) throws IOException {
        /*Answer[] answers = { new Answer("Rojo", true), new Answer("Azul", true), new Answer("Amarillo", true), new Answer("Verde", true) };
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.addQuestion(new Question("De qué color es cada cuadro?", 100, answers));

        questionnaire.saveToFile("prueba.bin");*/

        if (!ServerApplication.startServer(Integer.parseInt(txtPuerto.getText()), Integer.parseInt(txtPIN.getText()), "prueba.bin")) return;

        Parent root = FXMLLoader.load(Objects.requireNonNull(ServerApplication.class.getResource("start-game-view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onEditorButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ServerApplication.class.getResource("quiz-editor-view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
