package remote.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	public static ServerSocket		mainServerSocket;
	public static ArrayList<Socket>	Sockets;
	private static int				maxThreadCount;
	private static int				port;

	public static void start(int numThreads, int port){
		Server.port = port;
		maxThreadCount = numThreads;
		try {
			mainServerSocket = new ServerSocket(port);
		}
		catch (IOException e) {
			System.err.println("Failed to listen on socket " + port + ", Exiting...");
			System.exit(1);
		}
	}

	public static void run(){
		do {
			try {
				Socket interim = mainServerSocket.accept();
				new Thread(()->{
					remote.server.Server.handle(interim);
				}).start();;
			}
			catch (IOException e) {} ;
		} while (true);
	}
	public static void handle(Socket connection) {
		String connectionHandshakeHeader;
		ObjectInputStream connectionInput = null;
		ObjectOutputStream connectionOutput = null;
		try {
			connectionInput = new ObjectInputStream(connection.getInputStream());
			connectionOutput = new ObjectOutputStream(connection.getOutputStream());
		} catch (IOException e) {
			System.out.println("Failed to create object stream. exiting...");
		} finally {
			try {
				connectionInput.close();
				connectionOutput.close();
			}
			catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}
