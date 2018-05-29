package game;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Vector;

import com.badlogic.gdx.graphics.Color;

import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import objects.Bullets;
import objects.EnemySpaceShip;
import objects.OurSpaceShip;
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
	static OurSpaceShip os = new OurSpaceShip(new Point((int)width/2, (int)height/4), (float) 0, (float)0, width/80, 3);
	
	//Constructor
	public GameCode(){
		super((int)width,(int) height,true);
	}
	
	//Method to generate EnemyShip
	public void generateEnemy(){
		EnemySpaceShip es = new EnemySpaceShip(new Point((int)(Math.random()*width),(int) (3*height/4)), (float) 0,(float) 1, width/80, 1);
		ves.add(es);
	}
	
	@Override
	public void onInit() {
		generateEnemy();
	}
	
	@Override
	public void onKeyDown(int keycode) {
		super.onKeyDown(keycode);
		os.onKeyDown(keycode);
	}
	
	public void onKeyUp(int keycode){
		os.onKeyUp(keycode);
	}
	
	@Override
	public void onGraphicRender(GdxGraphics g) {
		g.clear(Color.WHITE);
		
		//Draw a red circle for all the EnemyShip
		for(int i = 0; i< ves.size(); i++){
			g.drawCircle((float)ves.get(i).getPosition().getX(),(float) ves.get(i).getPosition().getY(), ves.get(i).getHitBox(), Color.RED);
			ves.get(i).tick();
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
