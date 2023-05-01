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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerApplication extends Application {
    private final TreeSet<ServerThread> serverThreads = new TreeSet<>();
    private int state = 0; // Defines the state of the state machine. State 0 is a standby state, state 99 is a termination state
    private int pin = 0;
    private Questionnaire questionnaire;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ServerApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void startServer(int port, int pin, String pathToQuestionnaire) {
        this.pin = pin;
        state = 1;
        questionnaire = Questionnaire.loadFromFile(pathToQuestionnaire);

        if (questionnaire == null) {
            state = 0;
            return;
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            boolean runMainLoop = true;
            System.out.println("Server is listening on port " + port);

            while (runMainLoop) {
                switch (state) {
                    case 1 -> {
                        Socket socket = serverSocket.accept();
                        ServerThread worker = new ServerThread(socket, this);
                        this.serverThreads.add(worker);
                        worker.start();
                    }
                    case 2 -> {
                        clientDisplayLoadingScreen(questionnaire.getWaitTime());

                        try (ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1)) {
                            scheduler.schedule(() -> {
                                state = 3;
                            }, questionnaire.getWaitTime(), TimeUnit.SECONDS);
                        } catch (Exception e) {
                            System.out.println("Error in the server: " + e.getMessage());
                            e.printStackTrace();
                        }

                        state = 0;
                    }
                    case 3 -> {
                        clientEnableAnswerIntake(questionnaire.getCurrentQuestion());

                        try (ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1)) {
                            scheduler.schedule(() -> {
                                state = 4;
                            }, questionnaire.getAnswerTime(), TimeUnit.SECONDS);
                        } catch (Exception e) {
                            System.out.println("Error in the server: " + e.getMessage());
                            e.printStackTrace();
                        }

                        state = 0;
                    }
                    case 4 -> {
                        clientSendAnswerResults();
                        questionnaire.advanceQuestion();
                        state = 0;
                    }
                    case 99 -> runMainLoop = false;
                    default -> {}
                }
            }
        } catch (Exception e) {
            System.out.println("Error in the server: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void removeThread(ServerThread worker) {
        serverThreads.remove(worker);
    }

    public boolean isUsernameTaken(String username) {
        for (ServerThread userThread: this.serverThreads) {
            if (userThread.getUsername().equals(username)) return true;
        }

        return false;
    }
    public Set<ServerThread> getConnectedUsers() {
        return this.serverThreads;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public int getQuestionnaireSize() {
        return questionnaire.getQuestionCount();
    }

    public int getQuestionnaireWaitTime() {
        return questionnaire.getWaitTime();
    }

    public int getQuestionnaireAnswerTime() {
        return questionnaire.getAnswerTime();
    }

    public int getPin() {
        return pin;
    }

    public void clientDisplayLoadingScreen(int waitTime) throws Exception {
        for (ServerThread serverThread: serverThreads) {
            serverThread.displayLoadingScreen(waitTime);
        }
    }

    public void clientEnableAnswerIntake(Question question) throws Exception {
        for (ServerThread serverThread: serverThreads) {
            serverThread.enableAnswerIntake(question);
        }
    }

    public void clientSendAnswerResults() throws Exception {
        int index = 0;
        for (ServerThread serverThread : serverThreads) {
            serverThread.sendAnswerResults(index + 1);
            index++;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}