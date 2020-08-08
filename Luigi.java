import java.util.HashMap;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import java.util.Random;

public class Luigi extends Actor
{
	private HashMap<String,String> imageStrings; 
	private PathTransition transition; 
	private Line luigiPath = new Line();  
	
	private double pathLen;
	private double luigiSpeed = 600.0/6.0 ;
	private double luigiTime = 1;
	double booPosX ; 
	double booPosY;
	boolean booPresent = false;
	double [] pos;  
	 
	public Luigi(double startX, double startY) 
	{
		imageStrings = new HashMap<String,String>();
		imageStrings.put("NORMAL", getClass().getClassLoader().getResource("resources/luigi_normal.png").toString());
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
		double endX = 300;
		double endY = 300;
		
		luigiPath.setStartX(startX); 
		luigiPath.setStartY(startY); 
		luigiPath.setEndX(endX);
		luigiPath.setEndY(endY);
		pathLen = Math.sqrt(
				Math.pow((endY-startY), 2) + 
				Math.pow((endX-startX), 2) 
		); 
		luigiTime = pathLen / luigiSpeed;
		luigiPath.setEndX(endX);
		luigiPath.setEndY(endY);
		
		transition.setPath(luigiPath);
		setSpeed(luigiTime);	
	}
	
	public double [] randomPosGenerator()
	{
		pos = new double[2]; 
		double XPos; 
		double YPos; 
		double rangeMin = 0 ; 
		double rangeMax = 500.0; 
		Random rand = new Random(); 
		XPos = rangeMin + (rangeMax - rangeMin) * rand.nextDouble(); 
		YPos = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
		pos[0]= XPos; 
		pos[1]= YPos; 	
		return pos;
		
	}
	
	public void updateImage(String key) {
		super.setImage(imageStrings.get(key));
	} 
	
	public void resetDirection()
	{
		double oldX = luigiPath.getEndX();
		double oldY = luigiPath.getEndY();
		luigiPath.setStartX(oldX);  
		luigiPath.setStartY(oldY);
		randomPosGenerator();
		double endX = pos[0];
		double endY = pos[1];
		luigiPath.setEndX(endX);
		luigiPath.setEndY(endY);
		pathLen = Math.sqrt(
							Math.pow((endY-oldY), 2) + 
							Math.pow((endX-oldX), 2) 
							); 
		luigiTime = pathLen / luigiSpeed;
		luigiPath.setEndX(endX);
		luigiPath.setEndY(endY);
		setSpeed(luigiTime); 
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
