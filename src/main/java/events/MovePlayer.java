package events;

import MPGame.MPGame.MsgBox;
import MPGame.MPGame.Player;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MovePlayer implements EventHandler<KeyEvent> {
	Player player;
	Scene scene;
	
	public MovePlayer(Player player, Scene scene) {
		this.player = player;
		this.scene = scene;
	}

	public void handle(KeyEvent event) {
		if(event.getCode().equals(KeyCode.T)) {
			MsgBox msgBox = new MsgBox();
			try {
				msgBox.start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
			System.out.println("strzelam");
		}
		
	}

}
