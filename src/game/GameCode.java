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

public class GameCode extends PortableApplication {
	static Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	public static float height = (float) dimension.getHeight();
	public static float width = (float) dimension.getWidth();

	static Vector<Bullets> b = new Vector<Bullets>();
	static Vector<EnemySpaceShip> ves = new Vector<EnemySpaceShip>();
	public static OurSpaceShip os;

	public GameCode() {
		super((int) width, (int) height, true);
	}

	Random r = new Random(124345);

	public void generateEnemy() {
		EnemySpaceShip es = new EnemySpaceShip(new Point(r.nextInt((int) width), (int) (3 * height / 4)), (float) 0,
				(float) 1, width / 80, 1);
		ves.add(es);
	}

	public void generateOurSpaceShip() {
		os = new OurSpaceShip(new Point((int) width / 2, (int) height / 4), (float) 0, (float) 0, width / 80, 3);
	}

	@Override
	public void onInit() {
		for (int i = 0; i < 1; i++) {
			generateEnemy();
		}
		generateOurSpaceShip();
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

		for (int i = 0; i < ves.size(); i++) {
			g.drawCircle((float) ves.get(i).getPosition().getX(), (float) ves.get(i).getPosition().getY(),
					ves.get(i).getHitBox(), Color.RED);
			ves.get(i).tick();
		}
		g.drawCircle((float) os.getPosition().getX(), (float) os.getPosition().getY(), os.getHitBox(), Color.BLUE);
		os.ticks();
		Collision.tick();

	}

	public static void main(String[] args) {
		new GameCode();
	}

}
