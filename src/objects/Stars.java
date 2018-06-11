package objects;

import java.awt.Point;

import objects.Bullets.State;

public class Stars {

	//Class attributes
	Point p;
	float Vx;
	float Vy;
	//Constructor
		public Stars(Point p){
			 this.p = (Point)p.clone();
			 Vx = 0;
			 Vy = -1f;
	
		 }
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
		
		public void tick(){
			this.p.setLocation(this.p.getX()+this.Vx, this.p.getY()+this.Vy);
		}
}
