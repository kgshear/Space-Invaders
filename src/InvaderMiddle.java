import java.awt.Graphics2D;
import java.awt.Image;

public class InvaderMiddle extends Invader{
	private Image explodeImage;
	private Image imageA;
	private Image imageB;
	private boolean alt = false;
	
	InvaderMiddle(int newx, int newy) {
		super(newx, newy);
		imageA = getImage("img_invadermiddleA.gif");
		imageB = getImage("img_invadermiddleB.gif");
		explodeImage = getImage("img_invaderhit.gif");

	}

	@Override
	public int invaderValue() {
		return 20;
	}
	@Override
	public void draw(Graphics2D g2) {
		if(getHit() == true) {
			g2.drawImage(explodeImage, getX(), getY(), null);
			setExploded();
			return;
		}
		if(alt == false) {	
			g2.drawImage(imageA, getX(), getY(), null);
//			alt = true;
			return;
		}
		g2.drawImage(imageB, getX(), getY(), null);
//		alt = false;
	}
	
	@Override
	public void setAlt(boolean alternating) {
		alt = alternating;
	}
}
