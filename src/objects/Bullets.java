package objects;

import java.awt.Point;

import game.GameCode;
import javafx.scene.shape.Circle;

public class Bullets {
	
	enum State  {FRIEND, ENEMY};
	
	State s;
	Point p;
	float Vx;
	float Vy;
	float hitBox = (float)GameCode.height / 40;
	Circle c;
	 
	public Bullets(Point p, float Vx, float Vy , State s){ 
		 this.p = p;
		 this.Vx = Vx;
		 this.Vy = Vy;
		 this.c= new Circle(p.getX(),p.getY(),hitBox);
		 this.s = s;
	 }
	public void tick(){
		this.p.setLocation(this.p.getX()+this.Vx, this.p.getY()+this.Vy);
	}
	public double getx(){
		return p.getX();
		
	}
	public double gety(){
		return p.getY();
	}
	public Point getPosition(){
		return p;
	}
	public float getHitBox(){
		return hitBox;
	}

}
