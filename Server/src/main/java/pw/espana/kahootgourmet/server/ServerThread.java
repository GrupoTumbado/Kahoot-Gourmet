package pw.espana.kahootgourmet.server;

import pw.espana.kahootgourmet.commons.game.Questionnaire;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class ServerThread extends Thread {
    private final ServerSocket serverSocket;
    private final Questionnaire questionnaire;
    private int answerCount = 0;

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
                    answerCount = 0;
                    ServerApplication.clientDisplayLoadingScreen(questionnaire.getWaitTime());
                    ScreenSwitcher.showQuestionScene();
                    ServerApplication.setState(StateId.IDLE);
                }
                case SHOWING_QUESTION_AND_ANSWERS -> {
                    ServerApplication.clientEnableAnswerIntake(questionnaire.getCurrentQuestion());
                    ScreenSwitcher.showQuestionAnswersScene();
                    ServerApplication.setState(StateId.COUNT_ANSWERS);
                }
                case COUNT_ANSWERS -> {
                    if (answerCount >= ServerApplication.getConnectedUsers().size()) {
                        ServerApplication.setState(StateId.SHOWING_ANSWER);
                    }
                }
                case SHOWING_ANSWER -> {
                    ServerApplication.sortUsers();
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
                    ServerApplication.clientSendFinalScore();
                    ScreenSwitcher.showScoreboardScene();
                }
                default -> {}
            }
        }
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public void incrementAnswerCount() {
        this.answerCount++;
    }
}
