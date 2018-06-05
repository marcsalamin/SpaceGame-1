package objects;

import java.awt.Point;

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
		this.hp = 5;
		this.hitBox = hitBox;
		
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
		this.p.setLocation(this.p.getX()+this.Vx, this.p.getY()+this.Vy);
	}

}
