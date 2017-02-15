package client_thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketManager implements Runnable {

	private static Thread _t;
	private static Socket socket;

	SocketManager(Socket socket) {
		this.socket = socket;
		System.out.println("Creating SocketManager");
	}

	public void run() {
		try {
			Scanner scanner = new Scanner(socket.getInputStream());
			while (true) {

				String input = scanner.nextLine();
				System.out.println("Server: " + input);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void start() {
		System.out.println("Starting SocketManager");
		if (_t == null) {
			_t = new Thread(_t, "SocketManager");
			_t.start();
		}
	}

}