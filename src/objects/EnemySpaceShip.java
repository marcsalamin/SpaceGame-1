package objects;

import java.awt.Point;

import game.GameCode;
import javafx.scene.shape.Circle;

public class EnemySpaceShip {
	
	Point p;
	float Vx, Vy;
	Circle c;
	double hitBox;
	private int hp;
	
	public EnemySpaceShip(Point p,float Vx, float Vy,double hitBox,int hp){
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
	public void changeVx(EnemySpaceShip e, double d){
		e.Vx=(float)(Math.sin(d)*GameCode.width/40);
	}
	

}
