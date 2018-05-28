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
	double hitBox = GameCode.height / 40;
	Circle c;
	 
	public Bullets(Point p, float Vx, float Vy , State s){ 
		 this.p = p;
		 this.Vx = Vx;
		 this.Vy = Vy;
		 this.c= new Circle(p.getX(),p.getY(),hitBox);
		 this.s = s;
	 }

}
