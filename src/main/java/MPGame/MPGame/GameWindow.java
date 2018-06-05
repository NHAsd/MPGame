package MPGame.MPGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import events.Message;
import events.MovePlayer;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class GameWindow extends Application {
	Player player = new Player("Asd", 20, 20);
	Player playerEnemy;
	TextArea list = new TextArea();

	PrintWriter printWriter;
	BufferedReader bufferedReader;



	
	public void server() throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 50000);
		printWriter = new PrintWriter(socket.getOutputStream(), true);
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		printWriter.print("czy dziala");
		printWriter.flush();

	}
	
	public Scene getScene() {
		Pane root = new Pane();
		VBox hud = new VBox();
		Scene scene = new Scene(root,500,500);
		hud.setTranslateY(400);
		root.getChildren().addAll(player.getShape(), hud);
		hud.getChildren().addAll(list);
		list.setPrefSize(500, 100);

		list.setEditable(false);

		return scene;
	}
	
	
	
	@Override
	public void start(Stage stage) throws Exception {
		server();
		Scene scene = getScene();
		
		printWriter.print("asd");
		printWriter.flush();
		stage.setScene(scene);
		checkPosition();
		list.setOnKeyPressed(new MovePlayer(player, scene));


		stage.show();
	}
	
	@SuppressWarnings("restriction")
	public void checkPosition() {
	AnimationTimer a = new AnimationTimer() {
		
		@Override
		public void handle(long arg0) {
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
	}
	
	
	public static void main(String[] args) {
		launch(args);

	}

}
