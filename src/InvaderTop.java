
import java.awt.Graphics2D;
import java.awt.Image;

public class InvaderTop extends Invader{
	private Image explodeImage;
	private Image imageA;
	private Image imageB;
	private boolean alt = false;
	
	InvaderTop(int newx, int newy) {
		super(newx, newy);
		imageA = getImage("img_invadertopA.gif");
		imageB = getImage("img_invadertopB.gif");
		explodeImage = getImage("img_invaderhit.gif");

	}

	@Override
	public int invaderValue() {
		return 30;
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
			return;
		}
		g2.drawImage(imageB, getX(), getY(),  null);
	}
	@Override
	public void setAlt(boolean alternating) {
		alt = alternating;
	}
}
