
public class DiningRoom extends World {

	private ExitDoor endGameDoor;
	private ExitDoor nextLevelDoor1;
	
	public DiningRoom() {
		endGameDoor = new ExitDoor();
		nextLevelDoor1 = new ExitDoor();
		
		this.add(endGameDoor);
		endGameDoor.setFitHeight(50);
		endGameDoor.setFitWidth(15);
		endGameDoor.setX(493);
		endGameDoor.setY(230);
		
		this.add(nextLevelDoor1);
		nextLevelDoor1.setFitHeight(50);
		nextLevelDoor1.setFitWidth(15);
		nextLevelDoor1.setX(5);
		nextLevelDoor1.setY(190);
		 
		String imageFile = getClass().getClassLoader().getResource("resources/dining_room.png").toString();
		super.setImage(imageFile);
		
		ImageObject diningtable = new ImageObject("resources/table.png");
		this.add(diningtable);
		diningtable.setFitHeight(100);
		diningtable.setFitWidth(100);
		diningtable.setX(350);
		diningtable.setY(100);
		
		ImageObject image = new ImageObject("resources/glass_plants1.png");
		this.add(image);
		image.setFitHeight(60);
		image.setFitWidth(60);
		image.setX(25);
		image.setY(400);
		
		ImageObject cabinet = new ImageObject("resources/table.png");
		this.add(cabinet);
		cabinet.setFitHeight(100);
		cabinet.setFitWidth(50);
		cabinet.setX(140);
		cabinet.setY(100);
		
		ImageObject table = new ImageObject("resources/table_chair.png");
		this.add(table);
		table.setFitHeight(100);
		table.setFitWidth(200);
		table.setX(200);
		table.setY(300);
		
    	Coin coin1 = new Coin();
    	this.add(coin1); 
    	coin1.setX(350);
		coin1.setY(270);
		coin1.setFitHeight(20);
		coin1.setFitWidth(20);
 
		Coin coin2 = new Coin();
    	this.add(coin2); 
    	coin2.setX(150);
		coin2.setY(40);
		coin2.setFitHeight(20);
		coin2.setFitWidth(20);
		
		Luma luma = new Luma();
		this.add(luma);
		luma.setFitHeight(50);
		luma.setFitWidth(50);
		luma.setMovement(Movement.CIRCLE);
		luma.setFitHeight(50);
		luma.setFitWidth(50);
		luma.setPosition(230, 275, 500);
		luma.setSpeed(6);
		
		Luma luma1 = new Luma();
		this.add(luma1);//
		luma1.setFitHeight(50);
		luma1.setFitWidth(50);
		luma1.setMovement(Movement.VERTICAL);
		luma1.setFitHeight(50);
		luma1.setFitWidth(50);
		luma1.setPosition(50, 260, 400);
		luma.setSpeed(6);

	}
	
	@Override
	public void act(long now) {
		
		Boo b = this.getOneObject(Boo.class);
		if(nextLevelDoor1.getOneIntersectingObject(Boo.class)!=null && !this.isLocked()) {
			remove(b);
			goToNextRoom("BEDROOM", b, 400, 400);
		}
		else if(endGameDoor.getOneIntersectingObject(Boo.class)!=null && this.isLocked()) {
			remove(b);
			goToNextRoom("FIGHT_ROOM2", b, 400, 400);
		}
	}
}