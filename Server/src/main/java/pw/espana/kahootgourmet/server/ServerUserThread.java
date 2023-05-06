package pw.espana.kahootgourmet.server;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pw.espana.kahootgourmet.commons.game.Answer;
import pw.espana.kahootgourmet.commons.game.Question;
import pw.espana.kahootgourmet.commons.game.User;
import pw.espana.kahootgourmet.commons.messages.Message;
import pw.espana.kahootgourmet.commons.messages.MessageId;
import pw.espana.kahootgourmet.commons.messages.client.requests.AnswerRequest;
import pw.espana.kahootgourmet.commons.messages.client.requests.JoinRequest;
import pw.espana.kahootgourmet.commons.messages.server.requests.*;
import pw.espana.kahootgourmet.commons.messages.server.responses.JoinResponse;
import pw.espana.kahootgourmet.commons.messages.server.responses.MessageResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.Duration;
import java.time.Instant;

public class ServerUserThread extends Thread implements Comparable<ServerUserThread> {
    private boolean shouldStop;
    private final Socket socket;
    private User user;
    private Question question;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private Instant questionSentTime = Instant.now();
    private AnswerRequest answerRequest;
    private int pointsGained;

    public ServerUserThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("New connection from " + socket.getInetAddress());
            writer = new ObjectOutputStream(socket.getOutputStream());
            writer.flush();
            reader = new ObjectInputStream(socket.getInputStream());

            Object obj = null;
            do {
                try {
                    obj = reader.readObject();
                } catch (SocketTimeoutException e) {}
            } while (processMessage(obj) != MessageId.CLIENT_CLOSE_CONNECTION_REQUEST.getValue() && !shouldStop);

            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Override the compareTo method to implement the Comparable interface
    // and allow for natural ordering of ServerThread objects based on user score.
    @Override
    public int compareTo(ServerUserThread otherUser) {
        return Integer.compare(user.getScore(), otherUser.user.getScore());
    }

    private int processMessage(Object obj) throws Exception {
        if (!(obj instanceof Message message)) {
            System.err.println("Unknown message object");
            return MessageId.UNKNOWN_MESSAGE.getValue();
        }

        switch (MessageId.valueOf(message.getId())) {
            case CLIENT_DEBUG_REQUEST, CLIENT_DEBUG_RESPONSE -> {// Handle debug messages
                this.writer.writeObject(new MessageResponse());
                System.out.println("Received test message from client");
            }
            case CLIENT_JOIN_REQUEST -> { // Handle initial connection
                JoinRequest joinRequest = (JoinRequest) message;

                if (ServerApplication.isUsernameTaken(joinRequest.getUsername())) { // Username already in use. Reject connection
                    return rejectConnection("Este nombre de usuario ya ha está en uso. Por favor escoge otro.");
                } else if (ServerApplication.getPin() != joinRequest.getPin()) {
                    return rejectConnection("Este pin no es correcto. Intenta nuevamente.");
                }

                user = new User(joinRequest.getUsername());
                this.writer.writeObject(new JoinResponse(ServerApplication.getQuestionnaireSize(), ServerApplication.getQuestionnaireAnswerTime()));
                ServerApplication.addConnectedUsers(this);
            }
            case CLIENT_ANSWER_REQUEST -> { // Handle answer to question from client
                if (question == null || answerRequest != null) break; // Check if we have a valid question, and if the client has not answered

                answerRequest = (AnswerRequest) message;
                Answer answer = question.answers()[answerRequest.getAnswer()];

                if (answer.correct()) {
                    int answerTime = ServerApplication.getQuestionnaire().getAnswerTime();
                    Duration timeToAnswer = Duration.between(questionSentTime, answerRequest.getCreatedAt());
                    float scoreMultiplier = (float) answerTime / (answerTime + ((float) timeToAnswer.toMillis() / 1000.0f));
                    pointsGained = (int) (question.scoreValue() * scoreMultiplier);

                    user.addScore(pointsGained);
                }

                ServerApplication.incrementAnswerCount();
            }
            case CLIENT_CLOSE_CONNECTION_REQUEST -> closeConnection();
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
        ServerApplication.removeUserThread(this);
        reader.close();
        writer.close();
        socket.close();
    }

    private int rejectConnection(String message) throws Exception {
        this.writer.writeObject(new JoinResponse(message));
        closeRequest();
        return MessageId.CLIENT_CLOSE_CONNECTION_REQUEST.getValue();
    }

    public void displayLoadingScreen(int waitTime) throws Exception {
        this.writer.writeObject(new LoadingScreenRequest(waitTime));
    }

    public void enableAnswerIntake(Question question) throws Exception {
        this.question = question;
        this.writer.writeObject(new ChoiceScreenRequest());
        questionSentTime = Instant.now();
    }

    public void sendAnswerResults(int place) throws Exception {
        if (answerRequest == null) {
            this.writer.writeObject(new QuestionResultsScreenRequest(false, 0, user.getScore(), place));
        } else {
            Answer answer = question.answers()[answerRequest.getAnswer()];
            this.writer.writeObject(new QuestionResultsScreenRequest(answer.correct(), pointsGained, user.getScore(), place));
        }
    }

    public void sendFinalScore(int place) throws Exception {
        this.writer.writeObject(new FinalScoreScreenRequest(place, user.getScore()));
    }

    public String getUsername() {
        return user.getUsername();
    }

    public StringProperty usernameProperty() {
        return new SimpleStringProperty(user.getUsername());
    }
}
