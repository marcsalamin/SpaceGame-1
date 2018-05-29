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
	static Vector <EnemySpaceShip> ves = new Vector <EnemySpaceShip>();
	public static OurSpaceShip os = new OurSpaceShip(new Point((int)width/2, (int)height/4), (float) 0, (float)0, width/80, 3);
	
	//Constructor
	public GameCode(){
		super((int)width,(int) height,true);
	}
	
	Random r = new Random(124345);
	
	//Method to generate EnemyShip
	public void generateEnemy(){
		EnemySpaceShip es = new EnemySpaceShip(new Point(r.nextInt((int) width), (int) (9 * height / 10)), (float) 0,
				5f, width / 80, 1);
		ves.add(es);
	}
	public void generateBullet(Point p, float Vx, float Vy , State s){
		Bullets b = new Bullets(p, Vx, Vy, s);
		this.b.add(b);
	}
	
	@Override
	public void onInit() {

	}

	@Override
	public void onKeyDown(int keycode) {
		super.onKeyDown(keycode);
		os.onKeyDown(keycode);
	}

	public void onKeyUp(int keycode) {
		os.onKeyUp(keycode);
	}
	
	@Override
	public void onGraphicRender(GdxGraphics g) {
		g.clear(Color.WHITE);
		generateBullet(os.getPosition(),0,20,Bullets.State.FRIEND);
		generateEnemy();
	
		
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
