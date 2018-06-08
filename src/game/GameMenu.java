package game;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Philippine Favre et Marc Salamin
 * Class to manage the menu at the beginning
 *
 */
public class GameMenu extends JFrame{
	public static boolean gameMenu = true;
	public class ClickListener implements ActionListener{
		public ClickListener(){
			gameMenu = false;
			
		}
	}
	public void actionPerformed(ActionEvent e){
		
	}
	GameMenu(){
		setTitle("Space Shooter");
		setSize((int)GameCode.width/2,(int)GameCode.height/2);
		setLocation(new Point((int)GameCode.width/2,(int)GameCode.height/2));
		
		JButton play = new JButton("Play");
		JButton score = new JButton("High Score");
		
		play.addActionListener(ClickListener());
	}

}
