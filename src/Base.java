import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Clip;

public class Base extends Ship{
	private Image baseAlive;
	private Image baseHit;
	private Clip shootAudio;
    private List<Missile> ms;

	Base(int newx, int newy) {
		super(newx, newy);
		setWidth(10);
		setHeight(15);
		baseAlive = getImage("img_base.gif");
		baseHit = getImage("img_basehit.gif");
		shootAudio = getSound("aud_basefire.wav");
		ms = new ArrayList<Missile>();
		setWidth(10);
		setHeight(20);

	}
	public List<Missile> getMissiles() {
        return ms;
    }
	
	public void shootAudio() {
		shootAudio.setFramePosition(0);
		shootAudio.start();
	}
	
	@Override
	public void draw(Graphics2D g2) {
		if(getHit() == true) {
			g2.drawImage(baseHit, getX(), getY(), null);
			return;
		}
		g2.drawImage(baseAlive, getX(), getY(), null);
	}

}
