package pw.espana.kahootgourmet.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pw.espana.kahootgourmet.server.game.Question;
import pw.espana.kahootgourmet.server.game.Questionnaire;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerApplication extends Application {
    private static final TreeSet<ServerUserThread> serverUserThreads = new TreeSet<>();
    private static int state = 0; // Defines the state of the state machine. State 0 is a standby state, state 99 is a termination state
    private static int pin = 0;
    private static Questionnaire questionnaire;
    private static ServerSocket serverSocket;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ServerApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Kahoot Gourmet");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws IOException {
        closeServer();
    }

    public static boolean startServer(int port, int pin, String pathToQuestionnaire) {
        ServerApplication.pin = pin;
        state = 1;
        questionnaire = Questionnaire.loadFromFile(pathToQuestionnaire);

        if (questionnaire == null) {
            state = 0;
            return false;
        }

        try {
            serverSocket = new ServerSocket(port);
            ServerThread serverThread = new ServerThread(serverSocket, questionnaire);
            serverThread.start();

            System.out.println("Server is listening on port " + port);
        } catch (Exception e) {
            System.out.println("Error in the server: " + e.getMessage());
            e.printStackTrace();
        }

        return true;
    }

    public static void closeServer() throws IOException {
        for (ServerUserThread serverUserThread : serverUserThreads) {
            try {
                serverUserThread.closeRequest();
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        state = 0;
        questionnaire = null;
        serverSocket.close();
    }

    public static int getState() {
        return state;
    }

    public static void setState(int state) {
        ServerApplication.state = state;
    }

    public static void removeUserThread(ServerUserThread worker) {
        serverUserThreads.remove(worker);
    }

    public static boolean isUsernameTaken(String username) {
        for (ServerUserThread userThread: serverUserThreads) {
            if (userThread.getUsername().equals(username)) return true;
        }

        return false;
    }

    public static void addConnectedUsers(ServerUserThread serverUserThread) {
        serverUserThreads.add(serverUserThread);
    }

    public static Set<ServerUserThread> getConnectedUsers() {
        return serverUserThreads;
    }

    public static Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public static int getQuestionnaireSize() {
        return questionnaire.getQuestionCount();
    }

    public static int getQuestionnaireWaitTime() {
        return questionnaire.getWaitTime();
    }

    public static int getQuestionnaireAnswerTime() {
        return questionnaire.getAnswerTime();
    }

    public static int getPin() {
        return pin;
    }

    public static void clientDisplayLoadingScreen(int waitTime) throws Exception {
        for (ServerUserThread serverUserThread : serverUserThreads) {
            serverUserThread.displayLoadingScreen(waitTime);
        }
    }

    public static void clientEnableAnswerIntake(Question question) throws Exception {
        for (ServerUserThread serverUserThread : serverUserThreads) {
            serverUserThread.enableAnswerIntake(question);
        }
    }

    public static void clientSendAnswerResults() throws Exception {
        int index = 0;
        for (ServerUserThread serverUserThread : serverUserThreads) {
            serverUserThread.sendAnswerResults(index + 1);
            index++;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}