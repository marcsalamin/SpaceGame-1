package game;

import java.awt.Point;
import java.awt.Rectangle;

public class Boss {
	int hp;
	Rectangle r;
	float Vy = 0;
	Point p;
	float Vx;
	float hitbox;
	
	
	public Boss(Point p, float Vx, float hitbox,int nBoss){
		this.p = p;
		this.Vx = Vx;
		this.hitbox = hitbox;
		this.hp = 1000+ 100*nBoss;
		 
	}
	public Boss(float x, float y , float width, float height,int nBoss){
		Rectangle r = new Rectangle();
		r.setBounds((int)x, (int)y,(int) width,(int) height);
		
	}
	
	public Point getPosition(){
		return p;
	}
	public float getHeight(){
		return  hitbox;
	}
	public void healthDown(){
		hp--;
	}
	public int getHealth(){
		return hp;
	}






}
