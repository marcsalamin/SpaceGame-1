package game;

import java.awt.Dimension;
import java.awt.Point;

import objects.EnemySpaceShip;
 

public class GameCode {
	Point p;

	
	
	public void generateEnemey(){
		p.setLocation(Math.random()*width, height);
		EnemySpaceShip es = new EnemySpaceShip( p, (float) 0,(float) height/20, width/40, 1);
	}


	static Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	public static double height = dimension.getHeight();
	public static double width  = dimension.getWidth();

}
