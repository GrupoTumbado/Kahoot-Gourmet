package pw.espana.kahootgourmet.server;

import pw.espana.kahootgourmet.server.game.Questionnaire;

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
                case 1 -> {
                    try {
                        Socket socket = serverSocket.accept();
                        ServerUserThread worker = new ServerUserThread(socket);
                        ServerApplication.addConnectedUsers(worker);
                        worker.start();
                    } catch (SocketTimeoutException e) {}
                }
                case 2 -> {
                    ServerApplication.clientDisplayLoadingScreen(questionnaire.getWaitTime());

                    try (ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1)) {
                        scheduler.schedule(() -> {
                            ServerApplication.setState(3);
                        }, questionnaire.getWaitTime(), TimeUnit.SECONDS);
                    } catch (Exception e) {
                        System.out.println("Error in the server: " + e.getMessage());
                        e.printStackTrace();
                    }

                    ServerApplication.setState(0);
                }
                case 3 -> {
                    ServerApplication.clientEnableAnswerIntake(questionnaire.getCurrentQuestion());

                    try (ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1)) {
                        scheduler.schedule(() -> {
                            ServerApplication.setState(4);
                        }, questionnaire.getAnswerTime(), TimeUnit.SECONDS);
                    } catch (Exception e) {
                        System.out.println("Error in the server: " + e.getMessage());
                        e.printStackTrace();
                    }

                    ServerApplication.setState(0);
                }
                case 4 -> {
                    ServerApplication.clientSendAnswerResults();
                    questionnaire.advanceQuestion();
                    ServerApplication.setState(0);
                }
                default -> {}
            }
        }
    }
}
