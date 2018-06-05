package MPGame.MPGame;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Serv {
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss =new ServerSocket(50000); 
		Socket s = ss.accept();
//		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
//		System.err.println(br.readLine());
		
		DataInputStream dis = new DataInputStream(s.getInputStream());
		int i = dis.readInt();
		System.err.println("wartosc "+i);
		i++;
		System.err.println("wartosc "+i);

	}

}
