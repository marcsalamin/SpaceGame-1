package objects;

import java.awt.Point;
import java.awt.Rectangle;

import game.GameCode;

public class Boss {
	int hp;
	Rectangle r;
	float Vy = 0;
	Point p;
	float Vx;
	public float hitbox;
	
	
	public Boss(Point p, float Vx, float hitbox,int nBoss){
		this.p = p;
		this.Vx = Vx;
		this.hitbox = hitbox;
		this.hp = 1000+ 100*nBoss;
		 
	}
	
	public Point getPosition(){
		return p;
	}
	public float getHitbox(){
		return  hitbox;
	}
	public void healthDown(){
		hp--;
	}
	public int getHealth(){
		return hp;
	}
	
	public void ticks(){
		if(p.getX()+ Vx < 0||p.getX()+Vx > GameCode.width)
			this.Vx *=-1;
		p.setLocation(p.getX() + Vx, p.getY());
		
	}






}
