package events;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import MPGame.MPGame.Bullet;
import MPGame.MPGame.GameWindow;
import MPGame.MPGame.MsgBox;
import MPGame.MPGame.Player;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MovePlayer implements EventHandler<KeyEvent> {
	Player player;
	Scene scene;
	DataOutputStream message;
	DataOutputStream bulletShoot;



	
	public MovePlayer(Player player, Scene scene, DataOutputStream message) throws IOException {

		this.player = player;
		this.scene = scene;
		this.message = message;

	}

	public void handle(KeyEvent event) {
		if(event.getCode().equals(KeyCode.T)) {
			MsgBox msgBox = new MsgBox(message, player.getName());
			try {
				msgBox.start(new Stage());
			} catch (Exception e) {
				e.printStackTrace();
			}
			

		} 

		else if(event.getCode().equals(KeyCode.W)){
			player.moveUp();
		}
		else if(event.getCode().equals(KeyCode.S)){
			player.moveDown();
		}
		else if(event.getCode().equals(KeyCode.A)){
			player.moveLeft();
		}
		else if(event.getCode().equals(KeyCode.D)){
			player.moveRight();
		}
		else if(event.getCode().equals(KeyCode.SPACE)){	
			



		}
			
	}
		
}




