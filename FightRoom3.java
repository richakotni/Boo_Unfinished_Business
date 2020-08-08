public class FightRoom3 extends World {
	 
	public FightRoom3() { 
		String imageFile = getClass().getClassLoader().getResource("resources/blackrug.png").toString();
		super.setImage(imageFile);
		  
    	Coin coin1 = new Coin();
    	this.add(coin1); 
    	coin1.setX(350);
		coin1.setY(270);
		coin1.setFitHeight(20);
		coin1.setFitWidth(20);
 
		Coin coin2 = new Coin();
    	this.add(coin2); 
    	coin2.setX(200);
		coin2.setY(40);
		coin2.setFitHeight(20);
		coin2.setFitWidth(20);
		
		Coin coin3 = new Coin();
    	this.add(coin3); 
    	coin3.setX(150);
    	coin3.setY(270);
    	coin3.setFitHeight(20);
    	coin3.setFitWidth(20);
		
		Coin coin4 = new Coin();
    	this.add(coin4); 
    	coin4.setX(350);
    	coin4.setY(130);
    	coin4.setFitHeight(20);
    	coin4.setFitWidth(20);
    	
    	Coin coin5 = new Coin();
    	this.add(coin5); 
    	coin5.setX(10);
    	coin5.setY(100);
    	coin5.setFitHeight(20);
    	coin5.setFitWidth(20);
		
		Mario mario = new Mario(40,40);
 		this.add(mario);
		mario.setFitHeight(60);
		mario.setFitWidth(60); 
	}
	
	public double[] findBoo() {
		Boo b = this.getOneObject(Boo.class);
		if (b != null) {
			double[] booPos = {b.getX(), b.getY()};
			return booPos;
		}
		else {
			return null;
		}
	}
	
	
	@Override
	public void act(long now) {
		double[] booPos = this.findBoo();
		if(booPos != null) {
			Mario m = this.getOneObject(Mario.class);
			if(m != null) {
				m.setBooPos(booPos[0], booPos[1]);
			}
		}
		Boo b = this.getOneObject(Boo.class);
		if(this.getOneObject(Coin.class)==null) {
			remove(b);
			winGame("WIN_GAME");
		}	
	}
}
