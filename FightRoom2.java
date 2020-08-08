
public class FightRoom2 extends World {

	public FightRoom2() {
		
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
		
    	Luma luma = new Luma();
		this.add(luma);
		luma.setFitHeight(50);
		luma.setFitWidth(50);
		luma.setMovement(Movement.CIRCLE);
		luma.setFitHeight(50);
		luma.setFitWidth(50);
		luma.setPosition(250, 270, 400);
		luma.setSpeed(4);
    	
		Luigi luigi = new Luigi(40,40);
 		this.add(luigi);
 		luigi.setFitHeight(60);
 		luigi.setFitWidth(60);
		luigi.setX(40);
		luigi.setY(40);
		 
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
			Luigi l = this.getOneObject(Luigi.class);
			if(l != null) {
				l.setBooPos(booPos[0], booPos[1]);
			}
		}
		Boo b = this.getOneObject(Boo.class);
		if(this.getOneObject(Coin.class)==null) {
			remove(b);
			goToNextRoom("DINING_ROOM", b, 430, 430);
			this.getWorld("DINING_ROOM").setIsLocked(false);
		}	
 	}
 
}
