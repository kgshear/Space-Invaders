import java.awt.Graphics2D;
import java.util.List;

import javax.sound.sampled.Clip;

public class Missile extends Drawable{
    private List<Missile> ms;
	private Clip sound;
	
	public Missile(int newx, int newy) {
		super(newx, newy);
		sound = getSound("aud_basefire.wav");
		setWidth(2);
		setHeight(10);
		
	}
	@Override
	public void draw(Graphics2D g2) {
		g2.fillRect(getX(),getY(), 2, 10);
	}
	public void fire() {
		sound.setFramePosition(0);
		sound.start();
	}

}
