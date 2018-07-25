package FX;

import Server.DataServer;
import Server.MessageServer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ServerMenager extends Application {
	private DataServer dataServer;
	private MessageServer messageServer;


	private TextArea console;
	private TextArea message;
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(getScene());		
		stage.show();
		
		dataServer = new DataServer(console, message);
		Thread dataThread = new Thread(dataServer);
		dataThread.start();
		
		messageServer =new MessageServer(console, message);
	//	messageServer.connect(); // do konstrutkora wjebac i wyjebac elementy fx z konstryukotra i zrobic przelotki string z metod gdzie w fx beda wrzucane do komponetow
		Thread messageThread = new Thread(messageServer);
		messageThread.start();
		
	
	
	}


	public Scene getScene() {
		GridPane root = new GridPane();
		console = new TextArea();
		console.setEditable(false);
		console.setScrollTop(Double.MAX_VALUE);
		message = new TextArea();
		message.setEditable(false);
		message.setScrollTop(Double.MAX_VALUE);
        root.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        root.setVgap(10); //vertical gap in pixels
        root.setPadding(new Insets(10, 10, 10, 10));
		root.add(console, 0, 0);
		root.add(message, 0, 1);
		Scene scene = new Scene(root, 500, 400);
		return scene;
	}

}
