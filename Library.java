public class Library extends World {

	private ExitDoor fightRoomDoor;
	private ExitDoor nextLevelDoor;
	
	public Library() {
		fightRoomDoor = new ExitDoor();
		nextLevelDoor = new ExitDoor();
	 
		String imageFile = getClass().getClassLoader().getResource("resources/library.png").toString();
		super.setImage(imageFile);
		
		ImageObject desk = new ImageObject("resources/table.png");
		this.add(desk);
		desk.setFitHeight(100);
		desk.setFitWidth(50);
		desk.setX(100);
		desk.setY(410);
		
		ImageObject plant1 = new ImageObject("resources/glass_plants.png");
		this.add(plant1);
		plant1.setFitHeight(80);
		plant1.setFitWidth(80);
		plant1.setX(150);
		plant1.setY(150);
		
		ImageObject bookcase2 = new ImageObject("resources/bookshelft.png");
		this.add(bookcase2);
		bookcase2.setFitHeight(150);
		bookcase2.setFitWidth(150);
		bookcase2.setX(250);
		bookcase2.setY(100);
		
		ImageObject bookcase3 = new ImageObject("resources/bookshelf.png");
		this.add(bookcase3);
		bookcase3.setFitHeight(100);
		bookcase3.setFitWidth(50);
		bookcase3.setX(430);
		bookcase3.setY(380);	
		
    	Coin coin1 = new Coin();
    	this.add(coin1); 
    	coin1.setX(350);
		coin1.setY(270);
		coin1.setFitHeight(20);
		coin1.setFitWidth(20);
 
		Coin coin2 = new Coin();
    	this.add(coin2); 
    	coin2.setX(150);
		coin2.setY(100);
		coin2.setFitHeight(20);
		coin2.setFitWidth(20);

		this.add(fightRoomDoor);
		fightRoomDoor.setFitHeight(60);
		fightRoomDoor.setFitWidth(15);
		fightRoomDoor.setX(5);
		fightRoomDoor.setY(210);
		
		this.add(nextLevelDoor);
		nextLevelDoor.setFitHeight(70);
		nextLevelDoor.setFitWidth(15);
		nextLevelDoor.setX(5);
		nextLevelDoor.setY(340);
		
		Luma luma = new Luma();
		this.add(luma);
		luma.setFitHeight(50);
		luma.setFitWidth(50);
		luma.setMovement(Movement.HORIZONTAL);
		luma.setFitHeight(50);
		luma.setFitWidth(50);
		luma.setPosition(250, 270, 400);
		luma.setSpeed(4);
		
		Luma luma1 = new Luma();
		this.add(luma1);
		luma1.setFitHeight(50);
		luma1.setFitWidth(50);
		luma1.setMovement(Movement.VERTICAL);
		luma1.setFitHeight(50);
		luma1.setFitWidth(50);
		luma1.setPosition(200, 260, 400);
		luma.setSpeed(4);
		
	}
	
	@Override
	public void act(long now) {
		Boo b = this.getOneObject(Boo.class);
		if(nextLevelDoor.getOneIntersectingObject(Boo.class)!=null && !this.isLocked()) {
			remove(b);
			goToNextRoom("DINING_ROOM", b, 430, 430);
		}
		if((fightRoomDoor.getOneIntersectingObject(Boo.class)!=null) && isLocked()) {
			remove(b);
			goToNextRoom("FIGHT_ROOM1", b, 400, 400);
		}
	}
}
 

