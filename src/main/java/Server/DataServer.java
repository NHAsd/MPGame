package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import MPGame.MPGame.Player;
import MPGame.MPGame.Position;
import javafx.scene.control.TextArea;

public class DataServer implements Runnable {
	private ServerSocket server;
	private Position positionPlayer1;
	private Position positionPlayer2;
	private Socket connectPlayer1;
	private Socket connectPlayer2;
	private ObjectInputStream in2;
	private ObjectInputStream in;
	private ObjectOutputStream send2;
	private ObjectOutputStream send;		
	private TextArea console;
	private TextArea message;
	

	public DataServer(TextArea console, TextArea message) {
		this.console = console;
		this.message = message;
		

	}
	private void setPositionPlayer1() throws IOException, ClassNotFoundException {
		send = new ObjectOutputStream(connectPlayer1.getOutputStream());
		in = new ObjectInputStream(connectPlayer1.getInputStream());
		

		
		positionPlayer1 = (Position) in.readObject();
		positionPlayer1.setXY(1,1);
		Position wait = new Position(1000,1000);
		
		send.writeObject(positionPlayer1);
		send.writeObject(wait);
		
	}
	private void setPositionPlayer2() throws IOException, ClassNotFoundException {
		send2 = new ObjectOutputStream(connectPlayer2.getOutputStream());
		in2 = new ObjectInputStream(connectPlayer2.getInputStream());


		
		positionPlayer2 = (Position) in2.readObject();
		positionPlayer2.setXY(300, 300);
		
		send2.writeObject(positionPlayer2);
		send2.writeObject(positionPlayer1);

	}


	
	private void updateObject() throws IOException, ClassNotFoundException {
		while(true) {
			send.writeObject(positionPlayer2);
			send.writeObject(positionPlayer1);

			send2.writeObject(positionPlayer1);
			send2.writeObject(positionPlayer2);

			positionPlayer1 = (Position) in.readObject();
			positionPlayer2 = (Position) in2.readObject();

		}
	}


	public void run() {// wysylanie info na konsole w grze
		try {
			server = new ServerSocket(50000);
			console.setText(console.getText() + " Rozpoczecie sesji"+ server +"\n");	
			
			connectPlayer1 = server.accept();
			setPositionPlayer1();
			console.setText(console.getText() +" Polaczono" + connectPlayer1 + " name: " + positionPlayer1.getName()  +"\n");

			console.setText(console.getText() + " oczenikanie na gracza" +"\n");
			
			connectPlayer2 = server.accept();
			setPositionPlayer2();
			console.setText(console.getText() + " Polaczono" + connectPlayer2  + " name: " + positionPlayer2.getName()  +"\n");
			
			console.setText(console.getText() + " START " +"\n");			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	
		if (connectPlayer1.isConnected() && connectPlayer2.isConnected()) {
			try {
	//			setPosition();
				updateObject();
			} catch (IOException e) {

				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	}

}
