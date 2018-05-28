package objects;

import java.awt.Point;

public class Items {
	enum  Utility {munUpgrade, shield, life}
	Point p;
	float Vx;
	float Vy;
	int hp;
	Utility u;
	
	public Items(Point p, float Vx, float Vy, Utility u){
		this.p = p;
		this.Vx = Vx;
		this.Vy = Vy;
		this.u = u;
		this.hp = 10;
		
	}
	public void helthDown(){
		hp--;
	}
	public void tick(){
		this.p.setLocation(this.p.getX()+this.Vx, this.p.getY()+this.Vy);
	}

}
