package game;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import com.badlogic.gdx.graphics.Color;

import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import objects.Bullets;
import objects.Bullets.State;
import objects.EnemySpaceShip;
import objects.Items;
import objects.OurSpaceShip;

/**
 * @author Philippine Favre et Marc Salamin
 * Main class. We use gdx2D
 *
 */
public class GameCode extends PortableApplication {
	
	//Recovery screen data
	static Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	public static float height = (float) dimension.getHeight();
	public static float width  = (float) dimension.getWidth();
	
	//Class attributes
	static ArrayList<Bullets> bullets = new ArrayList<Bullets>();
	public static ArrayList <EnemySpaceShip> ves = new ArrayList <EnemySpaceShip>();
	public static ArrayList<Items> item = new ArrayList<Items>();
	public static OurSpaceShip os = new OurSpaceShip(new Point((int)width/2, (int)height/4), (float) 0, (float)0, width/80, 3, OurSpaceShip.Level.LEVEL1);
	static int timer;
	
	//Constructor
	public GameCode(){
		super((int)width,(int) height,true);
	}
	
	//Create a random variable who stay fix 
	Random r = new Random(124345);
	
	//Method to generate EnemyShip
	public void generateEnemy(EnemySpaceShip.Category e){
		EnemySpaceShip es = new EnemySpaceShip(new Point(r.nextInt((int) width), (int) (9 * height / 10)), (float) 0,
				5f, width / 80, 1, e);
		ves.add(es);
	}
	
	//Method to generate Bullets
	public void generateBullet(Point p, float Vx, float Vy , State s){
		Bullets b = new Bullets(p, Vx, Vy, s);
		this.bullets.add(b);
	}
	
	//Method to generate Item depending the level of our spaceShip
	public void generateItem(){
		Point p = new Point(r.nextInt((int) width), (int) (9 * height / 10));
		float Vx = 0;
		float Vy = 5f;
		float hitBox = height/80;
		Items mun;
		
		switch (r.nextInt(2)){
		case 0:
			Items life = new Items(p,Vx,Vy,hitBox,Items.Utility.life);
			this.item.add(life);
			break;
		case 1:
			if(os.getLevel()!= OurSpaceShip.Level.LEVEL3){
				Items.Utility u = Items.Utility.munUpgrade;
				mun = new Items(p,Vx,Vy,hitBox,u);
			}
			else {
				Items.Utility u = Items.Utility.life;
				mun = new Items(p,Vx,Vy,hitBox,u);
				}
			
			this.item.add(mun);
			break;
		case 2:
			Items shield = new Items(p,Vx,Vy,GameCode.height/80,Items.Utility.shield);
			this.item.add(shield);
			break;
		}	
	}
	
	@Override
	public void onInit() {
		generateItem();
	}

	@Override
	public void onKeyDown(int keycode) {
		super.onKeyDown(keycode);
		os.onKeyDown(keycode);
		
	}

	public void onKeyUp(int keycode) {
		os.onKeyUp(keycode);
	}
	
	public void lost(){
		
	}
	
	@Override
	public void onGraphicRender(GdxGraphics g) {
		timer++;
		g.clear(Color.WHITE);
		if(timer%100 == 0){
			generateItem();
		}
		if(timer%50 == 0){
			switch(r.nextInt(2)){
			case 0:
				generateEnemy(EnemySpaceShip.Category.ENEMY1);
				break;
			case 1:
				generateEnemy(EnemySpaceShip.Category.ENEMY2);
				break;
			case 2:
				generateEnemy(EnemySpaceShip.Category.ENEMY3);
				break;	
			}
		}
		
		if(timer%20 == 0){
				if(os.getLevel() == OurSpaceShip.Level.LEVEL1)
					generateBullet(os.getPosition(),0,20,Bullets.State.FRIEND);
				if(os.getLevel() == OurSpaceShip.Level.LEVEL2){
					generateBullet(os.getPosition(),0,20,Bullets.State.FRIEND);
					generateBullet(os.getPosition(),10,20,Bullets.State.FRIEND);
					generateBullet(os.getPosition(),-10,20,Bullets.State.FRIEND);
				}
		}
		
		if(os.getLevel() == OurSpaceShip.Level.LEVEL3){
			if(timer%10==0){
				if(os.getLevel() == OurSpaceShip.Level.LEVEL3){
					generateBullet(os.getPosition(),0,20,Bullets.State.FRIEND);
					generateBullet(os.getPosition(),10,20,Bullets.State.FRIEND);
					generateBullet(os.getPosition(),-10,20,Bullets.State.FRIEND);
					
				}
			}
		}
		
		if(timer%50 == 0){
			for(int i = 0; i< ves.size(); i++){
				generateBullet(ves.get(i).getPosition(),0,-20,Bullets.State.ENEMY);
			}
		}
	
		
		//Draw a red circle for all the EnemyShip
		for(int i = 0; i< ves.size(); i++){
			g.drawCircle((float)ves.get(i).getPosition().getX(),(float) ves.get(i).getPosition().getY(), ves.get(i).getHitBox(), Color.RED);
			ves.get(i).tick();
		}
		for(int j = 0; j< bullets.size();j++){
			g.drawCircle((float)bullets.get(j).getPosition().getX(),(float) bullets.get(j).getPosition().getY(), bullets.get(j).getHitBox(), Color.BLACK);
			bullets.get(j).tick();
		}
		// Draw a gold circle for Items
		for(int k = 0; k< item.size();k++){
			g.drawAntiAliasedCircle((float)item.get(k).getPosition().getX(),(float) item.get(k).getPosition().getY(), item.get(k).getHitbox(), Color.GOLD);
			item.get(k).tick();
		}
		
		//Draw a blue circle for OurSpaceShip
		g.drawCircle((float)os.getPosition().getX(),(float) os.getPosition().getY(),os.getHitBox(), Color.BLUE);
		os.ticks();
		
		//Call the method tick() from Collision at all refresh
		Collision.tick();
	}	
	
	//Main method to launch the game
	public static void main(String[] args) {
		new GameCode();
	}

}
