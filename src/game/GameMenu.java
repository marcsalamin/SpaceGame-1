package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.oracle.jrockit.jfr.Transition;

import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.ScreenManager.TransactionType;
import ch.hevs.gdx2d.lib.utils.Logger;

/**
 * Very simple UI demonstration
 *
 * @author Pierre-André Mudry (mui)
 * @version 1.1
 */
public class GameMenu extends RenderingScreen {
	// class attributes
	Skin skin;
	Stage stage;
	TextButton newGameButton, scoreButton;
	static TextField name;

	// Method to initials
	@Override
	public void onInit() {
		int buttonWidth = 180;
		int buttonHeight = 30;

		stage = new Stage();

		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(Gdx.input.getInputProcessor());
		Gdx.input.setInputProcessor(multiplexer);

		// Load the default skin (which can be configured in the JSON file)
		skin = new Skin(Gdx.files.internal("data/ui/uiskin.json"));
		// Create the button "play"
		newGameButton = new TextButton("new Game", skin); // Use the initialized
															// skin
		newGameButton.setWidth(buttonWidth);
		newGameButton.setHeight(buttonHeight);
		// Create the button "top 10"
		scoreButton = new TextButton("The top 10", skin); // Use the initialized
															// skin
		scoreButton.setWidth(buttonWidth);
		scoreButton.setHeight(buttonHeight);
		// Select the place of those button
		newGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .6));
		scoreButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .7));
		// Create a zone to write the player's name
		name = new TextField("Enter your name", skin);
		name.setWidth(buttonWidth);
		name.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .4));

		name.setTextFieldListener(new TextFieldListener() {
			public void keyTyped(TextField textField, char key) {
				name.setSelection(0, 0);

				// When you press 'enter', do something
				if (key == 13)
					Logger.log("You have typed " + name.getText());
			}
		});

		/**
		 * Adds the buttons to the stage
		 */
		stage.addActor(newGameButton);
		stage.addActor(scoreButton);
		stage.addActor(name);

		/**
		 * Register listener
		 */
		// Go to the Game
		newGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				GameCode.gameOver = false;
				onDispose();
				stage.unfocusAll();
				GameStart.s.transitionTo(1, TransactionType.SMOOTH);

			}
		});
		// Go to the top 10 menu
		scoreButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				GameStart.s.transitionTo(2, TransactionType.SMOOTH);
			}
		});
	}

	@Override
	public void onGraphicRender(GdxGraphics g) {
		g.clear(Color.BLACK);

		// This is required for having the GUI work properly
		stage.act();
		stage.draw();
		// write button status, logo of the school, and FPS
		g.drawStringCentered(g.getScreenHeight() / 4, "Button status " + newGameButton.isChecked());
		g.drawSchoolLogo();
		g.drawFPS();
	}

	// Close windows
	public void onDispose() {
		stage.dispose();
		skin.dispose();
	}

}
