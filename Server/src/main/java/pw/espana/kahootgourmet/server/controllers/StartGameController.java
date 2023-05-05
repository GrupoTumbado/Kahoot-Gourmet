package pw.espana.kahootgourmet.server.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pw.espana.kahootgourmet.server.ServerApplication;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartGameController {
    @FXML
    private TextField txtPIN;
    @FXML
    private TableView tableJugadores;
    @FXML
    private TableColumn columnJugadores;

    @FXML
    protected void initialize()  {
        txtPIN.setText(String.valueOf(ServerApplication.getPin()));
        columnJugadores.setCellFactory(new PropertyValueFactory<>("username"));
        tableJugadores.setItems((ObservableList) ServerApplication.getConnectedUsers());

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            while (true) {
                tableJugadores.refresh();
                wait(1000);
            }
        });
    }

    @FXML
    protected void onReturnButtonClick(ActionEvent actionEvent) throws Exception {
        ServerApplication.closeServer();

        Parent root = FXMLLoader.load(Objects.requireNonNull(ServerApplication.class.getResource("main-view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onStartButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(ServerApplication.class.getResource("question-view.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
