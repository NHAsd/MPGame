package MPGame.MPGame;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameWindow extends Application {
	final Rectangle player = new Rectangle(50,50,Color.BLUE);
	final TextArea message = new TextArea();
	PrintWriter printWriter;
	ObservableList<String> messagesFromServer = FXCollections.observableArrayList();
	Scanner in;
	
	public void server() throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 50000);
		printWriter= new PrintWriter(socket.getOutputStream(), true);
		Scanner in = new Scanner(socket.getInputStream());
		messagesFromServer.add(in.nextLine());
	}
	
	
	
	
	@Override
	public void start(Stage stage) throws Exception {
		Thread chat = new Thread(new Runnable() {
			
			public void run() {
				try {
					server();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		chat.start();
		checkPosition();
		
		Pane root = new Pane();
		VBox hud = new VBox();
		Scene scene = new Scene(root,500,500);
		stage.setScene(scene);
		
		hud.setTranslateY(400);
		
		ListView list = new ListView(messagesFromServer);
	
		list.setPrefSize(500, 50);
		

		message.setPrefSize(500,50);
		message.setDisable(true);
		
		
		
		root.getChildren().addAll(player, hud);
		hud.getChildren().addAll(message, list);


		message.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					message.setDisable(true);
					printWriter.print(message.getText());
					printWriter.flush();
					message.setText("");

					
				}else if(event.getCode().equals(KeyCode.ESCAPE)) {
					message.setText("");
					message.setDisable(true);
				}
			}
		});
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.T)) {
					message.setDisable(false);

				}
				else if(event.getCode().equals(KeyCode.W)){
					player.setTranslateY(player.getTranslateY()-50);
				}
				else if(event.getCode().equals(KeyCode.S)){
					player.setTranslateY(player.getTranslateY()+50);
				}
				else if(event.getCode().equals(KeyCode.A)){
					player.setTranslateX(player.getTranslateX()-50);
				}
				else if(event.getCode().equals(KeyCode.D)){
					player.setTranslateX(player.getTranslateX()+50);
				}
				else if(event.getCode().equals(KeyCode.SPACE)){
					System.out.println("strzelam");
				}
			}
		});

		
		
		stage.show();
	}
	
	public void checkPosition() {
	AnimationTimer a = new AnimationTimer() {
		
		@Override
		public void handle(long arg0) {
			if(player.getTranslateY()<0) {
				player.setTranslateY(350);
			}
			if(player.getTranslateY()>350) {
				player.setTranslateY(0);
			}
			if(player.getTranslateX()<0) {
				player.setTranslateX(450);
			}
			if(player.getTranslateX()>450) {
				player.setTranslateX(0);
			}
			
		}
	};
	a.start();
	}
	
	
	
	
	
	public static void main(String[] args) {
		launch(args);

	}

}
