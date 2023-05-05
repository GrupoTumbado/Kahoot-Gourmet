package pw.espana.kahootgourmet.server;

import pw.espana.kahootgourmet.server.game.Answer;
import pw.espana.kahootgourmet.server.game.Question;
import pw.espana.kahootgourmet.server.game.User;
import pw.espana.kahootgourmet.server.messages.Message;
import pw.espana.kahootgourmet.server.messages.MessageId;
import pw.espana.kahootgourmet.server.messages.client.requests.AnswerRequest;
import pw.espana.kahootgourmet.server.messages.client.requests.JoinRequest;
import pw.espana.kahootgourmet.server.messages.server.requests.AnswerResultsScreenRequest;
import pw.espana.kahootgourmet.server.messages.server.requests.ChoiceScreenRequest;
import pw.espana.kahootgourmet.server.messages.server.requests.LoadingScreenRequest;
import pw.espana.kahootgourmet.server.messages.server.responses.JoinResponse;
import pw.espana.kahootgourmet.server.messages.server.responses.MessageResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.Duration;
import java.time.Instant;

public class ServerUserThread extends Thread implements Comparable<ServerUserThread> {
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
            reader = new ObjectInputStream(socket.getInputStream());
            writer = new ObjectOutputStream(socket.getOutputStream());

            Object obj;
            do {
                obj = reader.readObject();
            } while (processMessage(obj) != MessageId.CLIENT_CLOSE_CONNECTION_REQUEST.getValue());
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
                    return rejectConnection("Parece que este nombre de usuario ya ha está en uso. Por favor escoge otro.");
                } else if (ServerApplication.getPin() != joinRequest.getPin()) {
                    return rejectConnection("Este pin no es correcto. Intenta nuevamente.");
                }

                user = new User(joinRequest.getUsername());
                this.writer.writeObject(new JoinResponse(ServerApplication.getQuestionnaireSize(), ServerApplication.getQuestionnaireAnswerTime()));
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
            }
            case CLIENT_CLOSE_CONNECTION_REQUEST -> closeRequest();
            default -> System.err.println("Unknown message");
        }

        return message.getId();
    }

    public void closeRequest() throws IOException {
        System.out.println("Closed connection from " + socket.getInetAddress());
        ServerApplication.removeUserThread(this);
        socket.close();
    }

    private int rejectConnection(String message) throws Exception {
        this.writer.writeObject(new JoinResponse(message));
        sleep(1000); // We wait for a little bit before closing the connection so the client can process the rejection message
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
        Answer answer = question.answers()[answerRequest.getAnswer()];
        this.writer.writeObject(new AnswerResultsScreenRequest(answer.correct(), pointsGained, user.getScore(), place));
    }

    public String getUsername() {
        return user.getUsername();
    }
}
