package pw.espana.kahootgourmet.server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pw.espana.kahootgourmet.commons.game.Question;
import pw.espana.kahootgourmet.commons.game.Questionnaire;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.TreeSet;

public class ServerApplication extends Application {
    private static final ObservableList<ServerUserThread> serverUserThreads = FXCollections.observableArrayList(new TreeSet<>());
    private static volatile StateId state = StateId.IDLE; // Defines the state of the state machine. State 0 is a standby state, state 99 is a termination state
    private static int pin = 0;
    private static Questionnaire questionnaire;
    private static ServerSocket serverSocket;
    private static ServerThread serverThread;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Kahoot Gourmet - Server");
        stage.setOnCloseRequest(event -> {
            // Call the stop() method to properly terminate the application
            Platform.exit();
        });
        ScreenSwitcher.initScreenSwitcher(stage);
        ScreenSwitcher.showMainScene();
    }

    @Override
    public void stop() throws IOException {
        ScreenSwitcher.terminate();
        closeServer();
    }

    public static boolean startServer(int port, int pin, String pathToQuestionnaire) {
        ServerApplication.pin = pin;
        state = StateId.LISTENING_FOR_CONNECTIONS;
        questionnaire = Questionnaire.loadFromFile(pathToQuestionnaire);

        if (questionnaire == null) {
            state = StateId.IDLE;
            return false;
        }

        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(100);
            serverThread = new ServerThread(serverSocket, questionnaire);
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
                if (serverUserThread != null && serverUserThread.isAlive()) {
                    serverUserThread.closeRequest();
                }
            } catch (Exception e) {}
        }

        serverUserThreads.clear();
        state = StateId.IDLE;
        questionnaire = null;
        if (serverSocket != null && serverSocket.isBound()) {
            serverSocket.close();
        }
    }

    public static void interruptServerThread() throws IOException {
        serverThread.interrupt();
    }

    public static StateId getState() {
        return state;
    }

    public static void setState(StateId state) {
        ServerApplication.state = state;
    }

    public static ObservableList<ServerUserThread> getConnectedUsers() {
        return serverUserThreads;
    }

    public static void addConnectedUsers(ServerUserThread serverUserThread) {
        serverUserThreads.add(serverUserThread);
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