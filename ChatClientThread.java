package client_thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientThread {
	
	static int port = 3333;
	static Socket socket;
	static String serverAddress = null;
	static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {

		try {
			System.out.println("Enter server address: ");
			serverAddress = keyboard.nextLine();
			socket = new Socket(serverAddress, port);
			System.out.println("Connection opened");

			Thread t1 = new Thread(new KeyboardManager(socket));
			Thread t2 = new Thread(new SocketManager(socket));

			t1.start();
			t2.start();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
