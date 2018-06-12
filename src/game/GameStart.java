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
	// Class attributes
	static public ScreenManager s = new ScreenManager();
	private int transactionTypeId;
	static Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	public static float height = (float) dimension.getHeight();
	public static float width = (float) dimension.getWidth() / 2;
	public static HighScore highScore = new HighScore();

	// Method to start the game
	GameStart() {
		super((int) width, (int) height);
	}

	// Method to initials
	@Override
	public void onInit() {

		setTitle("Multiple screens and transitions");
		Logger.log("Press space to access to the menu");
		s.registerScreen(GameMenu.class);
		s.registerScreen(GameCode.class);
		s.registerScreen(HighScoreMenu.class);

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

	// Go to the menu as default
	@Override
	public void onKeyDown(int keycode) {
		super.onKeyDown(keycode);

		if (s.getActiveScreen() != null) {
			s.getActiveScreen().onKeyDown(keycode);

			if (keycode == Input.Keys.SPACE)
				s.transitionTo(0, TransactionType.SMOOTH);
			;
		}
	}

	// Main method to throw the game
	public static void main(String[] args) {
		new GameStart();
	}
}
