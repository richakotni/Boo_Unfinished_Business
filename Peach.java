import java.util.HashMap;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import java.util.Random;

public class Peach extends Actor
{
	private HashMap<String,String> imageStrings; 
	private PathTransition transition; 
	private Line peachPath = new Line();  
	
	private double pathLen;
	private double peachSpeed = 600.0/6.0 ;
	private double peachTime = 1;
	
	private double peachStep = 80;
	
	double booPosX ; 
	double booPosY;
	boolean booPresent = false;
	double [] pos;  
	 
	public Peach(double startX, double startY) 
	{
		imageStrings = new HashMap<String,String>();
		imageStrings.put("NORMAL", getClass().getClassLoader().getResource("resources/peach_normal.png").toString());
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
		
		peachPath.setStartX(startX); 
		peachPath.setStartY(startY); 
		double endX = 300;
		double endY = 300;
		pathLen = Math.sqrt(
				Math.pow((endY-startY), 2) + 
				Math.pow((endX-startX), 2) 
				); 
		peachTime = pathLen / peachSpeed;
		peachPath.setEndX(endX);
		peachPath.setEndY(endY);
		
		transition.setPath(peachPath);
		setSpeed(peachTime);
	}
	
	public double [] randomPosGenerator()
	{
		pos = new double[2]; 
		Random rand = new Random(); 
		double theta = 2 * Math.PI * rand.nextDouble();
		pos[0] = Math.cos(theta);
		pos[1] = Math.sin(theta);
		return pos;
	}
	
	public void updateImage(String key) {
		super.setImage(imageStrings.get(key));
	} 
	
	public void resetDirection()
	{
		double oldX = peachPath.getEndX();
		double oldY = peachPath.getEndY();
		peachPath.setStartX(oldX);  
		peachPath.setStartY(oldY);
		randomPosGenerator();
		
		double endX = oldX + peachStep * pos[0];
		if (endX > 500 || endX <0 ) {
			endX = oldX + peachStep * pos[0];
		}
		double endY = oldY + peachStep * pos[1];
		if (endY > 500 || endY <0 ) {
			endY = oldY - peachStep * pos[1];
		}
		peachPath.setEndX(endX);
		peachPath.setEndY(endY);
		pathLen = Math.sqrt(
							Math.pow((endY-oldY), 2) + 
							Math.pow((endX-oldX), 2) 
							); 
		peachTime = pathLen / peachSpeed;
		peachPath.setEndX(endX);
		peachPath.setEndY(endY);
		setSpeed(peachTime);
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
