package game;

import java.util.Comparator;
import java.util.Vector;

import com.badlogic.gdx.graphics.Color;

import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;

/**
 * @author Philippine Favre et Marc Salamin Class for the High Score
 */

class Score {
	int value;
	String name = "Player";
}

public class HighScore {

	// Class attributes
	public Score highScore[] = new Score[10];
	public static boolean newHighScore = false;

	// Method to sort
	public void sorting() {
		Vector<Score> scores = new Vector<Score>();
		for (int i = 0; i < highScore.length; i++) {
			scores.add(highScore[i]);
		}
		scores.sort(new Comparator<Score>() {

			@Override
			public int compare(Score o1, Score o2) {
				return o1.value > o2.value ? -1 : 1;
			}

		});

		for (int i = 0; i < highScore.length; i++) {
			highScore[i] = scores.get(i);
		}

	}

	// Method to rank score
	public void ranking(int score) {
		if (score > highScore[9].value) {
			newHighScore = true;
			highScore[9].value = score;
			highScore[9].name = GameCode.player;
			sorting();
		}

	}

	// Constructor
	public HighScore() {
		for (int i = 0; i < highScore.length; i++) {
			highScore[i] = new Score();

		}
	}
}
