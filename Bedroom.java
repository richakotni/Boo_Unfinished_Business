public class Bedroom extends World {

	private ExitDoor endGameDoor;
	
	public Bedroom() {
		endGameDoor = new ExitDoor();
	String imageFile = "";
		imageFile =  getClass().getClassLoader().getResource("resources/bed_room.png").toString();
		super.setImage(imageFile);
		
		ImageObject desk = new ImageObject("resources/table_2.png");
		this.add(desk);
		desk.setFitHeight(75);
		desk.setFitWidth(80);
		desk.setX(180);
		desk.setY(400);
		
		ImageObject BookShlef = new ImageObject("resources/bookcase.png");
		this.add(BookShlef);
		BookShlef.setFitHeight(100);
		BookShlef.setFitWidth(100);
		BookShlef.setX(350);
		BookShlef.setY(100);
		
		ImageObject bed = new ImageObject("resources/bed.png");
		this.add(bed);
		bed.setFitHeight(75);
		bed.setFitWidth(150);
		bed.setX(350);
		bed.setY(250);
		
		ImageObject image = new ImageObject("resources/glass_plants2.png");
		this.add(image);
		image.setFitHeight(60);
		image.setFitWidth(60);
		image.setX(90);
		image.setY(200);
		
		Coin coin1 = new Coin();
		this.add(coin1); 
		coin1.setX(100);
		coin1.setY(300);
		coin1.setFitHeight(20);
		coin1.setFitWidth(20);
 
		Coin coin2 = new Coin();
    	this.add(coin2); 
    	coin2.setX(345);
		coin2.setY(40);
		coin2.setFitHeight(20);
		coin2.setFitWidth(20);
		
		this.add(endGameDoor);
		endGameDoor.setFitHeight(50);
		endGameDoor.setFitWidth(15);
		endGameDoor.setX(5);
		endGameDoor.setY(330);
		
		Luma luma = new Luma();
		this.add(luma);
		luma.setFitHeight(50);
		luma.setFitWidth(50);
		luma.setMovement(Movement.CIRCLE);
		luma.setPosition(250, 270, 400);
		luma.setSpeed(4);
		
		Luma luma1 = new Luma();
		this.add(luma1);
		luma1.setFitHeight(50);
		luma1.setFitWidth(50);
		luma1.setMovement(Movement.VERTICAL);
		luma1.setFitHeight(50);
		luma1.setFitWidth(50);
		luma1.setPosition(100, 260, 400);
		luma.setSpeed(4);
	}
	
	@Override
	public void act(long now) {
		Boo b = this.getOneObject(Boo.class);
		if(endGameDoor.getOneIntersectingObject(Boo.class)!=null) {
			remove(b);
			goToNextRoom("FIGHT_ROOM3", b, 400, 400);
		}
	}
}
