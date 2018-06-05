package game;

import java.awt.Point;

import ch.hevs.gdx2d.lib.utils.Logger;
import objects.Bullets;
import objects.Items;
import objects.OurSpaceShip;

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
			
			// Data recovery from the bullets
			Point p1 = GameCode.b.get(i).getPosition();
			float h1 = GameCode.b.get(i).getHitBox();
			boolean bulletDead = false;
			
			// Collision between enemy bullets and OurSpaceShip
			if (p1.distance(p3) < (h1 + h3) && GameCode.b.get(i).getState().equals(Bullets.State.ENEMY)) {
				bulletDead = true;
				if(GameCode.os.shield){
					//do something with the shield ex : start a timer , delete it ...
				}
				else GameCode.os.helthDown();
				if (GameCode.os.getHealth() < 1) {
//					GameCode.lost();
				}
			}
			for (int j = 0; j < GameCode.ves.size(); j++) {
				
			
				



				// Data recovery from the EnemyShip
				Point p2 = GameCode.ves.get(j).getPosition();
				float h2 = GameCode.ves.get(j).getHitBox();
				boolean enemySpaceShipDead = false;

				// Collision between bullets and EnemyShip
				if (p1.distance(p2) < (h1 + h2) && GameCode.b.get(i).getState().equals(Bullets.State.FRIEND)) {
					GameCode.ves.get(j).helthDown();
					if (GameCode.ves.get(j).getHealth() < 1) {
						enemySpaceShipDead = true;
					}
				}
				


				if (p2.distance(p3) < (h2 + h3)) {
					enemySpaceShipDead = true;
					if(GameCode.os.shield){
						//do something with the shield ex : start a timer , delete it ...
					}
					else GameCode.os.helthDown();
					if (GameCode.os.getHealth() < 1) {
//						GameCode.lost();
					}

				}

				// Collision between EnemyShip and OurSpaceShi
				if (p2.distance(p3) < (h2 + h3)) {
					enemySpaceShipDead = true;
					if(GameCode.os.shield){
						//do something with the shield ex : start a timer , delete it ...
					}
					else GameCode.os.helthDown();
					if (GameCode.os.getHealth() < 1) {
//						GameCode.lost();
					}
				}
				// remove the enemy space ship if a collision was detected
				if(enemySpaceShipDead){
					GameCode.ves.removeElementAt(j);	
				}
				// remove the bullet if a collision was detected
				if(bulletDead){
					GameCode.b.removeElementAt(i);
					}
				for(int k = 0; k< GameCode.i.size();k++){
					// collision between bullets and Items
					if(p1.distance(GameCode.i.elementAt(k).getPosition())<GameCode.i.elementAt(k).getHitbox()+h1&&GameCode.b.get(i).getState().equals(Bullets.State.FRIEND) ){
						GameCode.i.elementAt(k).healthDown();
						if(GameCode.i.elementAt(k).getHealth()<1){
							GameCode.os.upgrade(GameCode.i.elementAt(k).getUtility());
							GameCode.i.remove(k);
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
