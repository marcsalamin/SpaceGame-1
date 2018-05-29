package objects;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.lwjgl.input.Keyboard;

import com.badlogic.gdx.Input;

import game.GameCode;
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
		if(GameCode.os.getPosition().getY() < 0)
			Vy =0;
		if(GameCode.os.getPosition().getY() > GameCode.height)
			Vy = 0;
		if(GameCode.os.getPosition().getX() < 0)
			Vx =0;
		if(GameCode.os.getPosition().getX() > GameCode.width)
			Vx = 0;
		
		
	}
	
	public void onKeyDown(int keycode) {
		float speed = 5;
		switch(keycode){
		case Input.Keys.UP:
			if(GameCode.os.getPosition().getY() < GameCode.height)
				Vy = speed;
			
			break;
		case Input.Keys.DOWN:
			if(GameCode.os.getPosition().getY() > 0)
				Vy =- speed;
			
			break;
		case Input.Keys.LEFT:
			if(GameCode.os.getPosition().getX() > 0)
				Vx =- speed;
			
			break;
		case Input.Keys.RIGHT:
			if(GameCode.os.getPosition().getX() < GameCode.width)
				Vx = speed;
			
			break;
		
		}
	}
	
	public void onKeyUp(int keycode) {
		if(keycode == Input.Keys.DOWN && Vy < 0)
			Vy=0f;
		if(keycode == Input.Keys.UP && Vy >0 )
			Vy=0f;
		if(keycode == Input.Keys.LEFT && Vx < 0)
			Vx=0f;
		if(keycode == Input.Keys.RIGHT && Vx > 0)
			Vx=0f;
	}
}
