import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView {
	
	private Image image;
	
	public Actor() {
		image = null;
	}
	
	public void setImage(String imageFile) {
		image = new Image(imageFile);
		this.setImage(image);
	}
	
	public void move(double dx, double dy) {
		this.setX(this.getX() + dx);
		this.setY(this.getY() + dy);
	}
	
	public World getWorld() {
		return (World) (this.getParent());
	}
	
	public double getWidth() {
		return this.getBoundsInParent().getWidth();
	}
	
	public double getHeight() {
		return this.getBoundsInParent().getHeight();
	}
	
	public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls) {
		List<A> list = new ArrayList<A>();
		for(int i = 0;i<this.getWorld().getObjects(cls).size();i++) {
			if(!this.getWorld().getObjects(cls).get(i).equals(this)) {
				if(cls.isInstance(this.getWorld().getObjects(cls).get(i))) {
					if(this.intersects(this.getWorld().getObjects(cls).get(i).getBoundsInParent())) {
						list.add(this.getWorld().getObjects(cls).get(i));
					}
				}
			}
		}
		return list;
	}
	
	public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
		List<A> list = this.getIntersectingObjects(cls);
		if(list.size()!=0)	
			return list.get(0);
		else
			return null;
	}
	
	public abstract void act(long now);
	
}