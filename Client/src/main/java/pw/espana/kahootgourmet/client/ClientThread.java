package pw.espana.kahootgourmet.client;

import pw.espana.kahootgourmet.client.controllers.LoginScreenScreenController;
import pw.espana.kahootgourmet.commons.messages.Message;
import pw.espana.kahootgourmet.commons.messages.MessageId;
import pw.espana.kahootgourmet.commons.messages.client.requests.AnswerRequest;
import pw.espana.kahootgourmet.commons.messages.client.requests.CloseConnectionRequest;
import pw.espana.kahootgourmet.commons.messages.client.requests.JoinRequest;
import pw.espana.kahootgourmet.commons.messages.client.responses.MessageResponse;
import pw.espana.kahootgourmet.commons.messages.server.requests.FinalScoreScreenRequest;
import pw.espana.kahootgourmet.commons.messages.server.requests.QuestionResultsScreenRequest;
import pw.espana.kahootgourmet.commons.messages.server.requests.LoadingScreenRequest;
import pw.espana.kahootgourmet.commons.messages.server.responses.JoinResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ClientThread extends Thread {
    private boolean shouldStop;
    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private final String ip;
    private final int port;
    private final String username;
    private final int pin;
    private LoginScreenScreenController loginScreenController;

    public ClientThread(String ip, int port, String username, int pin) throws Exception {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.pin = pin;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(ip, port);
            writer = new ObjectOutputStream(socket.getOutputStream());
            writer.flush();
            reader = new ObjectInputStream(socket.getInputStream());

            System.out.println("Attempting to login into " + ip + ":" + port);
            writer.writeObject(new JoinRequest(pin, username));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Object obj = null;
            do {
                try {
                    obj = reader.readObject();
                }
                catch (SocketTimeoutException e) {}
            } while (processMessage(obj) != MessageId.SERVER_CLOSE_CONNECTION_REQUEST.getValue() && !shouldStop);

            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int processMessage(Object obj) throws Exception {
        if (!(obj instanceof Message message)) {
            System.err.println("Unknown message object");
            return MessageId.UNKNOWN_MESSAGE.getValue();
        }

        switch (MessageId.valueOf(message.getId())) {
            case SERVER_DEBUG_REQUEST, SERVER_DEBUG_RESPONSE -> { // Handle debug messages
                this.writer.writeObject(new MessageResponse());
                System.out.println("Received test message from server");
            }
            case SERVER_JOIN_RESPONSE -> {
                JoinResponse joinResponse = (JoinResponse) message;

                if (joinResponse.isJoined()) {
                    ScreenSwitcher.showLoadingScene();
                } else {
                    loginScreenController.setTxtError(joinResponse.getErrorMessage());
                }
            }
            case SERVER_LOADING_SCREEN_REQUEST -> {
                LoadingScreenRequest loadingScreenRequest = (LoadingScreenRequest) message;

                if (loadingScreenRequest.getWaitTime() > 0) {
                    ScreenSwitcher.showLoadingScene(loadingScreenRequest.getWaitTime());
                } else {
                    ScreenSwitcher.showLoadingScene();
                }
            }
            case SERVER_CHOICE_SCREEN_REQUEST -> {
                ScreenSwitcher.showButtonsScene();
            }
            case SERVER_ANSWER_RESULTS_SCREEN_REQUEST -> {
                QuestionResultsScreenRequest questionResultsScreenRequest = (QuestionResultsScreenRequest) message;
                ScreenSwitcher.showQuestionResultsScene(questionResultsScreenRequest);
            }
            case SERVER_FINAL_SCORE_SCREEN_REQUEST -> {
                FinalScoreScreenRequest finalScoreScreenRequest = (FinalScoreScreenRequest) message;
                ScreenSwitcher.showScoreboardScene(finalScoreScreenRequest);
            }
            case SERVER_CLOSE_CONNECTION_REQUEST -> closeConnection();
            default -> System.err.println("Unknown message");
        }

        return message.getId();
    }

    public void closeRequest() throws IOException {
        shouldStop = true;
        System.out.println("Closed connection from " + socket.getInetAddress());
        this.writer.writeObject(new CloseConnectionRequest());
        closeConnection();
    }

    public void closeConnection() throws IOException {
        reader.close();
        writer.close();
        socket.close();
        ScreenSwitcher.showLoginScene();
    }

    public void setLoginController(LoginScreenScreenController loginScreenController) {
        this.loginScreenController = loginScreenController;
    }

    public void sendAnswer(int selectedAnswer) throws IOException {
        this.writer.writeObject(new AnswerRequest(selectedAnswer));
    }
}
