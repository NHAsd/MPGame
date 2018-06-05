package MPGame.MPGame;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cli {
//http://cs.lmu.edu/~ray/notes/javanetexamples/
	public static void main (String [] args) throws UnknownHostException, IOException {

		Socket s = new Socket("127.0.0.1", 50000);
//		PrintWriter pw = new PrintWriter(s.getOutputStream());
//
//		
//		pw.println("testy");
//
//		pw.flush();
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		dos.writeInt(4);
		dos.flush();

		
				
	}
}
