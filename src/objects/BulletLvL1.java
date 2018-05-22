package objects;


public class BulletLvL1 extends Bullets {
	BulletLvL1 (float v,double hitBox ){
		Vx = 0;
		Vy = v;
		c.setRadius(hitBox);
	}

}
