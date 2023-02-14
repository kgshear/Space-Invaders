import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import javax.sound.sampled.Clip;

public class Mystery extends Invader{

	private Clip mysteryAudio;
	private Image mysteryImage;
	private int[] randVals = {50, 100, 150, 300};
	
	Mystery(int newx, int newy) {
		super(newx, newy);
		mysteryAudio = getSound("aud_mystery.wav");
		mysteryImage = getImage("img_mystery.gif");
		setWidth(40);
		setHeight(20);
	}

	@Override
	public int invaderValue() {
		int val = 0;
		Random r = new Random();
	    int nextRandomNumberIndex = r.nextInt(randVals.length);
	    val = randVals[nextRandomNumberIndex];
		return val;
	}
	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(mysteryImage, getX(), getY(), null);

	}
	
	public void mysteryAudio() {
		mysteryAudio.setFramePosition(0);
		mysteryAudio.start();
	}

	@Override
	public void setAlt(boolean alternating) {
		
	}


	
	
}
