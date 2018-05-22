package objects;

import java.awt.Point;

public class EnemyBullets extends Bullets{

	EnemyBullets(float Vx, float Vy){
		super(EnemySpaceShip.getPosition(), Vx, Vy);
				
	}

}
