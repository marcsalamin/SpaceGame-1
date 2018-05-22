package objects;

public class BulletLvL3Left extends Bullets {
	BulletLvL3Left (float v){
		super(OurSpaceShip.getPosition(),-v, v);
	}
}
