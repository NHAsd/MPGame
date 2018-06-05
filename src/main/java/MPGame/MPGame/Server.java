package MPGame.MPGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
//	private static PrintWriter out;
	private static BufferedReader in;
	
	
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(50000);
		System.out.println(serverSocket);
		Socket socket = serverSocket.accept();
		System.err.println(socket);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	//	out = new PrintWriter(socket.getOutputStream());
		
		while(true) {
			System.out.println(in.readLine());
		}
		

	}

}
