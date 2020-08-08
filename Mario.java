import java.util.HashMap;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import java.util.Random;

public class Mario extends Actor
{
	private HashMap<String,String> imageStrings; 
	private PathTransition transition; 
	private Line marioPath = new Line();
	private double pathLen;
	private double marioSpeed = 600.0/10.0 ;
	private double marioTime = 1;
	double booPosX ; 
	double booPosY;
	boolean booPresent = false;
	 
	public Mario(double startX, double startY) 
	{
		imageStrings = new HashMap<String,String>();
		imageStrings.put("NORMAL", getClass().getClassLoader().getResource("resources/mario_normal.png").toString());
		super.setImage(imageStrings.get("NORMAL"));

		transition = new PathTransition();
		transition.setNode(this);
		transition.setOrientation(OrientationType.NONE);
		transition.setAutoReverse(false);
		transition.setCycleCount(1);
		
		EventHandler<ActionEvent> sd = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) 
            { 
            	resetDirection();
            }
        };
            
		transition.setOnFinished(sd);
		
		this.setX(startX);
		this.setY(startY);
		
		marioPath.setStartX(startX);
		marioPath.setStartY(startY);
		double endX = 300;
		double endY = 300;
		pathLen = Math.sqrt(
				Math.pow((endY-startY), 2) + 
				Math.pow((endX-startX), 2) 
				); 
		marioTime = pathLen / marioSpeed;
		
		marioPath.setEndX(endX);
		marioPath.setEndY(endY);
		
		transition.setPath(marioPath);
		setSpeed(marioTime);
		
	}
	
	public void updateImage(String key) {
		super.setImage(imageStrings.get(key));
	} 
	
	public void resetDirection()
	{
		double oldX = marioPath.getEndX();
		double oldY = marioPath.getEndY();
		double endX;
		double endY;
		
 		marioPath.setStartX(oldX);  
		marioPath.setStartY(oldY);
		
		if(booPresent == true) 
		{
			endX = booPosX;
			endY = booPosY;
		}
		else 
		{
			Random rand = new Random();
			endX = rand.nextDouble() * 500.0 + 1;
			endY = rand.nextDouble() * 500.0 + 1;			
			if(endX > 500) endX = 500;
			if(endY > 500) endY = 500; 
	    }
		pathLen = Math.sqrt(
							Math.pow((endY-oldY), 2) + 
							Math.pow((endX-oldX), 2) 
							); 
		marioTime = pathLen / marioSpeed;
		marioPath.setEndX(endX);
		marioPath.setEndY(endY);
		setSpeed(marioTime);
 	}
	
	public void setSpeed(double seconds) {
		transition.setDuration(Duration.seconds(seconds));
	}
	
	public void setBooPos(double booX, double booY) 
	{
		booPosX = booX;
		booPosY = booY;
		booPresent = true;
	}
	
	@Override
	public void act(long now) 
	{  
		transition.play();
	}

}
