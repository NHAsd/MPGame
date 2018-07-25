package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import MPGame.MPGame.Position;

public class GameServer {
	static ServerSocket server;
	private static Position positionPlayer1 = new Position(1, 1, "p1");
	private static Position positionPlayer2 = new Position(100, 100, "p2");
	private static Socket connectPlayer1;
	private static Socket connectPlayer2;
	static ObjectInputStream in2;
	static ObjectInputStream in;
	static ObjectOutputStream send2;
	static ObjectOutputStream send;

	

	
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		server = new ServerSocket(50000);
		System.out.println("Rozpoczecie sesji"+ server);
		connectPlayer1 = server.accept();

		System.out.println("Polaczono" + connectPlayer1);
		System.out.println("oczenikanie na gracza");
		connectPlayer2 = server.accept();
		System.out.println("Polaczono" + connectPlayer2);
		
		if (connectPlayer1.isConnected() && connectPlayer2.isConnected()) {
			setPosition();
			updateObject();

		}
		
	}
	
	public static void setPosition() throws IOException, ClassNotFoundException {
		send2 = new ObjectOutputStream(connectPlayer2.getOutputStream());
		send = new ObjectOutputStream(connectPlayer1.getOutputStream());
		in2 = new ObjectInputStream(connectPlayer2.getInputStream());
		in = new ObjectInputStream(connectPlayer1.getInputStream());
	
		
		send.writeObject(positionPlayer1);
		send.flush();
		send.writeObject(positionPlayer2);
		send.flush();
		send2.writeObject(positionPlayer2);
		send2.flush();
		send2.writeObject(positionPlayer1);
		send2.flush();
//		positionPlayer1 = (Position) in.readObject();
//		positionPlayer2 = (Position) in2.readObject();


	}

	
	public static void updateObject() throws IOException, ClassNotFoundException {
		while(true) {
			send.writeObject(positionPlayer2);

			send2.writeObject(positionPlayer1);

			positionPlayer1 = (Position) in.readObject();
			positionPlayer2 = (Position) in2.readObject();

		}
	}
	

		
}



