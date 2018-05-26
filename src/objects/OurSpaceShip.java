package objects;

import java.awt.Point;

public class OurSpaceShip {
	private static Point p;
	private static int hp;
	
	OurSpaceShip(){
		
	}
	
	public static Point getPosition(){
		return p;
	}
	public static void helthDown(){
		hp--;
	}

}
