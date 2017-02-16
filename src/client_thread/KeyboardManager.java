package client_thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class KeyboardManager implements Runnable {

	private static Thread _t;
	private static Socket socket;

	KeyboardManager(Socket socket) {
		this.socket = socket;
		System.out.println("Creating KeyboardManager");
	}

	public void run() {
		try {
			Scanner keyboard = new Scanner(System.in);
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
			while (true) {
				String input = keyboard.nextLine();
				System.out.println("Client: " + input);

				// Check comand

				if (input.contains("/help")) {
					System.out.println(
							"/getCoords\nOttiene le coordinate gps\n\n/getTime\nOttiene la data\n\n/getSoftwareInfo\nOttiene informazioni riguardo il sistema operativo\n\n/getHardwareInfo\nOttiene informazioni riguardo l hardware del pc\n");
				} else if (input.contains("/close")) {
					printWriter.print("/close");
					// printWriter.close();
					socket.close();
					System.out.println("Connection closed");
					break;
				} else {
					printWriter.println(input);
					// printWriter.close();
					printWriter.flush();
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void start() {
		System.out.println("Starting KeyboardManager");
		if (_t == null) {
			_t = new Thread(_t, "KeyboardManager");
			_t.start();
		}
	}

}
