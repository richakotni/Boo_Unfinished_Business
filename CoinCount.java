import java.util.HashMap;

public class CoinCount extends Actor {

	private HashMap<Integer,String> imageStrings; 
	private int numCoins;
	
	public CoinCount() {
		numCoins = 0;
		imageStrings = new HashMap<Integer,String>();
		imageStrings.put(new Integer(0), getClass().getClassLoader().getResource("resources/0.png").toString());
		imageStrings.put(new Integer(1), getClass().getClassLoader().getResource("resources/1.png").toString());
		imageStrings.put(new Integer(2), getClass().getClassLoader().getResource("resources/2.png").toString());
		imageStrings.put(new Integer(3), getClass().getClassLoader().getResource("resources/3.png").toString());
		imageStrings.put(new Integer(4), getClass().getClassLoader().getResource("resources/4.png").toString());
		imageStrings.put(new Integer(5), getClass().getClassLoader().getResource("resources/5.png").toString());
		imageStrings.put(new Integer(6), getClass().getClassLoader().getResource("resources/6.png").toString());
		imageStrings.put(new Integer(7), getClass().getClassLoader().getResource("resources/7.png").toString());
		imageStrings.put(new Integer(8), getClass().getClassLoader().getResource("resources/8.png").toString());
		imageStrings.put(new Integer(9), getClass().getClassLoader().getResource("resources/9.png").toString());
		super.setImage(imageStrings.get(numCoins));
	}
	
	public void incrementCoins(int num) {
		numCoins+=num;
		if(numCoins>=5 && this.getWorld().getLife().getNumLives()<3) {
			this.getWorld().getLife().increment();
			numCoins-=5;
		}
		super.setImage(imageStrings.get(numCoins));
	}
	
	public void decrementCoins(int num) {
		numCoins-=num;
		if(numCoins>=5 && this.getWorld().getLife().getNumLives()<3) {
			getWorld().getLife().increment();
			numCoins-=5;
		}
		super.setImage(imageStrings.get(numCoins));
	}
	
	public int getNumCoins() {
		return numCoins;
	}
	
	public void setNumCoins(int num) {
		numCoins = num;
		super.setImage(imageStrings.get(numCoins));
	}
	
	@Override
	public void act(long now) {
		
	}
	
}
