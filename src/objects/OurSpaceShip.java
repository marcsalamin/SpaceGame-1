package objects;

import java.awt.Point;

import javafx.scene.shape.Circle;

public class OurSpaceShip {
	private static Point p;
	private static float Vx, Vy;
	private Circle c;
	private double hitBox;
	private static int hp;
	
	public OurSpaceShip(Point p, float Vx, float Vy, double hitBox, int hp){
		this.p = p;
		this.Vx = Vx;
		this.Vy = Vy;
		this.hitBox = hitBox;
		this.c= new Circle(p.getX(),p.getY(),hitBox);
		this.hp = 3;
		
	}	
	
	public static Point getPosition(){
		return p;
	}
	public static void helthDown(){
		hp--;
	}
	public double getHitBox(){
		return hitBox;
	}
	
	public void ticks(){
		
	}

}
