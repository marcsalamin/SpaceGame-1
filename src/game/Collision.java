package game;

import java.awt.Point;

public class Collision {
	
	public void tick(){
		for (int i =0; i < GameCode.b.size();i++){
			for(int j = 0; j< GameCode.ves.size();i++){
				Point p1 = GameCode.b.get(i).getPosition(); 
				Point p2 = GameCode.ves.get(j).getPosition(); 
				Point p3 = GameCode.os.getPosition();
				float h1 = GameCode.b.get(i).getHitBox();
				float h2 = GameCode.ves.get(j).getHitBox();
				float h3 = GameCode.os.getHitBox();
				
				if(p1.distance(p2)<h1+h2){
					GameCode.b.removeElementAt(i);
					GameCode.ves.get(j).helthDown();
					if(GameCode.ves.get(j).getHealth()<1){
						GameCode.ves.removeElementAt(j);
					}
					
				}
				
			}
		}
		
	}

}
