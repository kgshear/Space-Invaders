import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Clip;

public abstract class Ship extends Drawable{
	private Clip hitAudio;
	private boolean hit = false;

	Ship(int newx, int newy) {

		super(newx, newy);
		hitAudio = getSound("aud_hit.wav");
	}
	
	public boolean getHit() {
		return hit;
	}
	
	public void setHit(boolean hit) {
		this.hit = hit;
	}
		
	public void hitAudio() {
		hitAudio.setFramePosition(0);
		hitAudio.start();
	}
	
	//This class should include a method to find out whether a given missile has hit the ship, and another 
	//method changing the state of the ship to “hit” (at which point it should play the appropriate sound). 
	
}
