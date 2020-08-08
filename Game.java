import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class Game extends Application {
	private Boo boo;
	private Luma luma;
	private Coin coin;
	private BorderPane pane;
	private SplashScreen splashScreen;	
	private EndSplashPage game_Over ; 
	private WinSplashPage win_game;
	private Library library;
	private DiningRoom diningRoom;
	private Bedroom bedroom;
	private FightRoom1 fightRoom1;
	private FightRoom2 fightRoom2;
	private FightRoom3 fightRoom3; 
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setResizable(false);
		stage.setHeight(550);
		stage.setWidth(530);
				
		boo = new Boo();
		luma = new Luma();
		pane = new BorderPane();
		game_Over = new EndSplashPage();
		library = new Library();
		diningRoom = new DiningRoom();
		bedroom = new Bedroom();	
	    fightRoom1 = new FightRoom1();
	    fightRoom2 = new FightRoom2();
	    fightRoom3 = new FightRoom3(); 
		win_game = new WinSplashPage(); 
		coin = new Coin();
		splashScreen = new SplashScreen();
		
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		pane.setPrefSize(500, 500);
		pane.setCenterShape(true);

		BooControls handler = new BooControls(boo);
		
		
		splashScreen.setPrefSize(500, 500);
		splashScreen.addWorld("LIBRARY", library);
		library.setPrefSize(pane.getPrefWidth(),pane.getPrefHeight());
		library.addWorld("DINING_ROOM", diningRoom);
		library.addWorld("FIGHT_ROOM1", fightRoom1);
		library.setOnKeyPressed(handler);
		library.setOnKeyReleased(handler);
		library.add(boo);
		library.addWorld("START_PAGE", splashScreen);
		library.addWorld("GAME_OVER", game_Over);
		
		fightRoom1.setPrefSize(500,500);
		fightRoom1.addWorld("DINING_ROOM", diningRoom);
		fightRoom1.setOnKeyPressed(handler);
		fightRoom1.setOnKeyReleased(handler);
		fightRoom1.addWorld("LIBRARY", library);
		fightRoom1.addWorld("GAME_OVER", game_Over);
		
		fightRoom2.setPrefSize(500,500);
		fightRoom2.addWorld("BEDROOM", bedroom);
		fightRoom2.addWorld("DINING_ROOM", diningRoom);
		fightRoom2.setOnKeyPressed(handler);
		fightRoom2.setOnKeyReleased(handler);
		fightRoom2.addWorld("GAME_OVER", game_Over);
		
		fightRoom3.setPrefSize(500,500);
		fightRoom3.setOnKeyPressed(handler);
		fightRoom3.setOnKeyReleased(handler);
		fightRoom3.addWorld("GAME_OVER", game_Over);
		fightRoom3.addWorld("WIN_GAME", win_game);

		diningRoom.setPrefSize(500, 500);
		diningRoom.addWorld("BEDROOM", bedroom);
		diningRoom.setOnKeyPressed(handler);
		diningRoom.setOnKeyReleased(handler);
		diningRoom.addWorld("GAME_OVER", game_Over);
		diningRoom.addWorld("FIGHT_ROOM2", fightRoom2);
		library.addWorld("START_PAGE", splashScreen);
		
		bedroom.setPrefSize(500, 500);
		bedroom.setOnKeyPressed(handler);
		bedroom.setOnKeyReleased(handler);
		bedroom.addWorld("GAME_OVER", game_Over);
		bedroom.addWorld("FIGHT_ROOM3", fightRoom3);
		bedroom.addWorld("WIN_GAME", win_game);
		 	
		coin.setX(400);
		coin.setY(400);
		coin.setFitHeight(20);
		coin.setFitWidth(20);
		
		luma.setMovement(Movement.HORIZONTAL);
		luma.setFitHeight(50);
		luma.setFitWidth(50);
		luma.setPosition(250, 250, 400);
		luma.setSpeed(4);
		
		boo.setFitHeight(50);
		boo.setFitWidth(50);
		boo.setX(330);
		boo.setY(430);
		String fileName = getClass().getClassLoader().getResource("resources/Boo_Music.mp3").toString();
		AudioClip clip = new AudioClip(fileName); 
		clip.play();
		pane.setCenter(splashScreen);	
		splashScreen.start();
		splashScreen.setPrefSize(500, 500);		
		splashScreen.setPrefSize(500, 500);		
		stage.show();
		splashScreen.requestFocus();
	}
	
}