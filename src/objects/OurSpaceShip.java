package objects;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.lwjgl.input.Keyboard;

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
	
	public int getHealth(){
		return hp;
	}
	
	public void ticks(){
		p.setLocation(p.getX() + Vx, p.getY() + Vy);
	}
	
	public void onKeyDown(int keycode) {
		switch(keycode){
		case 19:
			Vy ++;
			break;
		case 20:
			Vy --;
			break;
		case 21:
			Vx --;
			break;
		case 22:
			Vx ++;
			break;
		
		}
	}
	
	public void onKeyUp(int keycode) {
		if(keycode == 19 || keycode == 20)
			Vy=0;
		if(keycode == 21 || keycode == 22)
			Vx=0;
	}
}
