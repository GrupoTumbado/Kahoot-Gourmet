import java.io.*;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Client {
	private static final String HOME = System.getProperty("user.home");
	private static Path workingDir = Paths.get(HOME);
	private static Socket socket;
	private static DataInputStream reader;
	private static DataOutputStream writer;

	public static DataInputStream GetReader() {
		return reader;
	}

	public static DataOutputStream GetWriter() {
		return writer;
	}

	public static void Close() throws IOException {
		socket.close();
	}

	public static void main(String[] args) {
		try {
			socket = new Socket("localhost", 1090);
			reader = new DataInputStream(socket.getInputStream());
			writer = new DataOutputStream(socket.getOutputStream());

			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

			do {
				String command = console.readLine();
				CommandHandler handler = new CommandHandler(command);
				handler.start();
			} while (true);
		} catch (Exception ignored) {
		}
	}
}