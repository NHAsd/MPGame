package MPGame.MPGame;



import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

public class MsgBox extends Application {
	
	private TextArea message= new TextArea();
	
	public Scene getScene() {
		VBox root = new VBox();
		root.getChildren().add(message);
		Scene scene = new Scene(root,300,200);
		return scene;
	}

	@SuppressWarnings("restriction")
	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setScene(getScene());
		primaryStage.show();
		message.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)){
					System.out.println(message.getText());
					primaryStage.close();
				}
				
			}
		});

	}

}
