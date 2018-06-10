package objects;

import java.awt.Point;

import game.GameCode;

public class Items {
	//Class attributes
	public enum  Utility {munUpgrade, shield, life}
	Point p;
	float Vx;
	float Vy;
	float hitBox;
	int hp;
	Utility utility;
	
	//Constructor 
	public Items(Point p, float Vx, float Vy,float hitbox, Utility u){
		this.p = p;
		this.Vx = Vx;
		this.Vy =Math.abs(Vy)*-1;
		this.utility = u;
		this.hp = 1;
		this.hitBox = hitbox;		
	}
	
	//Method to reduce health
	public void healthDown(){
		hp--;
	}
	
	//Method to get the Position
	public Point getPosition(){
		return p;
	}
	
	//Method to get the hitbox
	public float getHitBox(){
		return hitBox;
	}
	
	//Method to get the health
	public int getHealth(){
		return hp;
	}
	//Method to get the utility
	public Utility getUtility(){
		return utility;
	}
	
	//Method to actualize the Object
	public void tick(){
		if(this.Vx==0){
			double sign = Math.random();
			if(sign<0.5)sign=-1;
			else sign = 1;
			this.Vx=(float)(Math.random()*10*sign);
		}
		if(this.p.getX()+ this.Vx < 0||this.p.getX()+this.Vx > GameCode.width)
			this.Vx *=-1;
		if(p.getX()+Vx < GameCode.width &&p.getX()+ Vx > 0)
			this.p.setLocation(this.p.getX()+this.Vx, this.p.getY()+this.Vy);
	}

}
