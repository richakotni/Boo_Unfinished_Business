
public class ExitDoor extends Actor {
	
	public ExitDoor() {
		String imageFile = getClass().getClassLoader().getResource("resources/door1.png").toString();
		super.setImage(imageFile);
	}

	@Override
	public void act(long now) {
		
	}
}
