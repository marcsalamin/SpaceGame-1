package objects;

import java.awt.Point;

import game.GameCode;

public class Items {
	public enum  Utility {munUpgrade, shield, life}
	Point p;
	float Vx;
	float Vy;
	float hitBox;
	int hp;
	Utility u;
	
	public Items(Point p, float Vx, float Vy,float hitbox, Utility u){
		this.p = p;
		this.Vx = Vx;
		this.Vy = Vy;
		this.u = u;
		this.hp = 1;
		this.hitBox = hitbox;		
	}
	public void healthDown(){
		hp--;
	}
	public Point getPosition(){
		return p;
	}
	public float getHitbox(){
		return hitBox;
	}
	public int getHealth(){
		return hp;
	}
	public Utility getUtility(){
		return u;
	}
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
			this.p.setLocation(this.p.getX()+this.Vx, this.p.getY()-this.Vy);
	}

}
