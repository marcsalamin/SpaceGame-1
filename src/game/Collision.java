package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import objects.Bullets;
import objects.EnemySpaceShip;

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
		Point pOfOurSpaceShip = GameCode.os.getPosition();
		float hitBoxOfOurSpaceShip = GameCode.os.getHitBox();
		Point borderScreen1 = new Point(0, 0);
		Point borderScreen2 = new Point((int) GameCode.width, (int) GameCode.height);

		// Browse all our vector to see if there are any collisions

		Iterator<Bullets> b;
		ArrayList<Bullets> bulletsToDelete = new ArrayList<Bullets>();
		Iterator<EnemySpaceShip> e;
		ArrayList<EnemySpaceShip> enemyToDelete = new ArrayList<EnemySpaceShip>();
		if(GameCode.boss.size()>0){
		if(GameCode.os.getPosition().distance(GameCode.boss.get(0).getPosition())<GameCode.os.getHitBox()+GameCode.boss.get(0).getHitbox()){
			GameCode.os.helthDown();
			GameCode.boss.get(0).healthDown();
			if (GameCode.os.getHealth() < 1) {
				GameCode.lost();
			}
			
			if(GameCode.boss.get(0).getHealth()<1){
				GameCode.boss.clear();
				GameCode.score+=5;
			}
		}
		}
		e = GameCode.ves.iterator();
		while (e.hasNext()) {
			EnemySpaceShip enem = e.next();

			// Data recovery from the EnemyShip
			Point pOfEnemy = enem.getPosition();
			float hitBoxOfEnemy = enem.getHitBox();
			boolean enemySpaceShipDead = false;

			// Collision between EnemyShip and OurSpaceShi
			if (pOfEnemy.distance(pOfOurSpaceShip) < (hitBoxOfEnemy + hitBoxOfOurSpaceShip)) {
				enemySpaceShipDead = true;
				if (!GameCode.os.shield) {
					GameCode.os.helthDown();
				} 
					
				if (GameCode.os.getHealth() < 1) {
					GameCode.lost();
				}
			}
			if (enemySpaceShipDead) {
				enemyToDelete.add(enem);
			}
		}

		b = GameCode.bullets.iterator();
		while(b.hasNext()) {

			Bullets bul = b.next();
			// Data recovery from the bullets
			Point pOfBullet = bul.getPosition();
			float hitBoxOfBullet = bul.getHitBox();
			boolean bulletDead = false;
			// Collision between ally bullets and Boss
			if(GameCode.boss.size()>0){
			if(pOfBullet.getY()>(GameCode.height-GameCode.boss.get(0).getHitbox())&&bul.getState().equals(Bullets.State.FRIEND)){
				GameCode.boss.get(0).healthDown();
				bulletDead = true;
				if(GameCode.boss.get(0).getHealth()<1){
					GameCode.boss.clear();
					}
				}
			}

			// Collision between enemy bullets and OurSpaceShip
			if (pOfBullet.distance(pOfOurSpaceShip) < (hitBoxOfBullet + hitBoxOfOurSpaceShip) && bul.getState().equals(Bullets.State.ENEMY)) {
				bulletDead = true;
				if (GameCode.os.shield) {
					// do something with the shield ex : start a timer , delete
					// it ...
				} else
					GameCode.os.helthDown();
				if (GameCode.os.getHealth() < 1) {
					 GameCode.lost();
				}
			}

			// Have we shot a enemy vessel
			e = GameCode.ves.iterator();
			while (e.hasNext()) {
				EnemySpaceShip enem = e.next();

				// Data recovery from the EnemyShip
				Point pOfEnemy = enem.getPosition();
				float hitBoxOfEnemy = enem.getHitBox();
				boolean enemySpaceShipDead = false;

				// Collision between bullets and EnemyShip
				if (pOfBullet.distance(pOfEnemy) < (hitBoxOfBullet + hitBoxOfEnemy) && bul.getState().equals(Bullets.State.FRIEND)) {
					enemySpaceShipDead = true;

				}

				// remove the enemy space ship if a collision was detected
				if (enemySpaceShipDead) {
					enemyToDelete.add(enem);
				}

				// remove the bullet if a collision was detected
				if (bulletDead) {
					bulletsToDelete.add(bul);
					continue;
				}


				for (int k = 0; k < GameCode.item.size(); k++) {
					// collision between bullets and Items
					if (pOfBullet.distance(GameCode.item.get(k).getPosition()) < GameCode.item.get(k).getHitbox() + hitBoxOfBullet&&bul.getState().equals(Bullets.State.FRIEND)){

				
				
						GameCode.item.get(k).healthDown();
						if (GameCode.item.get(k).getHealth() < 1) {
							GameCode.os.upgrade(GameCode.item.get(k).getUtility());
							GameCode.item.remove(k);
						}
					}

				}
			}

		}

		for (EnemySpaceShip enem : enemyToDelete) {
			GameCode.ves.remove(enem);
		}

		
		for(Bullets  bul: bulletsToDelete){
			GameCode.bullets.remove(bul);

		}

		for (int i = 0; i < GameCode.ves.size(); i++) {
			Point pOfEnemy = GameCode.ves.get(i).getPosition();
			// Collision between Screen and EnemyShip
			if (pOfEnemy.getY() <= borderScreen1.getY() || pOfEnemy.getY() >= borderScreen2.getY()) {

				GameCode.ves.remove(i);
				GameCode.score++;
			}
		}

		for (int i = 0; i < GameCode.bullets.size(); i++) {
			Point pOfBullet = GameCode.bullets.get(i).getPosition();

		

			// Collision between Screen and bullets
			if (pOfBullet.getY() <= borderScreen1.getY() || pOfBullet.getY() >= borderScreen2.getY()) {

				GameCode.bullets.remove(i);
			}
		}

	}
}
