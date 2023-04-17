import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServerThread extends Thread {
    private static final String HOME = System.getProperty("user.home");
    private Path workingDir = Paths.get(HOME);
    private final Socket socket;
    private final Server server;
    private DataInputStream reader;
    private DataOutputStream writer;

    public ServerThread(Socket socket, Server server) throws Exception {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            System.out.println("New connection from " + socket.getInetAddress());
            reader = new DataInputStream(socket.getInputStream());
            writer = new DataOutputStream(socket.getOutputStream());

            String clientMessage;
            do {
                clientMessage = reader.readUTF();
            } while (!processMessage(clientMessage));

            server.removeThread(this);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean processMessage(String message) throws Exception {
        String res;
        String[] tokens = message.split(" ");

        switch (tokens[0].toUpperCase()) {
            case "CLOSE" -> {
                res = closeRequest(tokens);
                this.writer.writeUTF(res);
                return true;
            }
            case "GET" -> res = getRequest(tokens);
            case "PUT" -> res = putRequest(tokens);
            case "LCD" -> res = lcdRequest(tokens);
            case "CD" -> res = cdRequest(tokens);
            case "LS" -> res = lsRequest(tokens);
            case "DELETE" -> res = deleteRequest(tokens);
            case "MPUT" -> res = mPutRequest(tokens);
            case "MGET" -> res = mGetRequest(tokens);
            case "RMDIR" -> res = rmDirRequest(tokens);
            case "PWD" -> res = pwdRequest(tokens);
            default -> res = "Bad Request";
        }

        this.writer.writeUTF(res);
        return false;
    }

    private String closeRequest(String[] tokens) throws Exception {
        System.out.println("Closed connection from " + socket.getInetAddress());
        return "CLOSE";
    }

    private String getRequest(String[] tokens) throws Exception {
        return "";
    }

    private String putRequest(String[] tokens) throws Exception {
        return "";
    }

    private String lcdRequest(String[] tokens) throws Exception {
        return "";
    }

    private String cdRequest(String[] tokens) throws Exception {
        workingDir = workingDir.resolve(tokens[1]).normalize();
        return workingDir.toString();
    }

    private String lsRequest(String[] tokens) throws Exception {
        try {
            Stream<Path> stream = Files.list(workingDir);
            String tree = stream
                    .filter(Files::isDirectory)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.joining("\n", "", ""));
            stream = Files.list(workingDir);
            tree += "\n" + stream
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.joining("\n", "", ""));
            return tree;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String deleteRequest(String[] tokens) throws Exception {
        return "";
    }

    private String mPutRequest(String[] tokens) throws Exception {
        return "";
    }

    private String mGetRequest(String[] tokens) throws Exception {
        return "";
    }

    private String rmDirRequest(String[] tokens) throws Exception {
        return "";
    }

    private String pwdRequest(String[] tokens) throws Exception {
        return workingDir.toString();
    }
}
