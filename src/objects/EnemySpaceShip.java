package objects;

import java.awt.Point;

import game.GameCode;
import javafx.scene.shape.Circle;

public class EnemySpaceShip {
	
	Point p;
	float Vx, Vy;
	Circle c;
	float hitBox;
	private int hp;
	double d;
	
	public EnemySpaceShip(Point p,float Vx, float Vy,float hitBox,int hp){
		this.p = p;
		this.Vx = Vx;
		this.Vy = Vy;
		this.hitBox = hitBox;
		this.c= new Circle(p.getX(),p.getY(),hitBox);
		this.hp = hp;
		this.hitBox = hitBox;
	}
	
	public Point getPosition(){
		return this.p;
	}
	
	public void helthDown(){
		this.hp--;
	}
	
	public float getHitBox(){
		return hitBox;
	}
	
	public void tick(){
//		this.Vx=(float)(Math.sin(d)*10);
		if(p.getX()+Vx < GameCode.width && p.getY()-Vy > 0)
			this.p.setLocation(this.p.getX()+this.Vx, this.p.getY()-this.Vy);
		d+=0.1;
	}
	public int getHealth(){
		return hp;
	}

	

}
