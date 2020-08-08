import java.util.HashMap;

public class Life extends Actor {

	private HashMap<Integer, String> imageStrings;
	private int numLives;
	
	public Life(int initial) {
		numLives = initial;
		imageStrings = new HashMap<Integer, String>();
		imageStrings.put(new Integer(0), getClass().getClassLoader().getResource("resources/heart_zero.png").toString());
		imageStrings.put(new Integer(1), getClass().getClassLoader().getResource("resources/heart_one.png").toString());
		imageStrings.put(new Integer(2), getClass().getClassLoader().getResource("resources/heart_two.png").toString());
		imageStrings.put(new Integer(3), getClass().getClassLoader().getResource("resources/heart_three.png").toString());
		super.setImage(imageStrings.get(numLives));
	}
	
	public void increment() {
		numLives++;
		super.setImage(imageStrings.get(numLives));
	}
	
	public void decrement() {
		if(numLives > 0) {
		  numLives--;
		  super.setImage(imageStrings.get(numLives));
	    }
		if(this.getWorld().getCoinCount().getNumCoins()>=5) {
			this.increment();
			this.getWorld().getCoinCount().decrementCoins(5);
		}
    }
	
	public int getNumLives() {
		return numLives;
	}
	
	public void setNumLives(int l) {
		numLives = l;
		super.setImage(imageStrings.get(numLives));
	}
	
	@Override
	public void act(long now) {
		if(numLives<=0) {
			getWorld().finishGame();
		}
	}
	
}
