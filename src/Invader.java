import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;


abstract class Invader extends Ship{
	private Image explodeImage;
    private List<Missile> ms;
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
		if(getHit() == true) {
			g2.drawImage(explodeImage, getX(), getY(), null);
			return;
		}
	}

	
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
