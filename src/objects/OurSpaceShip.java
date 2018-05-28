package objects;

import java.awt.Point;

public class OurSpaceShip {
	private static Point p;
	private static int hp;
	
	public OurSpaceShip(Point p){
		this.p = p;
		this.hp = 3;
		
	}
	
	public static Point getPosition(){
		return p;
	}
	public static void helthDown(){
		hp--;
	}

}
