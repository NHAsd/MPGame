package MPGame.MPGame;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	static PrintWriter printWriter;
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(50000);
		System.out.println(serverSocket);
		Socket socket = serverSocket.accept();
		System.err.println(socket);
		printWriter = new PrintWriter(socket.getOutputStream());
		Scanner in = new Scanner(socket.getInputStream());
		while(true) {
			if(in.nextBoolean()) {
				System.err.println("otrzymano>: " + in.nextLine());
				
				printWriter.print(in.nextLine());
				printWriter.flush();
			}

			
		}
	}

}
