import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;


abstract class Invader extends Ship{
	private Image explodeImage;
    private List<Missile> ms;
//    private int width;
//    private int height;
    private boolean exploded;


	Invader(int newx, int newy) {
		super(newx, newy);
		setWidth(20);
		setHeight(20);
		explodeImage = getImage("img_invaderhit.gif");
		ms = new ArrayList<Missile>();
	}
	
	@Override
	public void draw(Graphics2D g2) {
//		Graphics2D g2 = (Graphics2D) g;
		if(getHit() == true) {
			g2.drawImage(explodeImage, getX(), getY(), null);
			return;
		}
	}
//	public void fire() {
//		ms.add(new Missile(getX() + 2, getY() + 10));
//	}
	
	public List<Missile> getMissiles() {
        return ms;
    }
	
	public abstract int invaderValue();
	
	public abstract void setAlt(boolean alternating);
	
	public boolean getExploded() {
		return exploded;
	}
	
	public void setExploded() {
		exploded = true;
	}


}
