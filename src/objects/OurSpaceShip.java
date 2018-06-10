package objects;

import java.awt.Point;

import com.badlogic.gdx.Input;

import game.GameCode;
import javafx.scene.shape.Circle;

public class OurSpaceShip {
	
	//Enum to know if it's a friendly bullet or not
	public enum Level {LEVEL1, LEVEL2, LEVEL3};
	
	//Class attributes
	private static Point p;
	private static float Vx, Vy;
	private float hitBox;
	private static int hp, score;
	private Level l;
	public boolean shield = false;
	public static int timerShield;
	public static int timerMun;
	
	//Constructor
	public OurSpaceShip(Point p, float Vx, float Vy, float hitBox, int hp, Level l){
		this.p = p;
		this.Vx = Vx;
		this.Vy = Vy;
		this.hitBox = hitBox;
		this.hp = 3;
		this.l = l;
	}
	//Method to get the bullets lvl
	public Level getLevel(){
		return l;
	}
	//Setter for the bullets lvl
	public void setLevel(Level l){
		this.l = l;
	}
	//Method to get the position of our space ship
	public static Point getPosition(){
		return p;
	}
	//Method to lower the hp of our space ship
	public static void helthDown(){
		hp--;
	}
	//Method to get the hit box of our space ship
	public float getHitBox(){
		return hitBox;
	}
	//Method to get the hp of our space ship
	public int getHealth(){
		return hp;
	}
	//Method to lower the bullets lvl
	public void lower(){
		timerMun = 200;
		switch(l){
		case LEVEL3 :
			l = Level.LEVEL2;
			break;
		case LEVEL2 :
			l = Level.LEVEL1;
			break;
		case LEVEL1 :
			l = Level.LEVEL1;
			break;
		}
	}
	//Method to upgrade our bullets lvl
	public void upgrade(Items.Utility u){
		if(u == Items.Utility.munUpgrade){
			timerMun = 200;
			switch (l){
			case LEVEL1 :
				l = Level.LEVEL2;
				break;
			case LEVEL2 :
				l = Level.LEVEL3;
				break;
			case LEVEL3 :
				break;
				
				
			}
		}
		if(u == Items.Utility.life){
			hp++;
		}
		if(u == Items.Utility.shield){
			shield = true;
			timerShield = 100;
			
		}
	}
	//Method to actualize the Object
	public void ticks(){
		if(timerShield < 0){
			shield = false;
		}
		if(timerMun<0){
			lower();
		}
		p.setLocation(p.getX() + Vx, p.getY() + Vy);
		
		if(GameCode.os.getPosition().getY() < GameCode.os.getHitBox())
			Vy =0;
		if(GameCode.os.getPosition().getY() > GameCode.height-GameCode.os.getHitBox())
			Vy = 0;
		if(GameCode.os.getPosition().getX() < GameCode.os.getHitBox())
			Vx =0;
		if(GameCode.os.getPosition().getX() > GameCode.width-GameCode.os.getHitBox())
			Vx = 0;
		
		
	}
	
	public void onKeyDown(int keycode) {
		float speed = 10f;
		switch(keycode){
		case Input.Keys.UP:
			if(GameCode.os.getPosition().getY() < GameCode.height-GameCode.os.getHitBox())
				Vy = speed;
			
			break;
		case Input.Keys.DOWN:
			if(GameCode.os.getPosition().getY() > GameCode.os.getHitBox())
				Vy =- speed;
			
			break;
		case Input.Keys.LEFT:
			if(GameCode.os.getPosition().getX() > GameCode.os.getHitBox())
				Vx =- speed;
			
			break;
		case Input.Keys.RIGHT:
			if(GameCode.os.getPosition().getX() < GameCode.width-GameCode.os.getHitBox())
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
