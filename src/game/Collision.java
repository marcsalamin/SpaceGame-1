package game;

import java.awt.Point;

public class Collision {
	
	public void tick(){
		for (int i =0; i < GameCode.b.size();i++){
			for(int j = 0; j< GameCode.ves.size();i++){
				Point p1 = GameCode.b.get(i).getPosition(); 
				Point p2 = GameCode.ves.get(j).getPosition(); 
				float h1 = GameCode.b.get(i).getHitBox();
				float h2 = GameCode.ves.get(j).getHitBox();
				
				if(p1.distance(p2)<h1+h2){
					
				}
				
			}
		}
		
	}

}
