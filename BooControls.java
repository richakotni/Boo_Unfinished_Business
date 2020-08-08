import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class BooControls implements EventHandler<KeyEvent> {
	
	Boo boo;
	
	public BooControls(Boo b) {
		boo = b;
	}
	
	@Override
	public void handle(KeyEvent event) {
		if(event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
			boo.getWorld().addKeyCode(event.getCode());
		}
		else {
			boo.getWorld().removeKeyCode(event.getCode());
		}
	}
}
