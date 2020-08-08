
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Luma extends Actor {

	private Movement movement;
	private PathTransition transition;
	private Circle c;
	private Line vLine;
	private Line hLine;
	
	public Luma() {
		String imageFile = getClass().getClassLoader().getResource("resources/luma.png").toString();
		super.setImage(imageFile);
		
		movement = Movement.CIRCLE;
		transition = new PathTransition();
		c = new Circle(100);
		vLine = new Line();
		hLine = new Line();
		
		transition.setNode(this);
		transition.setOrientation(OrientationType.NONE);
		transition.setPath(vLine);
		transition.setDuration(Duration.seconds(3));
	}
	
	public void setPosition(double x, double y, double lineLength) {
		c.setCenterX(x);
		c.setCenterY(y);
		
		hLine.setStartX(x - (lineLength/2));
		hLine.setEndX(x + (lineLength/2));
		hLine.setStartY(y);
		hLine.setEndY(y);
		
		vLine.setStartY(y - (lineLength/2));
		vLine.setEndY(y + (lineLength/2));
		vLine.setStartX(x);
		vLine.setEndX(x);
		
		this.setX(x);
		this.setY(y);
	}
	
	public void setSpeed(double seconds) {
		transition.setDuration(Duration.seconds(seconds));
	}
	
	public void setMovement(Movement d) {
		movement = d;
	}
	
	@Override
	public void act(long now) {
		
		if(movement.equals(Movement.CIRCLE)) {
			transition.setPath(c);
			transition.setCycleCount(1);
			transition.setAutoReverse(false);
			transition.play();
		}
		else if(movement.equals(Movement.HORIZONTAL)) {
			transition.setPath(hLine);
			transition.setAutoReverse(true);
			transition.setCycleCount(2);
			transition.play();
		}
		else if(movement.equals(Movement.VERTICAL)) {
			transition.setPath(vLine);
			transition.setAutoReverse(true);
			transition.setCycleCount(2);
			transition.play();
		}
	}
}


