package objects;

import java.awt.Point;

import game.GameCode;
import javafx.scene.shape.Circle;

public class Bullets {
	
 
	 Point p;
	 float Vx;
	 float Vy;
	 double hitBox = GameCode.height / 40;
	 Circle c;
	 
	 Bullets(Point p, float Vx, float Vy){ 
		 this.p = p;
		 this.Vx = Vx;
		 this.Vy = Vy;
		 this.c= new Circle(p.getX(),p.getY(),hitBox);
	 }

}
