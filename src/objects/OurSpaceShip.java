package objects;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.lwjgl.input.Keyboard;

import com.badlogic.gdx.Input;

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
		float speed = 5;
		switch(keycode){
		case Input.Keys.UP:
			Vy = speed;
			break;
		case Input.Keys.DOWN:
			Vy =- speed;
			break;
		case Input.Keys.LEFT:
			Vx =- speed;
			break;
		case Input.Keys.RIGHT:
			Vx = speed;
			break;
		
		}
	}
	
	public void onKeyUp(int keycode) {
		if(keycode == Input.Keys.DOWN || keycode == Input.Keys.UP)
			Vy=0f;
		if(keycode == Input.Keys.LEFT|| keycode == Input.Keys.RIGHT)
			Vx=0f;
	}
}
