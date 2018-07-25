package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.scene.control.TextArea;

public class MessageServer implements Runnable {
	private ServerSocket messageServer;
	private Socket connect1;
	private Socket connect2;
	private TextArea console;
	private TextArea message;
	private DataOutputStream send1;
	private DataOutputStream send2;
	private DataInputStream in1;
	private DataInputStream in2;
	

	
	public MessageServer(TextArea console, TextArea message)  {
		this.console = console;
		this.message = message;
	}
	
	public void connect() throws IOException {
		messageServer = new ServerSocket(50001);
		connect1 = messageServer.accept();
		connect2 = messageServer.accept();
		console.setText(console.getText() + "MessageChat" + connect1 +" " + connect2 + "\n");
		send1 = new DataOutputStream(connect1.getOutputStream());
		send2 = new DataOutputStream(connect2.getOutputStream());
		in1 = new DataInputStream(connect1.getInputStream());
		in2 = new DataInputStream(connect2.getInputStream());
	}

	
	public void messagesMenager() throws IOException {

		
		while(true) {
			if(in1.available()>0) {
				message.setText(message.getText()+in1.readUTF() + "\n");
				send1.writeUTF(message.getText());
				send1.flush();
				send2.writeUTF(message.getText());
				send2.flush();
			} else if(in2.available()>0) {
				message.setText(message.getText()+ in2.readUTF() + "\n");
				send1.writeUTF(message.getText());
				send1.flush();
				send2.writeUTF(message.getText());
				send2.flush();
			}

		}
	}
	

	

	public void run() {
		try {
			connect();
			messagesMenager();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
	}
	

}
