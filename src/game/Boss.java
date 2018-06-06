package game;

import java.awt.Rectangle;

public class Boss {
	int hp;
	Rectangle r;
	
	
	
	public Boss(float x, float y , float width, float height,int nBoss){
		Rectangle r = new Rectangle();
		r.setBounds((int)x, (int)y,(int) width,(int) height);
		this.hp = 1000+ 100*nBoss;
	}
	public float getHeight(){
		return (float) (r.getMaxY()-r.getMinY());
	}
	public void healthDown(){
		hp--;
	}
	public int getHealth(){
		return hp;
	}






}
