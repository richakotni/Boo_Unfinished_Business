import java.util.HashMap;
import javafx.scene.input.KeyCode;

public class Boo extends Actor {

	private HashMap<String,String> imageStrings;
	private boolean deadDelay;
	private long old;
	
	public Boo() {
		old = 0;
		deadDelay = false;
		imageStrings = new HashMap<String,String>();
		imageStrings.put("DOWN", getClass().getClassLoader().getResource("resources/boo_down.png").toString());
		imageStrings.put("LEFT", getClass().getClassLoader().getResource("resources/boo_left.png").toString());
		imageStrings.put("UP", getClass().getClassLoader().getResource("resources/boo_up.png").toString());
		imageStrings.put("RIGHT", getClass().getClassLoader().getResource("resources/boo_right.png").toString());
		imageStrings.put("DEAD", getClass().getClassLoader().getResource("resources/boo_dead.png").toString());
		super.setImage(imageStrings.get("DOWN"));
	}
	
	private void updateImage(String key) {
		super.setImage(imageStrings.get(key));
	}
	
	private boolean isOutOfBounds() {
		if(this.getX()<=15 || this.getX() + this.getWidth() >= this.getWorld().getPrefWidth()-5 ||
				this.getY()<=15 || this.getY() + this.getHeight() >= this.getWorld().getPrefHeight()-5) {
			return true;
		}
		return false;
	}
	
	@Override
	public void act(long now) {
		long delay = (long) 5e9;
		
		long currTime = now;
		if(((currTime - old)>=delay) && deadDelay) {
			this.updateImage("DOWN");
			deadDelay = false;
			old = currTime;
		}
		if(this.getOneIntersectingObject(Luma.class)!=null && !deadDelay) {
			deadDelay = true;
			this.getWorld().getLife().decrement();
			this.updateImage("DEAD");
			old = currTime;
		}
		if(this.getOneIntersectingObject(Mario.class)!=null && !deadDelay) {
			deadDelay = true;
			this.getWorld().getLife().decrement();
			this.updateImage("DEAD");
			old = currTime;
		}
		if(this.getOneIntersectingObject(Luigi.class)!=null && !deadDelay) {
			deadDelay = true;
			this.getWorld().getLife().decrement();
			this.updateImage("DEAD");
			old = currTime;
		}
		if(this.getOneIntersectingObject(Peach.class)!=null && !deadDelay) {
			deadDelay = true;
			this.getWorld().getLife().decrement();
			this.updateImage("DEAD");
			old = currTime;
		}
		if(this.getWorld().isActive(KeyCode.LEFT)) {
			if(!deadDelay) {	
				this.updateImage("LEFT");
			}
			this.setX(this.getX()-2);
			if(this.getOneIntersectingObject(ImageObject.class)!=null || this.isOutOfBounds()) {
				this.setX(this.getX()+2);
			}
		}
		if(this.getWorld().isActive(KeyCode.RIGHT)) {
			if(!deadDelay) {
				this.updateImage("RIGHT");
			}
			this.setX(this.getX()+2);
			if(this.getOneIntersectingObject(ImageObject.class)!=null || this.isOutOfBounds()) {
				this.setX(this.getX()-2);
			}
		}
		if(this.getWorld().isActive(KeyCode.DOWN)) {
			if(!deadDelay) {
				this.updateImage("DOWN");
			}
			this.setY(this.getY()+2);
			if(this.getOneIntersectingObject(ImageObject.class)!=null || this.isOutOfBounds()) {
				this.setY(this.getY()-2);
			}
		}
		if(this.getWorld().isActive(KeyCode.UP)) {
			if(!deadDelay) {
				this.updateImage("UP");
			}
			this.setY(this.getY()-2);
			if(this.getOneIntersectingObject(ImageObject.class)!=null || this.isOutOfBounds()) {
				this.setY(this.getY()+2);
			}
		}
	}

}
