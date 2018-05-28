package game;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Vector;

import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import objects.Bullets;
import objects.EnemySpaceShip;
import objects.OurSpaceShip;
import sun.java2d.pipe.OutlineTextRenderer;
 

public class GameCode extends PortableApplication {
	static Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	public static double height = dimension.getHeight();
	public static double width  = dimension.getWidth();
	
	Point p;
	Vector <Bullets> b = new Vector<Bullets>();
	Vector <EnemySpaceShip> ves = new Vector <EnemySpaceShip>();
	OurSpaceShip os;

	public void generateEnemey(){
		p.setLocation(Math.random()*width, height);
		EnemySpaceShip es = new EnemySpaceShip( p, (float) 0,(float) height/20, width/40, 1);
		ves.add(es);
	}
	public void generateOurSpaceShip(){
		p.setLocation(width/2, 0);
		os = new OurSpaceShip(p,0,0,width/40, 3);
	}


	@Override
	public void onInit() {
		
	}
	
	@Override
	public void onGraphicRender(GdxGraphics g) {
		g.drawCircle((float)os.getPosition().getX(),(float) os.getPosition().getY(),(float)os.getHitBox());
	}

}
