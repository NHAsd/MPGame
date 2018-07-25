package MPGame.MPGame;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import events.MovePlayer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.Stage;

public class GameWindow extends Application {
	Pane root;
	private String adress;
	private int port;
	private final int portMes = 50001;
	private String name;
	private Socket connectServer = null;
	private Socket connectMessage = null;
	public Position p1;
	public Position p2;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private DataInputStream inMsg;
	public DataOutputStream send;



	public Player player;
	public Player playerEnemy;
	private TextArea list = new TextArea();
	private Stage stage;
	
	public GameWindow(String Adress, int PORT, String name) {
		this.adress = adress;
		this.port = PORT;
		this.name = name;
	}



	public GameWindow(String name) {
		this.name = name;
		this.adress = "127.0.0.1"; // wjebac get local adres czy cos 
		this.port = 50000;
	}


	public void serverConnect() throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		connectServer = new Socket(adress, port);
		connectMessage = new Socket(adress, portMes);

		while(true){
		
				if(connectServer.isConnected() && connectMessage.isConnected()) {
					list.setText(list.getText()+" connected "+connectServer +"\n");
					inMsg = new DataInputStream(connectMessage.getInputStream());
					send = new DataOutputStream(connectMessage.getOutputStream());
					send.writeUTF("Incoming " + name);
					send.flush();
					break;
				}

		} 

		
		in = new ObjectInputStream(connectServer.getInputStream());
		out = new ObjectOutputStream(connectServer.getOutputStream());

		Position p = new Position(name);
		out.writeObject(p);
		out.flush();

		p1 = (Position) in.readObject();
		player = new Player(p1, Color.BLUE);
		
	
		p2 = (Position) in.readObject();
		playerEnemy = new Player(p2, Color.RED); // wjebac kolor do wyboru gracza wysylany przez serwer na starcie
	

	}
	

	
	
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		serverConnect();
		Thread move = new Thread(new Runnable() {
			
			public void run() {
				try {
					while(true) {			
						p2 = (Position) in.readObject();	
						p1 = (Position) in.readObject();
						out.writeObject(player.getPosition());
						
						playerEnemy.setPosition(p2);
	//					player.setPosition(p1);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		move.start();

		Thread message = new Thread(new Runnable() {
			public void run() {
				while(true) {
					try {
						list.setText(list.getText() + " " + inMsg.readUTF() + "\n");			
						list.setScrollTop(list.getScrollTop() + Double.MAX_VALUE);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
		}); 
		message.start();
		

		


		Scene scene = getScene();

		stage.setScene(scene);
		checkPosition();
		list.setOnKeyPressed(new MovePlayer(player, scene, send)); // pozneij smienic konstruktor


		stage.show();
	}
	
	@SuppressWarnings("restriction")
	public void checkPosition() {
	AnimationTimer a = new AnimationTimer() {

		@Override
		public void handle(long arg0) {
			player.update();
			if(player.getShape().getTranslateY()<0) {
				player.getShape().setTranslateY(350);
			}
			if(player.getShape().getTranslateY()>350) {
				player.getShape().setTranslateY(0);
			}
			if(player.getShape().getTranslateX()<0) {
				player.getShape().setTranslateX(450);
			}
			if(player.getShape().getTranslateX()>450) {
				player.getShape().setTranslateX(0);
			}
			
		}
	};

		a.start();
		AnimationTimer b = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				player.update();
				
			}
		};

			b.start();
	}
	
	public Scene getScene() {
		root = new Pane();
		VBox hud = new VBox();
		Scene scene = new Scene(root,500,500);
		hud.setTranslateY(400);
		root.getChildren().addAll(player.getShape(),playerEnemy.getShape(), hud);
		hud.getChildren().addAll(list);
		list.setPrefSize(500, 100);
		list.setEditable(false);
		return scene;
	}
	






}
