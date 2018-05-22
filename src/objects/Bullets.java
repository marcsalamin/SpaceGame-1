package objects;

import java.awt.Point;

import game.GameCode;
import javafx.scene.shape.Circle;

public class Bullets {
	
 
	 Point p = OurSpaceShip.getPosition;//implémenter la methode
	 float Vx;
	 float Vy;
	 double hitBox = GameCode.height / 40;
	 Circle c;
	 
	 Bullets(float Vx, float Vy, double hitBox){ 
		 this.Vx = Vx;
		 this.Vy = Vy;
		 this.c= new Circle(p.x,p.y,hitBox);
		 
		 
	 }

}
