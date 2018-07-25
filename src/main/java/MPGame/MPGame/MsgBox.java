package MPGame.MPGame;



import java.io.DataOutputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MsgBox extends Application {
	
	private TextArea message= new TextArea();
	private DataOutputStream out;
	private String name;
	
	public MsgBox(DataOutputStream out, String name) {
		this.out = out;
		this.name = name;
	}
	
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
					try {
						out.writeUTF(name + ": " + message.getText());
						out.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					primaryStage.close();	
				}
				
			}
		});

	}

}
