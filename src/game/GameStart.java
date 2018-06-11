package game;

import java.awt.Dimension;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.ScreenManager;
import ch.hevs.gdx2d.lib.ScreenManager.TransactionType;
import ch.hevs.gdx2d.lib.utils.Logger;

/**
 * Show how to add multiple screen and switch between them with different
 * transitions.
 *
 * @author Pierre-André Mudry (mui)
 * @version 1.1
 */
public class GameStart extends PortableApplication {

	static public ScreenManager s = new ScreenManager();
	private int transactionTypeId;
	static Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	public static float height = (float) dimension.getHeight();
	public static float width = (float) dimension.getWidth() / 2;

	GameStart() {
		super((int) width, (int) height);
	}

	@Override
	public void onInit() {
		setTitle("Multiple screens and transitions");
		Logger.log("Press enter/space to show the next screen, 1/2/3 to transition to them");
		s.registerScreen(GameMenu.class);
		s.registerScreen(GameCode.class);
		s.registerScreen(HighScore.class);
	}

	@Override
	public void onGraphicRender(GdxGraphics g) {
		s.render(g);
	}

	@Override
	public void onClick(int x, int y, int button) {
		// Delegate the click to the child class
		s.getActiveScreen().onClick(x, y, button);
	}

	@Override
	public void onKeyUp(int keycode) {
		super.onKeyUp(keycode);

		if (s.getActiveScreen() != null)
			s.getActiveScreen().onKeyUp(keycode);

	}

	@Override
	public void onKeyDown(int keycode) {
		super.onKeyDown(keycode);

		if (s.getActiveScreen() != null) {
			s.getActiveScreen().onKeyDown(keycode);

			if (keycode == Input.Keys.SPACE)
				s.transitionTo(0, TransactionType.SMOOTH);;
		}
	}

	public static void main(String[] args) {
		new GameStart();
	}
}
