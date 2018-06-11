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

public class HighScore extends RenderingScreen {

	// Class attributes
	public Score highScore[] = new Score[10];
	public boolean newHighScore = false;

	@Override
	public void onKeyDown(int keycode) {
		// TODO Auto-generated method stub
		super.onKeyDown(keycode);
		System.out.println(keycode);
	}

	// Method to sort
	public void sorting() {
		Vector<Score> scores = new Vector<Score>();
		for (int i = 0; i < highScore.length; i++) {
			scores.add(highScore[i]);
		}
		scores.sort(new Comparator<Score>() {

			@Override
			public int compare(Score o1, Score o2) {
				return o1.value > o2.value ? 1 : -1;
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

	public void onGraphicRender(GdxGraphics h) {
		h.clear(Color.BLACK);
		h.setColor(Color.WHITE);
		h.drawString(GameCode.width / 2, GameCode.height - 180, "Player        Score");
		for (int i = 0; i < highScore.length; i++) {

			h.drawString(GameCode.width / 2, GameCode.height - 200 - (i * 20), highScore[i].name +"           "+ highScore[i].value);
		}
	}

	@Override
	public void onInit() {
		System.out.println("Pouet");
		for (int i = 0; i < highScore.length; i++) {
			highScore[i] = new Score();

		}
		// TODO Auto-generated method stub

	}
}
