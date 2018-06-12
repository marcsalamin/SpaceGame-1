package game;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;

import ch.hevs.gdx2d.components.audio.MusicPlayer;
import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.components.screen_management.RenderingScreen;
import ch.hevs.gdx2d.lib.GdxGraphics;
import ch.hevs.gdx2d.lib.utils.Logger;
import objects.Boss;
import objects.Bullets;
import objects.Bullets.State;
import objects.EnemySpaceShip;
import objects.Items;
import objects.OurSpaceShip;
import objects.Stars;

/**
 * @author Philippine Favre et Marc Salamin Main class. We use gdx2D
 *
 */
public class GameCode extends RenderingScreen {

	// Recovery screen data
	static Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	public static float height = (float) dimension.getHeight();
	public static float width = (float) dimension.getWidth() / 2;

	// Class attributes
	static ArrayList<Bullets> bullets = new ArrayList<Bullets>();
	public static ArrayList<EnemySpaceShip> ves = new ArrayList<EnemySpaceShip>();
	public static ArrayList<Items> item = new ArrayList<Items>();
	public static ArrayList<Boss> boss = new ArrayList<Boss>();
	public static ArrayList<Stars> stars = new ArrayList<Stars>();
	public static OurSpaceShip os;
	static int timer;
	static int bossTimer;
	static int backGroundTimer;
	static int score = 0;
	static int nBoss;
	static boolean onlyOne = true;
	static boolean gameOver = false;
	static String player = "";
	// Music
	MusicPlayer ambianceMusic = new MusicPlayer("8-bit Detective.wav");
	// Pictures
	BitmapImage theBoss;
	BitmapImage enemy1, enemy2, enemy3;
	BitmapImage ourShip;
	BitmapImage enemyBullets, ourBullets;
	BitmapImage shield, life, munUp;
	BitmapImage shieldOnShip;
	BitmapImage star;

	// Constructor
	public GameCode() {
	}

	// Create a random variable who stay fix
	Random r = new Random(124345);

	// Method to generate EnemyShip
	public void generateEnemy(EnemySpaceShip.Category e) {
		EnemySpaceShip es = new EnemySpaceShip(new Point(r.nextInt((int) width), (int) (9 * height / 10)), (float) 0,
				2f, width / 40, 1, e);
		ves.add(es);
	}

	// Method to generate Bullets
	public void generateBullet(Point p, float Vx, float Vy, State s) {
		Bullets b = new Bullets(p, Vx, Vy, s);
		this.bullets.add(b);
	}

	// Method to generate Boss
	public void generateBoss() {
		Boss b = new Boss(new Point((int) width / 2, (int) (height - (height / 6))), 5f, width / 20, ++nBoss);
		boss.add(b);
	}

	// Method to generate Stars
	public void generateStar() {
		Stars s = new Stars(new Point((int) (Math.random() * width), (int) (height)));
		stars.add(s);
	}

	// Method to generate Item
	public void generateItem() {
		Point p = new Point(r.nextInt((int) width), (int) (9 * height / 10));
		float Vx = 0;
		float Vy = 5f;
		float hitBox = height / 80;
		Items mun;
		// switch to choose randomly witch bonus is going to give this item
		switch (r.nextInt(3)) {
		case 0:
			Items life = new Items(p, Vx, Vy, hitBox, Items.Utility.life);
			this.item.add(life);
			break;
		case 1:
			if (os.getLevel() != OurSpaceShip.Level.LEVEL3) {
				Items.Utility u = Items.Utility.munUpgrade;
				mun = new Items(p, Vx, Vy, hitBox, u);
			} else {
				Items.Utility u = Items.Utility.life;
				mun = new Items(p, Vx, Vy, hitBox, u);
			}

			this.item.add(mun);
			break;
		case 2:
			Items shield = new Items(p, Vx, Vy, GameCode.height / 80, Items.Utility.shield);
			this.item.add(shield);
			break;
		}
	}

	@Override
	// Method to initials
	public void onInit() {
		// play the sound
		ambianceMusic.loop();
		// name the player
		player = GameMenu.name.getText();
		// clear all the arraylist
		bullets.clear();
		ves.clear();
		item.clear();
		boss.clear();
		stars.clear();

		// build our spaceship
		os = new OurSpaceShip(new Point((int) width / 2, (int) height / 4), (float) 0, (float) 0, width / 40, 3,
				OurSpaceShip.Level.LEVEL1);
		// Import image
		theBoss = new BitmapImage("enemyBlue1.png");
		enemy1 = new BitmapImage("enemyGreen1.png");
		enemy2 = new BitmapImage("enemyGreen2.png");
		enemy3 = new BitmapImage("enemyGreen3.png");
		ourShip = new BitmapImage("enemyRed5.png");
		enemyBullets = new BitmapImage("laserBlue13.png");
		ourBullets = new BitmapImage("laserRed16.png");
		shield = new BitmapImage("shield_gold.png");
		life = new BitmapImage("playerLife1_red.png");
		munUp = new BitmapImage("bolt_bronze.png");
		shieldOnShip = new BitmapImage("shield3.png");
		star = new BitmapImage("etoile.png");

		// reboot attributes
		nBoss = 0;
		timer = 0;
		bossTimer = 0;
		score = 0;
		onlyOne = true;
	}

	@Override
	// Method to detect if a key is pressed
	public void onKeyDown(int keycode) {
		super.onKeyDown(keycode);
		os.onKeyDown(keycode);

	}

	// Method to detect if a key is released
	public void onKeyUp(int keycode) {
		os.onKeyUp(keycode);
	}

	// Method to stop the game
	public static void lost() {
		gameOver = true;

	}

	@Override
	// Graph and timer to generate all
	public void onGraphicRender(GdxGraphics g) {
		// Clear
		g.clear(Color.BLACK);

		// Write GameOver and the number of enemy that u killed (1p for enemy,
		// 5p for Boss)
		if (gameOver) {
			// Stop the music
			ambianceMusic.stop();
			g.setColor(Color.WHITE);
			g.clear();
			g.drawStringCentered(height / 2, "GAME OVER");

			// add only one time the score in the array of score if you did a
			// top 10
			if (onlyOne) {
				GameStart.highScore.ranking(score);
				onlyOne = false;
			}
			g.drawStringCentered(height / 2 - 50, "You killed " + score + " enemys");
			// write new high score if you did it
			if (HighScore.newHighScore) {
				g.drawStringCentered(height / 2 - 150, "Congratulation you did a new High Score!!!");
			}
			g.drawStringCentered(height / 2 - 250, "Press space to acces to the menu");

			KeyListener listener = new KeyListener() {

				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyChar() == ' ') {
						gameOver = false;
						new GameCode();

					}
				}

				@Override
				public void keyReleased(KeyEvent arg0) {
				}

				@Override
				public void keyTyped(KeyEvent arg0) {
				}
			};

		}
		// Generate all the things you need to play
		if (!gameOver) {
			// increase the timer
			timer++;
			g.setColor(Color.WHITE);

			// generate stars
			if (timer % 20 == 0) {
				generateStar();
			}

			// Draw score
			g.drawString(width - 100, 100, "Score :" + score);

			// Draw Life(3 pictures or 1 picture and the number of life if you
			// have more than 3 life)
			if (os.getHealth() > 3) {
				g.drawString(30, 100, os.getHealth() + "X");
				g.drawTransformedPicture(100, 100, 0f, height / 80, height / 80, life);
			} else {
				for (int l = 0; l < os.getHealth(); l++) {
					g.drawTransformedPicture(l * 100 + 100, 100, 0f, height / 80, height / 80, life);
				}
			}

			// If there is no Boss increase the BossTimer
			if (boss.size() == 0) {
				bossTimer++;
			}
			// Generate Boss's bullets (without Vx if the boss is happy)
			else if (timer % 20 == 0) {
				if (boss.get(0).s.equals(Boss.State.HAPPY)) {
					generateBullet(boss.get(0).getPosition(), 0, -10, Bullets.State.ENEMY);
				} else {
					int sign = 1;
					if (Math.random() < 0.5) {
						sign = -1;
					}
					generateBullet(boss.get(0).getPosition(), (float) Math.random() * 20 * sign, -10,
							Bullets.State.ENEMY);
				}
			}

			// Generate Boss
			if (bossTimer % 1000 == 0) {
				generateBoss();
				bossTimer++;
			}
			// Actualize the timer of our shield
			if (timer % 10 == 0) {
				os.timerShield--;
			}
			// Actualize the timer of our bullets lvl
			if (timer % 10 == 0) {
				if (!os.getLevel().equals(OurSpaceShip.Level.LEVEL1))
					os.timerMun--;
			}

			// Generate Item
			if (timer % 600 == 0) {
				generateItem();
			}
			// Generate randomly enemy, difficulty increase with nbBoss
			switch (nBoss) {
			case 0:
				if (timer % 50 == 0 && ves.size() < 4) {
					switch (r.nextInt(2)) {
					case 0:
						generateEnemy(EnemySpaceShip.Category.ENEMY1);
						break;
					case 1:
						generateEnemy(EnemySpaceShip.Category.ENEMY2);
						break;
					case 2:
						generateEnemy(EnemySpaceShip.Category.ENEMY3);
						break;
					}
				}
				break;
			case 1:
				if (timer % 40 == 0 && ves.size() < 8) {
					switch (r.nextInt(2)) {
					case 0:
						generateEnemy(EnemySpaceShip.Category.ENEMY1);
						break;
					case 1:
						generateEnemy(EnemySpaceShip.Category.ENEMY2);
						break;
					case 2:
						generateEnemy(EnemySpaceShip.Category.ENEMY3);
						break;
					}
				}
				break;

			case 2:
				if (timer % 30 == 0 && ves.size() < 12) {
					switch (r.nextInt(2)) {
					case 0:
						generateEnemy(EnemySpaceShip.Category.ENEMY1);
						break;
					case 1:
						generateEnemy(EnemySpaceShip.Category.ENEMY2);
						break;
					case 2:
						generateEnemy(EnemySpaceShip.Category.ENEMY3);
						break;
					}
				}
				break;

			default:
				if (timer % 20 == 0 && ves.size() < 16) {
					switch (r.nextInt(2)) {
					case 0:
						generateEnemy(EnemySpaceShip.Category.ENEMY1);
						break;
					case 1:
						generateEnemy(EnemySpaceShip.Category.ENEMY2);
						break;
					case 2:
						generateEnemy(EnemySpaceShip.Category.ENEMY3);
						break;
					}
				}

			}

			// Generate ally bullets
			if (timer % 20 == 0) {
				// Generate ally bullets lvl 1
				if (os.getLevel() == OurSpaceShip.Level.LEVEL1)
					generateBullet(os.getPosition(), 0, 20, Bullets.State.FRIEND);

				// Generate ally bullets lvl 2
				if (os.getLevel() == OurSpaceShip.Level.LEVEL2) {
					generateBullet(os.getPosition(), 0, 20, Bullets.State.FRIEND);
					generateBullet(os.getPosition(), 10, 20, Bullets.State.FRIEND);
					generateBullet(os.getPosition(), -10, 20, Bullets.State.FRIEND);
				}
			}
			// Generate ally bullets lvl 3
			if (os.getLevel() == OurSpaceShip.Level.LEVEL3) {
				if (timer % 10 == 0) {
					if (os.getLevel() == OurSpaceShip.Level.LEVEL3) {
						generateBullet(os.getPosition(), 0, 20, Bullets.State.FRIEND);
						generateBullet(os.getPosition(), 10, 20, Bullets.State.FRIEND);
						generateBullet(os.getPosition(), -10, 20, Bullets.State.FRIEND);

					}
				}
			}
			// Generate an enemy's bullet
			if (timer % 100 == 0) {
				for (int i = 0; i < ves.size(); i++) {
					generateBullet(ves.get(i).getPosition(), 0, -10, Bullets.State.ENEMY);
				}
			}
			// Draw stars
			if (stars.size() > 0) {
				for (int i = 0; i < stars.size(); i++) {
					g.drawTransformedPicture((float) stars.get(i).getPosition().getX(),
							(float) stars.get(i).getPosition().getY(), 0, 40, 40, star);
					stars.get(i).tick();
				}

			}
			// Draw boss
			if (boss.size() > 0) {
				g.drawTransformedPicture((float) boss.get(0).getPosition().getX(),
						(float) boss.get(0).getPosition().getY(), 0, boss.get(0).hitBox, boss.get(0).hitBox, theBoss);
				boss.get(0).ticks();
			}

			// Draw all the EnemyShip
			for (int i = 0; i < ves.size(); i++) {
				switch (ves.get(i).category) {
				case ENEMY1:
					g.drawTransformedPicture((float) ves.get(i).getPosition().getX(),
							(float) ves.get(i).getPosition().getY(), 0, ves.get(i).getHitBox(), ves.get(i).getHitBox(),
							enemy1);
					break;
				case ENEMY2:
					g.drawTransformedPicture((float) ves.get(i).getPosition().getX(),
							(float) ves.get(i).getPosition().getY(), 0, ves.get(i).getHitBox(), ves.get(i).getHitBox(),
							enemy2);
					break;
				case ENEMY3:
					g.drawTransformedPicture((float) ves.get(i).getPosition().getX(),
							(float) ves.get(i).getPosition().getY(), 0, ves.get(i).getHitBox(), ves.get(i).getHitBox(),
							enemy3);
					break;
				}
				ves.get(i).tick();
			}
			// Draw all the bullets
			for (int j = 0; j < bullets.size(); j++) {
				if (bullets.get(j).getState() == State.ENEMY) {
					g.drawTransformedPicture((float) bullets.get(j).getPosition().getX(),
							(float) bullets.get(j).getPosition().getY(), 0, bullets.get(j).getHitBox() / 2,
							bullets.get(j).getHitBox(), enemyBullets);
				} else
					g.drawTransformedPicture((float) bullets.get(j).getPosition().getX(),
							(float) bullets.get(j).getPosition().getY(), 0, bullets.get(j).getHitBox() / 2,
							bullets.get(j).getHitBox(), ourBullets);
				bullets.get(j).tick();
			}
			// Draw Items
			for (int k = 0; k < item.size(); k++) {
				switch (item.get(k).getUtility()) {
				case shield:
					g.drawTransformedPicture((float) item.get(k).getPosition().getX(),
							(float) item.get(k).getPosition().getY(), 0, item.get(k).getHitBox(),
							item.get(k).getHitBox(), shield);
					break;
				case life:
					g.drawTransformedPicture((float) item.get(k).getPosition().getX(),
							(float) item.get(k).getPosition().getY(), 0, item.get(k).getHitBox(),
							item.get(k).getHitBox(), life);
					break;
				case munUpgrade:
					g.drawTransformedPicture((float) item.get(k).getPosition().getX(),
							(float) item.get(k).getPosition().getY(), 0, item.get(k).getHitBox(),
							item.get(k).getHitBox(), munUp);
					break;
				}
				item.get(k).tick();
			}

			// Draw OurSpaceShip
			g.drawTransformedPicture((float) os.getPosition().getX(), (float) os.getPosition().getY(), 180,
					os.getHitBox(), os.getHitBox(), ourShip);

			// Draw a green circle for the shield
			if (os.shield) {
				g.drawTransformedPicture((float) os.getPosition().getX(), (float) os.getPosition().getY(), 0,
						os.getHitBox() + 20, os.getHitBox() + 20, shieldOnShip);
			}
			os.ticks();

			// Call the method tick() from Collision at all refresh
			Collision.tick();
		}
	}
}
