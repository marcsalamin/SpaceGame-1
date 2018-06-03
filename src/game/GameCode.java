package game;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;
import java.util.Vector;

import com.badlogic.gdx.graphics.Color;

import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import objects.Bullets;
import objects.EnemySpaceShip;
import objects.OurSpaceShip;
import objects.Bullets.State;
import sun.java2d.pipe.OutlineTextRenderer;

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
	static Vector <Bullets> b = new Vector<Bullets>();
	public static Vector <EnemySpaceShip> ves = new Vector <EnemySpaceShip>();
	public static OurSpaceShip os = new OurSpaceShip(new Point((int)width/2, (int)height/4), (float) 0, (float)0, width/80, 3, OurSpaceShip.Level.LEVEL2);
	static int timer;
	
	//Constructor
	public GameCode(){
		super((int)width,(int) height,true);
	}
	
	Random r = new Random(124345);
	Random rEnemy = new Random(124345);
	
	//Method to generate EnemyShip
	public void generateEnemy(EnemySpaceShip.Category e){
		EnemySpaceShip es = new EnemySpaceShip(new Point(r.nextInt((int) width), (int) (9 * height / 10)), (float) 0,
				5f, width / 80, 1, e);
		ves.add(es);
	}
	public void generateBullet(Point p, float Vx, float Vy , State s){
		Bullets b = new Bullets(p, Vx, Vy, s);
		this.b.add(b);
	}
	
	@Override
	public void onInit() {
		generateEnemy(EnemySpaceShip.Category.ENEMY2);
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
		if(timer%10 == 0){
			switch(rEnemy.nextInt(2)){
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
		
		if(timer%30 == 0){
			for(int i = 0; i< ves.size(); i++){
				generateBullet(ves.get(i).getPosition(),0,-20,Bullets.State.ENEMY);
			}
		}
	
		
		//Draw a red circle for all the EnemyShip
		for(int i = 0; i< ves.size(); i++){
			g.drawCircle((float)ves.get(i).getPosition().getX(),(float) ves.get(i).getPosition().getY(), ves.get(i).getHitBox(), Color.RED);
			ves.get(i).tick();
		}
		for(int j = 0; j< b.size();j++){
			g.drawCircle((float)b.get(j).getPosition().getX(),(float) b.get(j).getPosition().getY(), b.get(j).getHitBox(), Color.BLACK);
			b.get(j).tick();
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
