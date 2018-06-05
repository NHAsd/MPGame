package events;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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
import javafx.scene.control.TextArea;

public class Message implements EventHandler<KeyEvent> {
	Scene scene;
	
	public Message(Scene scene) {
		this.scene = scene;
	}
	public void handle(KeyEvent event) {

			
		
	}

}
