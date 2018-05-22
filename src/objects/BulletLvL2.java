package objects;

public class BulletLvL2 extends Bullets {
	BulletLvL2(float v ,double hitBox){
		Vx = 0;
		Vy = v;
		c.setRadius(hitBox);
	}

}
