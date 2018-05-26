package objects;

import java.awt.Point;

import javafx.scene.shape.Circle;

public class EnemySpaceShip {
	Point p;
	float Vx, Vy;
	private  int hp ;
	Circle c;
	double hitBox;
	EnemySpaceShip(Point p,float Vx, float Vy,double hitBox,int hp){
		this.p = p;
		this.Vx = Vx;
		this.Vy = Vy;
		this.c= new Circle(p.getX(),p.getY(),hitBox);
	}
	
	public  Point getPosition(){
		return this.p;
	}
	public  void helthDown(){
		this.hp--;
	}
	

}
