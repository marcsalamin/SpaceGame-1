package objects;

import java.awt.Point;

import game.GameCode;
import javafx.scene.shape.Circle;

/**
 * @author Philippine Favre et Marc Salamin
 *Class for the Bullets
 */
public class Bullets {
	
	//Enum to know if it's a friendly bullet or not
	public enum State  {FRIEND, ENEMY};
	
	//Class attributes
	State s;
	Point p;
	float Vx;
	float Vy;
	float hitBox = (float)GameCode.height / 100;
	Circle c;
	 
	//Constructor
	public Bullets(Point p, float Vx, float Vy , State s){ 
		 this.p = p;
		 this.Vx = Vx;
		 this.Vy = Vy;
		 this.c= new Circle(p.getX(),p.getY(),hitBox);
		 this.s = s;
	 }
	
	//Getter the x of the position
	public double getx(){
		return p.getX();
		
	}
	
	//Getter the y of the position
	public double gety(){
		return p.getY();
	}
	
	//Getter the position
	public Point getPosition(){
		return p;
	}
	
	//Getter the hitBox
	public float getHitBox(){
		return hitBox;
	}
	
	//Getter the state
	public State getState(){
		return s;
	}
	
	//Method 
	public void tick(){
		this.p.setLocation(this.p.getX()+this.Vx, this.p.getY()+this.Vy);
	}

}
