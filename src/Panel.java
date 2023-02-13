import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class Panel extends JPanel implements ActionListener, KeyListener {
	private int score = 0;
	private Random rand = new Random();
	// booleans controlling whether the base should move
	private boolean left;
	private boolean right;
	// boolean controlling whether invader should move
	private boolean invaderMove;
	// boolean controlling whether enemy missile should move
	private boolean misMove;
	private boolean alternateImage;
	// determining state of whether a ship object is hit
	private boolean hit = false;

	private Timer timer;
	// private Invader top[];
	private Base base;

	// array list containing rows of invaders.
	// the rows are verticle
	private ArrayList<ArrayList<Invader>> horde;
	private Mystery mystery;

	// current direction of the msytery ship
	private String mysDirection;

	private int missilecount;

	// boolean describing whether the base has shot a missile
	private boolean missileshot;

	// Arraylist of enemy missiles
	private ArrayList<Missile> enemyMissile; // list of enemy missiles
	private Missile baseMissile;

	// direction of invaders
	private String invDirection;

	// boolean describing whether the direction of invaders has recently changed
	private boolean dirChange;
	// rate (pace per pulse) of invaders
	private int invRate;
	private int invRCount;

	// rate of enemy missiles (and mystery ship, if it exists)
	private int misRate;
	private int misRCount;

	boolean gameEnd;
	boolean newGame; // true if all the invaders are killed

	@SuppressWarnings("serial")
	Panel() {
		// TODO draw score on screen
		// TODO make it so that when all enemies are killed, game is restarted (new
		// wave)
		// initializing base here so that if a new wave is created, the base can remain
		// in the same spot
		base = new Base(250, 350);
		addKeyListener(this);

		initializeValues();

		setBackground(Color.BLACK);
		setFocusable(true);

		timer = new Timer(20, e -> {
			actionPerformed(e);
			invRCount += 1;
			misRCount += 1;

			if ((left) && (base.getX() != 0)) {
				base.moveLeft(5);
			}
			if ((right) && (base.getX() != 460)) {
				base.moveRight(5);
			}

			// if a missile was shot, create a missile
			// TODO move invaders
			if (baseMissile != null) {
				baseMissile.moveUp(5);
			}
			// if enough pulses have passed for enemy missiles to move,
			if (misMove) {
				for (Missile m : enemyMissile) {
					m.moveDown(5);
				}
			}
			if (missileshot) {
				baseMissile.fire();
				missileshot = false;
			}

			// if invader should move, it moves 5 pixels in the mandated direction
			// if a direction change has just occurred, it moves down 12 pixels
			if (invaderMove) {

				for (int i = 0; i < horde.size(); i++) {
					ArrayList<Invader> curr = horde.get(i);
					for (Invader enemy : curr) {
						enemy.setAlt(alternateImage);
						if (dirChange) {
							enemy.moveDown(12);
							// enemy.setAlt(dirChange);

						}
						if (invDirection.equals("right")) {
							enemy.moveRight(5);
						}

						else {
							enemy.moveLeft(5);
						}

					}
				}
				invaderMove = false;
				dirChange = false;

			}

			// if there is a mystery ship, move it in the mandated direction every 2 paces
			// play music every pace
			if (mystery != null) {
				// TODO check if this is how to play audio
				mystery.mysteryAudio();
				if (misRate == misRCount) {
					if (mysDirection.equals("right")) {
						mystery.moveRight(5);
					} else {
						mystery.moveLeft(5);
					}
				}
			}
			// new game starts if all enemies have been killed and there are no
			// missiles/mystery ships left on screen
			if ((newGame) && (enemyMissile.size() == 0) && (baseMissile == null) && (mystery == null)) {
				initializeValues();
			}

			repaint();
		});
		resumeTimer();

		// initial painting

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_SPACE:
			if (baseMissile == null) {
				missileshot = true;
				baseMissile = new Missile(base.getX() + 12, base.getY() - 5);
//				baseMissile.fire();
			}

			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_SPACE:
			break;
		}
	}

	// create an action listener and add it
	@Override
	public void actionPerformed(ActionEvent e) {

		// check if any missiles are out of bounds
		checkBounds();
		checkCollisions();
		
		// if the horde is destroyed, a new game (keeping the old score) is started
		if (horde.size() == 0) {
			newGame = true;
		}

		if (enemyMissile.size() < 3 && horde.size() != 0) {
			// 80% chance of missile firing if there are less than 3 on screen
			if (rand.nextInt(10) <= 7) {
				// picks a random row, the last invader in the row shoots
				int rowNum = 0;
				if (horde.size() > 1) {
					rowNum = rand.nextInt(horde.size() - 1); 
					}
				ArrayList<Invader> enemyRow = horde.get(rowNum);
				Invader i = enemyRow.get(0);
				Missile m = new Missile(i.getX() + 15, i.getY() + 5);
				enemyMissile.add(m);
				m.fire();

			}
		}

		// determine if any invaders have been hit
		// also check to see if enemies have reached the base
		for (int i = 0; i < horde.size(); i++) {
			ArrayList<Invader> curr = horde.get(i);
			Invader lastEnemy = curr.get(0);
			if (lastEnemy.getY() >= base.getY() - 15) {
				gameEnd = true;
			}
			// if there are no invaders left in the row, remove the row from the horde
			
			// if an invader in the row has been hit, remove the invader from the row
			// also increase score!!
			for (int j = 0; j < curr.size(); j++) {
				Invader enemy = curr.get(j);
				if (enemy.getExploded()) {
					score += enemy.invaderValue();
					curr.remove(enemy);
				}
			}
			if (curr.size() == 0) {
				horde.remove(curr);
			}
		}



		// if enough pulses have passed, the invader can move
		// also alternate image
		if (invRCount == invRate) {
			if (alternateImage) {
				alternateImage = false;
			} else {
				alternateImage = true;
			}
			invaderMove = true;
			invRCount = 0;
		}

		// if 2 paces have passed, the enemy missile can move
		if (misRCount == misRate) {
			misMove = true;
			misRCount = 0;
		}

		// check if invader rows have hit sides of the screen; this will change
		// direction
		// and change the rate;
		if (horde.size() != 0) {
			ArrayList<Invader> leftmost = horde.get(0);
			ArrayList<Invader> rightmost = horde.get(horde.size() - 1);
			// hit the left of the screen, so the invaders switch to right
			if (leftmost.get(0).getX() <= 0) {
				invDirection = "right";
				invaderMove = true;
				dirChange = true;
				// rate decreases by 20%
				if (invRate > 2) {
					invRate *= 0.8;
				}
			}
			// hit the right of the screen, so the invaders switch to left
			else if (rightmost.get(0).getX() + 50 >= 500) {
				invDirection = "left";
				invaderMove = true;
				dirChange = true;
				if (invRate > 2) {
					invRate *= 0.8;
				}
			}
		}

		if (mystery == null) {
			if (rand.nextFloat() > 0.999) {
				if (rand.nextBoolean()) {
					mystery = new Mystery(0, 50);
					mysDirection = "right";
				} else {
					mystery = new Mystery(500, 50);
					mysDirection = "left";
				}
			}

		} else {
			if (mystery.getHit()) {
				score += mystery.invaderValue();
				mystery = null;

			}
		}
		// game ends if missile hits the base
		if (base.getHit()) {
			gameEnd = true;
		}

	}

	public void checkCollisions() {
		//TODO check mystery ship collision
		// check if alien missiles collide with base
		Rectangle r3 = base.getBounds();
		for (Missile miss : enemyMissile) {
			Rectangle r2 = miss.getBounds();
			if (r3.intersects(r2)) {
				base.setHit(true);
			}
		}
		// check if baseMissile collides with aliens
		if (baseMissile != null) {
			Rectangle r1 = baseMissile.getBounds();
			for (ArrayList<Invader> aliens1 : horde) {
				for (Invader alien : aliens1) {
					Rectangle r2 = alien.getBounds();
					if (r1.intersects(r2)) {
						baseMissile = null;
						alien.setHit(true);
					}
				}
			}
			if (mystery != null) {
			Rectangle r4 = mystery.getBounds();
			int rowNum = 0;
			if (r1.intersects(r4)) {
				baseMissile = null;
				mystery.setHit(true); }
		}
	}
	}

	public void stopTimer() {
		timer.stop();
	}

	public void resumeTimer() {
		timer.start();
	}

	public void createEnemyHorde() {
		// create in vertcle rows
		horde = new ArrayList<>();

		int x = 75;
		int y = 80;
		for (int i = 0; i != 10; i++) {
			ArrayList<Invader> temp = new ArrayList<>();
			temp.add(new InvaderBottom(x, y + 100));
			temp.add(new InvaderBottom(x, y + 75));
			temp.add(new InvaderMiddle(x, y + 50));
			temp.add(new InvaderMiddle(x, y + 25));
			temp.add(new InvaderTop(x, y));
			x += 35;
			horde.add(temp);

		}

	}

	/*
	 * Check if missile/mystery ship has gone outside the bounds of the program- if
	 * so, remove the missile/mystery ship
	 */
	public void checkBounds() {
		if (baseMissile != null) {
			if (baseMissile.getY() <= 0) {
				missileshot = false;
				baseMissile = null;
			}
		}

		if (enemyMissile.size() != 0) {
			for (int i = 0; i < enemyMissile.size(); i++) {
				Missile m = enemyMissile.get(i);
				if (m.getY() >= 450) {
					enemyMissile.remove(m);
				} else {
				}
			}
		}
		if (mystery != null) {
			if ((mystery.getX() >= 500 || mystery.getX() <= 0)) {
				mystery = null;
			}
		}

	}

	public void initializeValues() {
		enemyMissile = new ArrayList<>();
		newGame = false;
		left = false;
		right = false;
		invaderMove = false;
		missileshot = false;
		missilecount = 0;
		invRate = 40;
		invRCount = 0;
		invDirection = "right";
		misRCount = 0;
		misRate = 2;
		createEnemyHorde();
		left = false;
		right = false;
		invaderMove = false;
		misMove = false;
		mystery = null;
		mysDirection = "right";
		missilecount = 0;
		missileshot = false;
		baseMissile = null;
		dirChange = false;
		misRate = 2;
		gameEnd = false;
		alternateImage = false;

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		for (int i = 0; i < horde.size(); i++) {
			ArrayList<Invader> row = horde.get(i);
			for (Invader enemy : row) {
				enemy.draw(g2);
			}
		}
		if (baseMissile != null) {
			baseMissile.draw(g2);
		}
		if (enemyMissile.size() != 0 || enemyMissile != null) {
			for (Missile m : enemyMissile) {
				m.draw(g2);
			}
		}
		if (mystery != null) {
			mystery.draw(g2);
		}

		base.draw(g2);

		if (gameEnd) {
			String text = "Game Over";
			g2.setColor(Color.GREEN);
			g2.setFont(new Font("Arial", Font.BOLD, 40));
			FontMetrics fm = g2.getFontMetrics();
			int width = fm.stringWidth(text);
			int descent = fm.getDescent();
			double x = getWidth() / 2f - width / 2f;
			double y = getHeight() / 2f + descent;
			g2.drawString(text, (int) x, (int) y);
			stopTimer();
		}

		String text = "score: " + score;
		g2.setColor(Color.GREEN);
		g2.setFont(new Font("Arial", Font.BOLD, 15));
		FontMetrics fm = g2.getFontMetrics();
		int x = 480 - (fm.stringWidth(text) + 10);
		g2.drawString(text, x, 20);

	}
}
