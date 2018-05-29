package game;

import java.awt.Point;

import ch.hevs.gdx2d.lib.utils.Logger;
import objects.Bullets;

/**
 * @author Philippine Favre et Marc Salamin Class allowing the management of
 *         collisions
 *
 */
public class Collision {
	/**
	 * Method manager the collisions
	 */
	public static void tick() {

		// Data recovery from OurSpaceShip
		Point p3 = GameCode.os.getPosition();
		float h3 = GameCode.os.getHitBox();
		Point borderScreen1 = new Point(0, 0);
		Point borderScreen2 = new Point((int) GameCode.width, (int) GameCode.height);

		// Browse all our vector to see if there are any collisions
		for (int i = 0; i < GameCode.b.size(); i++) {
			for (int j = 0; j < GameCode.ves.size(); j++) {

				// Data recovery from the bullets
				Point p1 = GameCode.b.get(i).getPosition();
				float h1 = GameCode.b.get(i).getHitBox();

				// Data recovery from the EnemyShip
				Point p2 = GameCode.ves.get(j).getPosition();
				float h2 = GameCode.ves.get(j).getHitBox();

				// Collision between bullets and EnemyShip
				if (p1.distance(p2) < (h1 + h2) && GameCode.b.get(i).getState().equals(Bullets.State.FRIEND)) {
					GameCode.b.removeElementAt(i);
					GameCode.ves.get(j).helthDown();
					if (GameCode.ves.get(j).getHealth() < 1) {
						GameCode.ves.removeElementAt(j);
					}

					// Collision between bullets and OurSpaceShip
					if (p1.distance(p3) < (h1 + h3) && GameCode.b.get(i).getState().equals(Bullets.State.ENEMY)) {
						GameCode.b.removeElementAt(i);
						GameCode.os.helthDown();
						if (GameCode.os.getHealth() < 1) {
							// lost
						}
					}
					if (p2.distance(p3) < (h2 + h3)) {

						GameCode.ves.removeElementAt(j);
						GameCode.os.helthDown();
						if (GameCode.os.getHealth() < 1) {
							// lost
						}

					}

					// Collision between EnemyShip and OurSpaceShi
					if (p2.distance(p3) < (h2 + h3)) {
						GameCode.ves.removeElementAt(j);
						GameCode.os.helthDown();
						if (GameCode.os.getHealth() < 1) {
							// lost
						}
					}

				}

			}

		}
		for (int i = 0; i < GameCode.ves.size(); i++) {
			Point p2 = GameCode.ves.get(i).getPosition();
			// Collision between Screen and EnemyShip
			if (p2.getY() <= borderScreen1.getY() || p2.getY() >= borderScreen2.getY()) {

				GameCode.ves.removeElementAt(i);
			}
		}
		for (int i = 0; i < GameCode.b.size(); i++) {
			Point p1 = GameCode.b.get(i).getPosition();

			// Collision between Screen and bullets
			if (p1.getY() <= borderScreen1.getY() || p1.getY() >= borderScreen2.getY()) {

				GameCode.b.removeElementAt(i);
			}
		}

	}
}
