package pw.espana.kahootgourmet.server;

import pw.espana.kahootgourmet.commons.game.Questionnaire;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerThread extends Thread {
    private final ServerSocket serverSocket;
    private final Questionnaire questionnaire;

    public ServerThread(ServerSocket serverSocket, Questionnaire questionnaire) {
        this.serverSocket = serverSocket;
        this.questionnaire = questionnaire;
    }

    @Override
    public void run() {
        try {
            startServer();
        } catch (SocketException e) {
            System.out.println("Server closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startServer() throws Exception {
        while (true) {
            switch (ServerApplication.getState()) {
                case LISTENING_FOR_CONNECTIONS -> {
                    try {
                        Socket socket = serverSocket.accept();
                        ServerUserThread worker = new ServerUserThread(socket);
                        worker.start();
                    } catch (SocketTimeoutException e) {}
                }
                case SHOWING_QUESTION -> {
                    ServerApplication.clientDisplayLoadingScreen(questionnaire.getWaitTime());
                    ScreenSwitcher.showQuestionScene();
                    ServerApplication.setState(StateId.IDLE);
                }
                case SHOWING_QUESTION_AND_ANSWERS -> {
                    ServerApplication.clientEnableAnswerIntake(questionnaire.getCurrentQuestion());
                    ScreenSwitcher.showQuestionAnswersScene();
                    ServerApplication.setState(StateId.IDLE);
                }
                case SHOWING_ANSWER -> {
                    ServerApplication.clientSendAnswerResults();
                    ScreenSwitcher.showAnswerScene();
                    ServerApplication.setState(StateId.IDLE);
                }
                case HANDLE_NEXT_QUESTION -> {
                    if (questionnaire.moreQuestionsAvailable()) {
                        questionnaire.advanceQuestion();
                        ServerApplication.setState(StateId.SHOWING_QUESTION);
                    } else {
                        ServerApplication.setState(StateId.SHOWING_SCOREBOARD);
                    }
                }
                case SHOWING_SCOREBOARD -> {
                    // TODO: Send the scoreboard to the client
                    ScreenSwitcher.showScoreboardScene();
                }
                default -> {}
            }
        }
    }
}
