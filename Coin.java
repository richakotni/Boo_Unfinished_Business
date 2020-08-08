import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class Coin extends Actor {

	List<String> strings;
	long secs = 0;
	private int index;
	
	public Coin() {
		strings = new ArrayList<String>();
		index = 1;
		strings.add(getClass().getClassLoader().getResource("resources/coin_1.png").toString());
		strings.add(getClass().getClassLoader().getResource("resources/coin_2.png").toString());
		strings.add(getClass().getClassLoader().getResource("resources/coin_3.png").toString());
		strings.add(getClass().getClassLoader().getResource("resources/coin_4.png").toString());
		strings.add(getClass().getClassLoader().getResource("resources/coin_5.png").toString());
		strings.add(getClass().getClassLoader().getResource("resources/coin_6.png").toString());
		super.setImage(new Image(strings.get(0)));
	}
	
	@Override
	public void act(long now) {
		long delay = (long) 1e8;
		if((now - secs) >= delay) {
			super.setImage(strings.get(index));
			secs = (long) (now);			
			if(index!=5) {
				index++;
			}
			else {
				index = 0;	
			}
		}
		Boo b = this.getOneIntersectingObject(Boo.class);
		if(b!=null) {
			this.getWorld().remove(this);
			b.getWorld().getCoinCount().incrementCoins(1);
			String fileName = getClass().getClassLoader().getResource("resources/coin_sound.mp3").toString();
			AudioClip clip = new AudioClip(fileName);
			clip.play();
		}
	}

}
