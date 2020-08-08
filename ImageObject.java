public class ImageObject extends Actor {

	public ImageObject(String imageFileName) {
		String imageFile = getClass().getClassLoader().getResource(imageFileName).toString();
		super.setImage(imageFile);
	}
	
	@Override
	public void act(long now) {
		
	}

}