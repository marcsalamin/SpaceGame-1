package game;

import java.util.Comparator;
import java.util.Vector;

import com.badlogic.gdx.graphics.Color;

import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;

public class HighScoreMenu extends RenderingScreen {


	@Override
	public void onKeyDown(int keycode) {
		// TODO Auto-generated method stub
		super.onKeyDown(keycode);

	}
	public void onGraphicRender(GdxGraphics h) {
		h.clear(Color.BLACK);
		h.setColor(Color.WHITE);
		h.drawString(GameCode.width / 2-200, GameCode.height - 180, "Player");
		h.drawString(GameCode.width / 2+200, GameCode.height - 180, "Score");
		for (int i = 0; i < GameStart.highScore.highScore.length; i++) {

			h.drawString(GameCode.width / 2-200, GameCode.height - 200 - (i * 20), GameStart.highScore.highScore[i].name);
			h.drawString(GameCode.width / 2+200, GameCode.height - 200 - (i * 20),""+GameStart.highScore.highScore[i].value);
		}
	}

	@Override
	public void onInit() {

	}
}
