import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class SpaceInvaders extends JFrame {
	private JMenuItem pause;
	private JMenuItem resume;

	public static void main(String[] args) {
		var f = new SpaceInvaders();
		f.setVisible(true);
	}

	public SpaceInvaders() {
		super("Space Invaders");
		setResizable(false);
		setSize(500, 450);
		setLocationRelativeTo(null);
		
		//getContentPane().setBackground(Color.BLACK);
		setDefaultCloseOperation( DO_NOTHING_ON_CLOSE );
		Panel p = new Panel();
		add(p);
		JMenuBar main = new JMenuBar();
		setJMenuBar(main);
		JMenu game = new JMenu("Game");
		JMenuItem newgame = game.add("New Game");
		JMenuItem quit = game.add("Quit");

		main.add(game);
		
		resume = game.add("Resume");
		
		pause = game.add("Pause");
		
		resume.setEnabled(false);
		pause.setEnabled(false);
		game.addSeparator();
		main.add(game);
		//new game
		//quit
		JMenu help = new JMenu("Help");
		JMenuItem about = help.add("About...");
		main.add(help);
		// about
		
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.stopTimer();
				int result = JOptionPane.showConfirmDialog(SpaceInvaders.this, "Dare to Quit?");
				if (result == JOptionPane.YES_OPTION) {
					dispose();	
			}
				}
		});
		
		addWindowListener( new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				pause.setEnabled(false);
				resume.setEnabled(true);
				p.stopTimer();
				int result = JOptionPane.showConfirmDialog(SpaceInvaders.this, "Dare to Quit?");
				if (result == JOptionPane.YES_OPTION) {
					dispose();	
			}
				else {
					
				}
				;
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			});
		
		pause.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//TODO stop timer
					pause.setEnabled(false);
					resume.setEnabled(true);
					p.stopTimer();
					
				}});
		
		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.resumeTimer();
				pause.setEnabled(true);
				resume.setEnabled(false);
				
			}});
		
		newgame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pause.setEnabled(false);
				resume.setEnabled(true);
		
				p.stopTimer();
				int result = JOptionPane.showConfirmDialog(SpaceInvaders.this, "Start a new game?");
				if (result == JOptionPane.YES_OPTION) {
					dispose();
					var f = new SpaceInvaders();
					f.setVisible(true);
					f.pause.setEnabled(true);
					f.setPause(true);
					
				}
				else {
					pause.setEnabled(false);
					resume.setEnabled(true);
				}
			}});
				
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.stopTimer();
				pause.setEnabled(false);
				resume.setEnabled(true);
				JOptionPane.showMessageDialog(
						SpaceInvaders.this, 
						"Space Invaders, by Thalya Thompson and Kelsey Shearon",
						"About",
						JOptionPane.INFORMATION_MESSAGE ); }}); }
				
	
					
				
	public void setPause(boolean enable) {
		pause.setEnabled(enable);
	}
		
	
}
