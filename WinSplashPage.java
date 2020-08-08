
//import javafx.scene.media.AudioClip;

public class WinSplashPage extends World {
		
		public WinSplashPage() {
			
			String imageFile = getClass().getClassLoader().getResource("resources/BooOnBeach1.jpg").toString();
			this.remove(getLife());
			this.remove(getCoinCount());
			this.remove(getCoinImage()); 
			//String fileName  = null; 
			//fileName = getClass().getClassLoader().getResource("resources/game_win.mp3").toString();
			//AudioClip clip = new AudioClip(fileName); 
			//clip.play();		
			super.setImage(imageFile);
			ImageObject img = new ImageObject("resources/you_win.png");
			img.setX(50);
			img.setY(20);
			this.add(img);
		}
		
		@Override
		public void act(long now) {
		}
}

