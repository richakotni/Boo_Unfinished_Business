import java.io.File;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class SplashScreen extends World {
	
	public SplashScreen() {
		this.remove(getLife());
		this.remove(getCoinCount());
		this.remove(getCoinImage());
		
		String imageFile = getClass().getClassLoader().getResource("resources/splashPage.png").toString();
		super.setImage(imageFile);
	 	
		ImageObject gameTitle = new ImageObject("resources/unfinished_business.png");
		this.add(gameTitle); 
		gameTitle.setFitHeight(130);
		gameTitle.setFitWidth(480);        
		gameTitle.setX(1);
		gameTitle.setY(1);
		gameTitle.setPreserveRatio(true);

		ImageObject peachimg = new ImageObject("resources/peach_normal.png");
		this.add(peachimg); 
		peachimg.setFitHeight(150);
    	peachimg.setFitWidth(75);        
    	peachimg.setX(10);
    	peachimg.setY(350); 		
		
		ImageObject luigiimg = new ImageObject("resources/luigi_normal.png");
		this.add(luigiimg); 
		luigiimg.setFitHeight(100);
    	luigiimg.setFitWidth(100);        
    	luigiimg.setX(400);
    	luigiimg.setY(390); 
	 	
		ImageObject marioimg = new ImageObject("resources/mario_normal.png");
		this.add(marioimg); 
		marioimg.setFitHeight(100);
    	marioimg.setFitWidth(100);        
    	marioimg.setX(150);
    	marioimg.setY(290); 

		ImageObject booimg = new ImageObject("resources/boo_right.png");
		this.add(booimg); 
		booimg.setFitHeight(90);
    	booimg.setFitWidth(90);        
    	booimg.setX(10);
    	booimg.setY(150);     	
    	
    	Luma luma = new Luma();
		this.add(luma);
		
		ImageObject help = new ImageObject("resources/help.png");
		this.add(help);
		help.setFitHeight(90);
		help.setFitWidth(90);
		help.setX(100);
		help.setY(210);
		help.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				WebView myWeb = new WebView();
				WebEngine webEngine = myWeb.getEngine(); 
				File myFile = new File("gameDescription.html"); 
				webEngine.load("file:" + myFile.getAbsolutePath());
				Stage f = new Stage(); 
				Scene s = new Scene(myWeb);
				f.setScene(s);
				f.show();
			}
			
		});
				
		ImageObject play = new ImageObject("resources/play.png");
		this.add(play);
		play.setFitHeight(90);
		play.setFitWidth(90);
		play.setX(210);
		play.setY(210);
		play.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					BorderPane p = (BorderPane) getParent();
					p.setCenter(getWorld("LIBRARY"));
					stop();
					getWorld("LIBRARY").start();
					getWorld("LIBRARY").requestFocus();
				}
			}
			
		});
		
		luma.setFitHeight(50);
		luma.setFitWidth(50);
		luma.setX(40);
		luma.setY(40);
		luma.setMovement(Movement.HORIZONTAL);
		luma.setFitHeight(50);
		luma.setFitWidth(50);
		luma.setPosition(250, 150, 400);
		luma.setSpeed(4);
    	
    	Coin coin1 = new Coin();
    	this.add(coin1); 
    	coin1.setX(400);
		coin1.setY(300);
		coin1.setFitHeight(40);
		coin1.setFitWidth(40);
	}

	@Override
	public void act(long now) {
		
	}
}
