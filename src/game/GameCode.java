package game;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Vector;

import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import objects.Bullets;
import objects.EnemySpaceShip;
 

public class GameCode extends PortableApplication {
	Point p;
	Vector <Bullets> b = new Vector<Bullets>();
	Vector <EnemySpaceShip> ves = new Vector <EnemySpaceShip>();

	
	
	public void generateEnemey(){
		p.setLocation(Math.random()*width, height);
		EnemySpaceShip es = new EnemySpaceShip( p, (float) 0,(float) height/20, width/40, 1);
		ves.add(es);
	}


	static Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	public static double height = dimension.getHeight();
	public static double width  = dimension.getWidth();



	@Override
	public void onInit() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onGraphicRender(GdxGraphics g) {
		// TODO Auto-generated method stub
		g.drawCircle(40, 30, 50);
	}

}
