package game;

import java.awt.Point;

import ch.hevs.gdx2d.lib.utils.Logger;
import objects.Bullets;



/**
 * @author Philippine Favre et Marc Salamin
 * Class allowing the management of collisions
 *
 */
public class Collision {
<<<<<<< HEAD
	public static void tick() {
		Point p3 = GameCode.os.getPosition();
		float h3 = GameCode.os.getHitBox();
		Point borderScreen1 = new Point(0, 0);
		Point borderScreen2 = new Point((int) GameCode.width, (int) GameCode.height);
		Logger.dbg("Ticking");
		for (int i = 0; i < GameCode.b.size(); i++) {
			for (int j = 0; j < GameCode.ves.size(); j++) {

				Point p1 = GameCode.b.get(i).getPosition();
				float h1 = GameCode.b.get(i).getHitBox();

				Point p2 = GameCode.ves.get(j).getPosition();
				float h2 = GameCode.ves.get(j).getHitBox();

				if (p1.distance(p2) < (h1 + h2) && GameCode.b.get(i).getState().equals(Bullets.State.FRIEND)) {
=======
<<<<<<< HEAD
	
	
	/**
	 * Method manager the collisions
	 */
=======
>>>>>>> 29e551b4a5e9e23bffacdbc3f966efc91e1c0130
	public static void tick(){
		
		//Data recovery from OurSpaceShip
		Point p3 = GameCode.os.getPosition();
		float h3 = GameCode.os.getHitBox();
		Point borderScreen1 = new Point(0,0);
		Point borderScreen2 = new Point((int)GameCode.width,(int)GameCode.height);
<<<<<<< HEAD
		
		//Browse all our vector to see if there are any collisions
=======
		Logger.dbg("Ticking");
>>>>>>> 29e551b4a5e9e23bffacdbc3f966efc91e1c0130
		for (int i =0; i < GameCode.b.size();i++){
			for(int j = 0; j< GameCode.ves.size();j++){
				
				//Data recovery from the bullets
				Point p1 = GameCode.b.get(i).getPosition(); 
				float h1 = GameCode.b.get(i).getHitBox();
				
				//Data recovery from the EnemyShip
				Point p2 = GameCode.ves.get(j).getPosition(); 
				float h2 = GameCode.ves.get(j).getHitBox();
				
				//Collision between bullets and EnemyShip 
				if(p1.distance(p2)<(h1+h2) && GameCode.b.get(i).getState().equals(Bullets.State.FRIEND)){
>>>>>>> afa04fba4296dbb3ccb9c6a4771b1a083dcf7288
					GameCode.b.removeElementAt(i);
					GameCode.ves.get(j).helthDown();
					if (GameCode.ves.get(j).getHealth() < 1) {
						GameCode.ves.removeElementAt(j);
					}

				}
<<<<<<< HEAD
				if (p1.distance(p3) < (h1 + h3) && GameCode.b.get(i).getState().equals(Bullets.State.ENEMY)) {
=======
				
				//Collision between bullets and OurSpaceShip
				if(p1.distance(p3)<(h1+h3) && GameCode.b.get(i).getState().equals(Bullets.State.ENEMY)){
>>>>>>> afa04fba4296dbb3ccb9c6a4771b1a083dcf7288
					GameCode.b.removeElementAt(i);
					GameCode.os.helthDown();
					if (GameCode.os.getHealth() < 1) {
						// lost
					}
				}
<<<<<<< HEAD

				Logger.dbg(p2.distance(p3) + ", " + h2 + h3);
		

					if (p2.distance(p3) < (h2 + h3)) {

						GameCode.ves.removeElementAt(j);
						GameCode.os.helthDown();
						if (GameCode.os.getHealth() < 1) {
							// lost
						}

					}
					if (p1.distance(borderScreen1) < 20 || p1.distance(borderScreen2) < 20) {
						GameCode.b.removeElementAt(i);
					}
					if (p2.distance(borderScreen1) < 20 || p2.distance(borderScreen2) < 20) {
						GameCode.ves.removeElementAt(j);
					}

=======
<<<<<<< HEAD
				
				//Collision between EnemyShip and OurSpaceShip
=======
<<<<<<< HEAD
				
				Logger.dbg(p2.distance(p3) + ", " + h2+h3);
				if(p2.distance(p3)<h2+h3){
=======
>>>>>>> 29e551b4a5e9e23bffacdbc3f966efc91e1c0130
				if(p2.distance(p3)<(h2+h3)){
>>>>>>> abd9b38535daba0d6b4a79564a8f4ee2d8dd580c
					GameCode.ves.removeElementAt(j);
					GameCode.os.helthDown();
					if(GameCode.os.getHealth()<1){
						//lost
					}	
				}
				
				//Collision between Screen and bullets
				if(p1.distance(borderScreen1)<20||p1.distance(borderScreen2)<20){
					GameCode.b.removeElementAt(i);
				}
				
				//Collision between Screen and EnemyShip
				if(p2.distance(borderScreen1)<20||p2.distance(borderScreen2)<20){
					GameCode.ves.removeElementAt(j);
>>>>>>> afa04fba4296dbb3ccb9c6a4771b1a083dcf7288
				}
			}

		}

	
}
