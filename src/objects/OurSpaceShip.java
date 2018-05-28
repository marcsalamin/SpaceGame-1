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
		case KeyEvent.VK_UP:
			System.out.println("Vy UP");
			Vy +=5;
		case KeyEvent.VK_DOWN:
			System.out.println("Vy DOWN");
			Vy -=5;
		case KeyEvent.VK_LEFT:
			System.out.println("Vx DOWN");
			Vx -=5;
		case KeyEvent.VK_RIGHT:
			System.out.println("Vx UP");
			Vx +=5;
		
		}
	}
	
	public void onKeyUp(int keycode) {
		if(keycode == Keyboard.KEY_UP || keycode == Keyboard.KEY_DOWN)
			Vy=0;
		if(keycode == Keyboard.KEY_LEFT || keycode == Keyboard.KEY_RIGHT)
			Vx=0;
	}
}
