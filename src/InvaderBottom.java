import java.awt.Graphics2D;
import java.awt.Image;

public class InvaderBottom extends Invader{

	private Image imageA;
	private Image imageB;
	private Image explodeImage;
	private boolean alt = false;

	
	InvaderBottom(int newx, int newy) {
		super(newx, newy);
		imageA = getImage("img_invaderbottomA.gif");
		imageB = getImage("img_invaderbottomB.gif");
		explodeImage = getImage("img_invaderhit.gif");
	}
	
	@Override
	public int invaderValue() {
		return 10;
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
		g2.drawImage(imageB, getX(), getY(), null);
	}
	
	@Override
	public void setAlt(boolean alternating) {
		alt = alternating;
	}


}
