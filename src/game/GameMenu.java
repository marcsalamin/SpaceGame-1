package game;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

//import ch.hevs.gdx2d.demos.menus.DemoGUI;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.utils.Logger;

/**
 * @author Philippine Favre et Marc Salamin
 * Class to manage the menu at the beginning
 *
 */
public class GameMenu extends JFrame{
	public static boolean gameMenu = true;


	

	GameMenu(){
		setTitle("Space Shooter");
		setSize((int)GameCode.width/10,(int)GameCode.height/10);
		setLocation(new Point((int)GameCode.width/2,(int)GameCode.height/2));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(2, 1));
		
		JButton play = new JButton("Play");
		JButton score = new JButton("Score");
		
		play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				new GameCode();
			}
		});
		score.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for(int i = 0; i<HighScore.highScore.length;i++){
					System.out.println(HighScore.highScore[i]);
				}
		
			}
		});
		add(play);
		add(score);
		setVisible(true);
		
		
	}

}

//extends PortableApplication
//
//Skin skin;
//Stage stage;
//TextButton newGameButton, quitGameButton;
//TextField textArea;
//
//public static void main(String[] args) {
//	new DemoGUI();
//}
//
//@Override
//public void onInit() {
//	int buttonWidth = 180;
//	int buttonHeight = 30;
//
//	setTitle("GUI demonstration");
//
//	stage = new Stage();
//	Gdx.input.setInputProcessor(stage);// Make the stage consume events
//
//	// Load the default skin (which can be configured in the JSON file)
//	skin = new Skin(Gdx.files.internal("data/ui/uiskin.json"));
//
//	newGameButton = new TextButton("Click me", skin); // Use the initialized skin
//	newGameButton.setWidth(buttonWidth);
//	newGameButton.setHeight(buttonHeight);
//
//	quitGameButton = new TextButton("Useless button", skin); // Use the initialized skin
//	quitGameButton.setWidth(buttonWidth);
//	quitGameButton.setHeight(buttonHeight);
//
//	newGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .6));
//	quitGameButton.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .7));
//
//	textArea = new TextField("Enter some text...", skin);
//	textArea.setWidth(buttonWidth);
//	textArea.setPosition(Gdx.graphics.getWidth() / 2 - buttonWidth / 2, (int) (Gdx.graphics.getHeight() * .4));
//
//	textArea.setTextFieldListener(new TextFieldListener() {
//		public void keyTyped(TextField textField, char key) {
//			textArea.setSelection(0, 0);
//
//			// When you press 'enter', do something
//			if (key == 13)
//				Logger.log("You have typed " + textArea.getText());
//		}
//	});
//
//	/**
//	 * Adds the buttons to the stage
//	 */
//	stage.addActor(newGameButton);
//	stage.addActor(quitGameButton);
//	stage.addActor(textArea);
//
//	/**
//	 * Register listener
//	 */
//	newGameButton.addListener(new ClickListener() {
//		@Override
//		public void clicked(InputEvent event, float x, float y) {
//			super.clicked(event, x, y);
//
//			if (newGameButton.isChecked())
//				Logger.log("Button is checked");
//			else
//				Logger.log("Button is not checked");
//		}
//	});
//}
//
//@Override
//public void onGraphicRender(GdxGraphics g) {
//	g.clear(Color.BLACK);
//
//	// This is required for having the GUI work properly
//	stage.act();
//	stage.draw();
//
//	g.drawStringCentered(getWindowHeight() / 4, "Button status " + newGameButton.isChecked());
//	g.drawSchoolLogo();
//	g.drawFPS();
//}
//
//@Override
//public void onDispose() {
//	super.onDispose();
//	stage.dispose();
//	skin.dispose();
//}
//}

