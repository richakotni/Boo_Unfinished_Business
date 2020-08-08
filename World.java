import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

public abstract class World extends Pane {
	
	private HashMap<String,World> subWorlds;
	private AnimationTimer timer;
	private HashSet<KeyCode> currentActiveKeys;
	private Image worldImage;
	private Life life;
	private CoinCount coins;
	private ImageObject coinImage;
	private boolean isLocked;
	
	public World() {
		isLocked = true;
		life = new Life(3);
		this.add(life);
		life.setX(20);
		life.setY(20);
		life.setFitHeight(25.5);
		life.setFitWidth(68);
		coinImage = new ImageObject("resources/coin_1.png");
		this.add(coinImage);
		coinImage.setX(20);
		coinImage.setY(50);
		coinImage.setFitHeight(25.5);
		coinImage.setFitWidth(25.5);
		coins = new CoinCount();
		this.add(coins);
		coins.setX(60);
		coins.setY(50);
		coins.setFitHeight(25.5);
		coins.setFitWidth(25.5);
		subWorlds = new HashMap<String,World>();
		currentActiveKeys = new HashSet<KeyCode>();
		
		timer = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				act(now);
				for(int i = 0;i<getObjects(Actor.class).size();i++) {
					getObjects(Actor.class).get(i).act(now);
				}
			}
		};
		worldImage = null;
	}
	
	public boolean isLocked() {
		return isLocked;
	}
	
	public void setIsLocked(boolean b) {
		isLocked = b;	
	}
	
	public CoinCount getCoinCount() {
		return coins;
	}
	
	public ImageObject getCoinImage() {
		return coinImage;
	}
	
	public void setCoinCount(CoinCount c) {
		coins.setNumCoins(c.getNumCoins());
	}
	
	public Life getLife() {
		return life;
	}
	
	public void setLife(Life l) {
		life.setNumLives(l.getNumLives());
	}
	
	public void addWorld(String name, World world) {
		subWorlds.put(name, world);
	}
	
	public void removeWorld(String name) {
		subWorlds.remove(name);
	}
	
	public World getWorld(String name) {
		return subWorlds.get(name);
	}
	
	public void setImage(String imageFile) {
		worldImage = new Image(imageFile);
		BackgroundSize size = new BackgroundSize(this.getPrefWidth(), this.getPrefHeight(), false, false, true, true);
		BackgroundImage im = new BackgroundImage(worldImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size); 
		this.setBackground(new Background(im));
	}
	
	public void removeActiveKeyCodes() {
		if(isActive(KeyCode.LEFT)) {
			this.removeKeyCode(KeyCode.LEFT);
		}
		if(isActive(KeyCode.RIGHT)) {
			this.removeKeyCode(KeyCode.RIGHT);
		}
		if(isActive(KeyCode.UP)) {
			this.removeKeyCode(KeyCode.UP);
		}
		if(isActive(KeyCode.DOWN)) {
			this.removeKeyCode(KeyCode.DOWN);
		}
	}
	
	public void addKeyCode(KeyCode code) {
		currentActiveKeys.add(code);
	}
	
	public void removeKeyCode(KeyCode code) {
		currentActiveKeys.remove(code);
	}
	
	public boolean isActive(KeyCode code) {
		return currentActiveKeys.contains(code);
	}
	
	public abstract void act(long now);
	
	public <A extends Actor> java.util.List<A> getObjects(java.lang.Class<A> cls) {
		List<A> list = new ArrayList<A>();
		for(int i = 0;i<this.getChildren().size();i++) {
			if(cls.isInstance(this.getChildren().get(i))) {
				list.add(cls.cast(this.getChildren().get(i)));
			}
		}
		return list;
	}
	
	public void remove(Actor actor) {
		this.getChildren().remove(actor);
	}
	
	public void add(Actor actor) {
		this.getChildren().add(actor);
	}
	
	public <A extends Actor> A getOneObject(java.lang.Class<A> cls) {
		List<A> list = this.getObjects(cls);
		if(list.size()>0) {
			return list.get(0);
		}
		else {
			return null;
		}
	}
	
	public void stop() {
		timer.stop();
	}
	
	public void start() {
		timer.start();
	}
	
	public void finishGame() {
		BorderPane p = (BorderPane) getParent();
		p.setCenter(getWorld("GAME_OVER"));
		
		stop();
		
		getWorld("GAME_OVER").setPrefSize(500, 500);
		getWorld("GAME_OVER").start();
		String fileName = getClass().getClassLoader().getResource("resources/game_overSound.mp3").toString();
		AudioClip clip = new AudioClip(fileName);
		clip.play();
		getWorld("GAME_OVER").requestFocus();
	}
	
	
	public void goToNextRoom(String nextRoomName,
							 Boo boo,
			                 double booPosX, double booPosY) {
		World nextRoom = getWorld(nextRoomName);
		BorderPane p = (BorderPane) getParent();
		p.setCenter(nextRoom);
		removeActiveKeyCodes();
		
		String fileName = getClass().getClassLoader().getResource("resources/door_creak_closing.mp3").toString();
		AudioClip clip = new AudioClip(fileName);
		clip.play();
		
		nextRoom.setCoinCount(getCoinCount());
		nextRoom.setLife(getLife());
		if(boo != null) {
			nextRoom.add(boo);
			boo.setX(booPosX);
			boo.setY(booPosY);
		}
				
		stop();
		nextRoom.setPrefSize(500, 500);
		nextRoom.start();
		nextRoom.requestFocus();
	}
	
	public void winGame(String winPageName) {
		World nextRoom = getWorld(winPageName);
		BorderPane p = (BorderPane) getParent();
		p.setCenter(nextRoom);
		  		
		stop();
		nextRoom.start();
		nextRoom.requestFocus();
	}
	
}
