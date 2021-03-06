package objects;

import java.awt.Point;

import game.GameCode;
import javafx.scene.shape.Circle;

/**
 * @author Philippine Favre et Marc Salamin
 *Class to generate the enemies
 */
public class EnemySpaceShip {
	
	public enum Category {ENEMY1, ENEMY2, ENEMY3};
	
	//Class attributes
	private Point p;
	private float Vx, Vy;
	private float hitBox;
	private int hp;
	static double d;
	public Category category;
	
	//Constructor
	public EnemySpaceShip(Point p,float Vx, float Vy,float hitBox,int hp, Category c){
		this.p = p;
		this.Vx = Vx;
		this.Vy = Math.abs(Vy)*-1;
		this.hitBox = hitBox;
		this.hp = hp;
		category = c;
	}
	
	//Getter and setter
	public Point getPosition(){
		return this.p;
	}
	
	public void setPosition(Point p){
		this.p = p;
	}
	
	public float getVx() {
		return Vx;
	}

	public void setVx(float vx) {
		Vx = vx;
	}

	public float getVy() {
		return Vy;
	}

	public void setVy(float vy) {
		Vy = vy;
	}
	
	public int getHealth(){
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public float getHitBox(){
		return hitBox;
	}
	
	public void setHitBox(float hitBox) {
		this.hitBox = hitBox;
	}

	
	//Method 
	public void helthDown(){
		this.hp--;
	}
	
	//Method tick is to manage the move of enemy ship at each refresh. Each category of enemies has a specific movement 
	public void tick(){
		switch(category){
		case ENEMY1:
			this.Vx=(float)(Math.sin(d)*10);
			if(this.p.getX()+ this.Vx < hitBox || this.p.getX()+this.Vx > GameCode.width - hitBox){
				this.Vx=0;
			}

			this.p.setLocation(this.p.getX()+this.Vx, this.p.getY()+this.Vy); 
			d+=0.1/GameCode.ves.size();	
			if(d==6.3)
				d=0;
			break;
		case ENEMY2:
			
			if(this.Vx==0){
				double sign = Math.random();
				if(sign<0.5)sign=-1;
				else sign = 1;
				this.Vx=(float)(Math.random()*10*sign);
			}
			if(this.p.getX()+ this.Vx < 0||this.p.getX()+this.Vx > GameCode.width)
				this.Vx *=-1;
			if(p.getX()+Vx < GameCode.width &&p.getX()+ Vx > 0)
				this.p.setLocation(this.p.getX()+this.Vx, this.p.getY()+this.Vy);
		
			break;
		case ENEMY3:
			this.Vx =0;
			this.p.setLocation(this.p.getX()+this.Vx, this.p.getY()+this.Vy);
			break;
		}
		
	}
	

	

}
