package FX;

import java.util.Optional;

import MPGame.MPGame.GameWindow;
import Server.GameServer;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FindGame extends Application {
	private GridPane root;
	private Scene scene;
	private Button join;
	private TextArea addresIP;
	private final int PORT = 50000;
	private Stage stage;
	private Stage menuStage;
	private String name;
	
	public FindGame(Stage stage, String name) {
		menuStage = stage;
		this.name = name;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		primaryStage.setScene(getScene());
		primaryStage.show();
		events();
		
		
	}
	
	public void events() {
		join.setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event event) {

				GameWindow gameWindow = new GameWindow(addresIP.getText(),PORT , name);
				try {
					gameWindow.start(new Stage());
					stage.close();
					menuStage.close();
				} catch (Exception e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Connect Fail");
					alert.setHeaderText(null);
					alert.setContentText("Problem z polaczeniem");

					alert.showAndWait();
			
				}
				finally {

				}


			}
		});
	}
	
	public Scene getScene() {
		root = new GridPane();
		scene = new Scene(root, 220, 50);
		addresIP = new TextArea();
		addresIP.setPrefSize(150, 30);
		join = new Button("Join");
		root.add(addresIP, 0, 0);
		root.add(join, 1, 0);
        root.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        root.setVgap(10); //vertical gap in pixels
        root.setPadding(new Insets(10, 10, 10, 10));
		return scene;
	}

}
