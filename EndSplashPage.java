
public class EndSplashPage extends World {
	
	public EndSplashPage() {
	
		this.remove(getLife());
		this.remove(getCoinCount());
		this.remove(getCoinImage()); 
		String imageFile = getClass().getClassLoader().getResource("resources/game_over.jpg").toString();
		super.setImage(imageFile);
		ImageObject booimg = new ImageObject("resources/boo_right.png");
		this.add(booimg); 
		booimg.setFitHeight(100);
    	booimg.setFitWidth(100);        
    	booimg.setX(220);
    	booimg.setY(350);	
	}

	@Override
	public void act(long now) {
	}
}
