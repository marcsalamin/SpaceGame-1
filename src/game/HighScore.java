package game;

import com.badlogic.gdx.graphics.Color;

import ch.hevs.gdx2d.lib.GdxGraphics;

/**
 * @author Philippine Favre et Marc Salamin
 *Class for the High Score
 */

public class HighScore {
	
	//Class attributes
	public static int highScore [] = new int [10];
	public static String names[] = new String[10];
	public static boolean newHighScore = false;
	
	
	//Method to sort
	public static void sorting(){
		for(int i =0; i < highScore.length;i++){
			int toCheck = highScore[9];
			if(toCheck > highScore[i]){
				for(int j = highScore.length-1; j>i;j--){
					highScore[j]=highScore[j-1];
					names[j]=names[j-1];
				}
			highScore[i]=toCheck;
			names[i]= GameCode.player;
			continue;
			}
			
		}
	}
	//Method to rank score
	public static void ranking(int score){
		if(score > highScore[9]){
			newHighScore = true;
			highScore[9]=score;
			sorting();
		}
	}
	public static void scoreToString(){
		for(int i = 0; i< highScore.length;i++){
			System.out.println(highScore[i]);
		}
	}
	public void onGraphicRender(GdxGraphics h) {
		h.clear(Color.BLACK);
		h.setColor(Color.WHITE);
		for(int i = 0; i<HighScore.highScore.length;i++){
			h.drawString(50, i*10+10, HighScore.names[i]);
			h.drawString(200, i*10+10,"" + HighScore.highScore[i]);
		}
	}
}
