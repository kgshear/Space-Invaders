import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

public abstract class Drawable {
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean hit;
	
	protected Drawable(int x, int y) { 
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, getWidth(), getHeight());
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	protected Clip getSound(String filename) {
		Clip clip = null;
		try {
			InputStream      in     = getClass().getResourceAsStream( filename );
			InputStream      buf    = new BufferedInputStream( in );
			AudioInputStream stream = AudioSystem.getAudioInputStream( buf );
			clip = AudioSystem.getClip();
			clip.open( stream );
		} catch (UnsupportedAudioFileException | 
				IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		return clip;
	}
	protected Image getImage(String filename) {
		URL       url  = getClass().getResource( filename );
		ImageIcon icon = new ImageIcon( url );
		return    icon.getImage();
	}
	
	public abstract void draw(Graphics2D g2);
	
	public void moveLeft(int pxl) {
		setX(x-pxl);	
	}
	public void moveRight(int pxl) {
		setX(x+pxl);	
	}
	public void moveUp(int pxl) {
		setY(y-pxl);	
	}
	public void moveDown(int pxl) {
		setY(y+pxl);	
	}
}
