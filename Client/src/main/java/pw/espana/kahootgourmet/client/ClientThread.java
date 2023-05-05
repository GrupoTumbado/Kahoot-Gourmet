package pw.espana.kahootgourmet.client;

import pw.espana.kahootgourmet.client.messages.Message;
import pw.espana.kahootgourmet.client.messages.MessageId;
import pw.espana.kahootgourmet.client.messages.client.requests.JoinRequest;
import pw.espana.kahootgourmet.client.messages.client.responses.MessageResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.time.Duration;

public class ClientThread extends Thread {
    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private final String ip;
    private final int port;
    private final String username;
    private final int pin;

    public ClientThread(String ip, int port, String username, int pin) throws Exception {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.pin = pin;

        socket = new Socket(ip, port);
        reader = new ObjectInputStream(socket.getInputStream());
        writer = new ObjectOutputStream(socket.getOutputStream());

        System.out.println("Attempting to login into " + ip + ":" + port);
        writer.writeObject(new JoinRequest(pin, username));
    }

    @Override
    public void run() {
        try {
            Object obj;
            do {
                obj = reader.readObject();
            } while (processMessage(obj) != MessageId.CLIENT_CLOSE_CONNECTION_REQUEST.getValue());
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
                System.out.println("Received test message from client");
            }
            case SERVER_JOIN_RESPONSE -> {
                /*
                Parent root = FXMLLoader.load(Objects.requireNonNull(ClientApplication.class.getResource("loading-screen-view.fxml")));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                */
            }
            default -> System.err.println("Unknown message");
        }

        return message.getId();
    }
}
