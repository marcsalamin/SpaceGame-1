package objects;

import java.awt.Point;
import java.awt.Rectangle;

import game.GameCode;

public class Boss {
	
	//enum to increase the difficulty if the boss hasn't a lot of health
	public enum State{ANGRY,HAPPY}
	
	//Class attributes
	int hp;
	float Vy = 0;
	Point p;
	float Vx;
	public State s ;
	public float hitbox;
	
	//Constructor
	public Boss(Point p, float Vx, float hitbox,int nBoss){
		this.p = p;
		this.Vx = Vx;
		this.hitbox = hitbox;
		this.hp = 100*nBoss;
		s = State.HAPPY;
		 
	}
	
	//Method to get the position
	public Point getPosition(){
		return p;
	}
	
	//Method to get the hitbox
	public float getHitbox(){
		return  hitbox;
	}
	
	//Method to lower the Boss's life
	public void healthDown(){
		hp--;
	}
	
	//Method to get the health of the Boss
	public int getHealth(){
		return hp;
	}
	
	//Method to actualize the Object
	public void ticks(){
		if(hp<20){
			s = State.ANGRY;
		}
		else s = State.HAPPY;
		
		if(p.getX()+ Vx <= hitbox||p.getX()+Vx >= GameCode.width-hitbox)
			this.Vx *=-1;
		p.setLocation(p.getX() + Vx, p.getY());
		
	}






}
