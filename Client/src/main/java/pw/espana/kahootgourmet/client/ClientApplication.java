package pw.espana.kahootgourmet.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;

public class ClientApplication extends Application {
    private static ClientThread clientThread;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Kahoot Gourmet");
        stage.setScene(scene);
        stage.show();
    }

    public static boolean joinServer(String ip, int port, String username, int pin) {
        try {
            clientThread = new ClientThread(ip, port, username, pin);
            clientThread.start();
        } catch (Exception e) {
            System.out.println("Error in the server: " + e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {
        launch();
    }
}