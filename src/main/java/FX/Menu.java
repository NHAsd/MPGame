package FX;

import java.util.Optional;

import MPGame.MPGame.GameWindow;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Menu extends Application {
	private GridPane root;
	private Scene scene;
	private Button multiplayer;
	private Button findgame;
	private Button singleplayer;
	private Button profile;
	private Button exit;
	private Stage stage;
	private String name;


	@Override
	public void start(Stage stage) throws 
	Exception {
		this.stage = stage;

		stage.setScene(getScene());
		stage.show();
		menu();
		
		events();		
	}
	public void events() {
		multiplayer.setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event event) {
				TextInputDialog dialog = new TextInputDialog("NoName");
				dialog.setTitle("Nowy gracz");
				dialog.setHeaderText("Utwórz profil");
				dialog.setContentText("Podaj nick:");

				// Traditional way to get the response value.
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
					name = result.get();
					
				}
				ServerMenager server = new ServerMenager();
				try {
					server.start(new Stage());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				GameWindow startGame = new GameWindow(name);
				try {
					startGame.start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
				stage.close();
			}
		});
		findgame.setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event event) {// jakis warunek wjebac ze jesli nei sczytal z SQL profilu to ma zarejestrowac sie tutaj i warunek w petli ze ma podac a nie wejsc z nullem
				TextInputDialog dialog = new TextInputDialog("NoName");
				dialog.setTitle("Nowy gracz");
				dialog.setHeaderText("Utwórz profil");
				dialog.setContentText("Podaj nick:");

				// Traditional way to get the response value.
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
					name = result.get();
				}
				FindGame findGame = new FindGame(stage, name);
				try {
					findGame.start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		singleplayer.setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event event) {
			
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("WORK IN PROGRESS!");
				alert.setHeaderText(null);
				alert.setContentText("narazie z botem nie pograsz pajacu xDD");

				alert.showAndWait();
			}
		});
		profile.setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event event) {
			
				
			}
		});
		exit.setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event event) {
				stage.close();			
			}
		});
	}
	
	public void menu() {
		multiplayer = new Button("HostGame");
		findgame = new Button("FindGame");
		singleplayer = new Button("PlayWithBot");
		profile = new Button("Proile");
		exit = new Button("Exit");
		root.add(multiplayer, 0, 0);
		root.add(findgame, 0, 1);
		root.add(singleplayer, 0, 2);
		root.add(profile, 0, 3);
		root.add(exit, 0, 4);
	}
	
	public Scene getScene() {
		root = new GridPane();
		scene = new Scene(root, 250, 300);
        root.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        root.setVgap(10); //vertical gap in pixels
        root.setPadding(new Insets(10, 10, 10, 10));
		return scene;
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
