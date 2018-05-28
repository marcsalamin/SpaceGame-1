package objects;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javafx.scene.shape.Circle;

public class OurSpaceShip {
	private static Point p;
	private static float Vx, Vy;
	private Circle c;
	private float hitBox;
	private static int hp;
	
	public OurSpaceShip(Point p, float Vx, float Vy, float hitBox, int hp){
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
	public float getHitBox(){
		return hitBox;
	}
	
	public void ticks(){
		
	}

	public void onKeyDown(int keycode) {
		
	}
	
	public void onKeyUp(int keycode) {
	}
}
