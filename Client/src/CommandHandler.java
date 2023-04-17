import java.io.DataInputStream;
import java.io.DataOutputStream;

public class CommandHandler extends Thread {
    private DataInputStream reader;
    private DataOutputStream writer;
    private String command;

    public CommandHandler(String command) throws Exception {
        this.reader = Client.GetReader();
        this.writer = Client.GetWriter();
        this.command = command;
    }

    public void run() {
        try {
            processCommand(this.command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean processCommand(String message) throws Exception {
        String[] tokens = message.split(" ");

        switch (tokens[0].toUpperCase()) {
            case "CLOSE" -> {
                closeCommand(tokens);
                return true;
            }
            case "GET" -> getCommand(tokens);
            case "PUT" -> putCommand(tokens);
            case "LCD" -> lcdCommand(tokens);
            case "CD" -> cdCommand(tokens);
            case "LS" -> lsCommand(tokens);
            case "DELETE" -> deleteCommand(tokens);
            case "MPUT" -> mPutCommand(tokens);
            case "MGET" -> mGetCommand(tokens);
            case "RMDIR" -> rmDirCommand(tokens);
            case "PWD" -> pwdCommand(tokens);
            default -> System.out.println("Unknown command: " + tokens[0].toUpperCase());
        }
        return false;
    }

    private void closeCommand(String[] tokens) throws Exception {
        writer.writeUTF(tokens[0]);
    }

    private void getCommand(String[] tokens) throws Exception {
        writer.writeUTF(String.join(" ", tokens));
    }

    private void putCommand(String[] tokens) throws Exception {
        writer.writeUTF(tokens[0]);
    }

    private void lcdCommand(String[] tokens) throws Exception {
        writer.writeUTF(String.join(" ", tokens));
    }

    private void cdCommand(String[] tokens) throws Exception {
        writer.writeUTF(String.join(" ", tokens));
    }

    private void lsCommand(String[] tokens) throws Exception {
        writer.writeUTF(tokens[0]);
    }

    private void deleteCommand(String[] tokens) throws Exception {
        writer.writeUTF(String.join(" ", tokens));
    }

    private void mPutCommand(String[] tokens) throws Exception {
        writer.writeUTF(String.join(" ", tokens));
    }

    private void mGetCommand(String[] tokens) throws Exception {
        writer.writeUTF(String.join(" ", tokens));
    }

    private void rmDirCommand(String[] tokens) throws Exception {
        writer.writeUTF(String.join(" ", tokens));
    }

    private void pwdCommand(String[] tokens) throws Exception {
        writer.writeUTF(tokens[0]);
    }
}
