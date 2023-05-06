package pw.espana.kahootgourmet.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;

public class ClientApplication extends Application {
    private static ClientThread clientThread;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Kahoot Gourmet - Client");
        stage.setOnCloseRequest(event -> {
            // Call the stop() method to properly terminate the application
            Platform.exit();
        });
        ScreenSwitcher.initScreenSwitcher(stage);
        ScreenSwitcher.showLoginScene();
    }

    @Override
    public void stop() throws IOException {
        ScreenSwitcher.terminate();
        closeClient();
        System.exit(0);
    }

    public static ClientThread joinServer(String ip, int port, String username, int pin) {
        try {
            clientThread = new ClientThread(ip, port, username, pin);
            clientThread.start();
        } catch (Exception e) {
            System.out.println("Error in the server: " + e.getMessage());
            e.printStackTrace();
        }
        return clientThread;
    }

    public static void closeClient() throws IOException {
        if (clientThread != null && clientThread.isAlive()) {
            clientThread.closeRequest();
            clientThread.interrupt();
        }
    }

    public static void sendAnswer(int selectedAnswer) throws IOException {
        clientThread.sendAnswer(selectedAnswer);
    }

    public static void main(String[] args) {
        launch();
    }
}