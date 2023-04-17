import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server {
	private int port;
	private final Set<ServerThread> ftpServerThreads = new HashSet<>();
	public Server(int port) {
		this.port = port;
	}

	private void execute() {
		try (ServerSocket serverSocket = new ServerSocket(port)) {

			System.out.println("Server is listening on port " + port);

			while (true) {
				Socket socket = serverSocket.accept();
				ServerThread newWorker = new ServerThread(socket, this);
				this.ftpServerThreads.add(newWorker);
				newWorker.start();
			}
		} catch (Exception e) {
			System.out.println("Error in the server: " + e.getMessage());
			e.printStackTrace();
		}
	}

	void removeThread(ServerThread worker) {
		ftpServerThreads.remove(worker);
	}

	public static void main(String[] args) {
		int port = 1090;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}

		Server server = new Server(port);
		server.execute();
	}
}