package game;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import com.badlogic.gdx.graphics.Color;

import ch.hevs.gdx2d.components.bitmaps.BitmapImage;
import ch.hevs.gdx2d.desktop.PortableApplication;
import ch.hevs.gdx2d.lib.GdxGraphics;
import objects.Boss;
import objects.Bullets;
import objects.Bullets.State;
import objects.EnemySpaceShip;
import objects.EnemySpaceShip.Category;
import objects.Items;
import objects.OurSpaceShip;

/**
 * @author Philippine Favre et Marc Salamin
 * Main class. We use gdx2D
 *
 */
public class GameCode extends PortableApplication {
	
	//Recovery screen data
	static Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	public static float height = (float) dimension.getHeight();
	public static float width  = (float) dimension.getWidth()/2;
	
	//Class attributes
	static ArrayList<Bullets> bullets = new ArrayList<Bullets>();
	public static ArrayList <EnemySpaceShip> ves = new ArrayList <EnemySpaceShip>();
	public static ArrayList<Items> item = new ArrayList<Items>();
	public static ArrayList<Boss> boss = new ArrayList<Boss>();
	public static OurSpaceShip os = new OurSpaceShip(new Point((int)width/2, (int)height/4), (float) 0, (float)0, width/40, 3, OurSpaceShip.Level.LEVEL1);
	static int timer;
	static int bossTimer;
	static int score = 0;
	static int nBoss;
	static boolean gameOver = false;
	
	//Pictures
	BitmapImage theBoss;
	BitmapImage enemy1, enemy2, enemy3;
	BitmapImage ourShip;
	BitmapImage enemyBullets, ourBullets;
	BitmapImage shield, life, munUp;
	BitmapImage shieldOnShip;
	BitmapImage backGround;
	
	//Constructor
	public GameCode(){
		super((int)width,(int) height);
	}
	
	//Create a random variable who stay fix 
	Random r = new Random(124345);
	
	//Method to generate EnemyShip
	public void generateEnemy(EnemySpaceShip.Category e){
		EnemySpaceShip es = new EnemySpaceShip(new Point(r.nextInt((int) width), (int) (9 * height / 10)), (float) 0,
				2f, width / 40, 1, e);
		ves.add(es);
	}
	
	//Method to generate Bullets
	public void generateBullet(Point p, float Vx, float Vy , State s){
		Bullets b = new Bullets(p, Vx, Vy, s);
		this.bullets.add(b);
	}
	//Method to generate Boss
	public void generateBoss(){
		Boss b = new Boss(new Point((int)width/2,(int)(height-(height/6))),5f,width / 20,++nBoss);
		boss.add(b);
	}
	
	//Method to generate Item depending the level of our spaceShip
	public void generateItem(){
		Point p = new Point(r.nextInt((int) width), (int) (9 * height / 10));
		float Vx = 0;
		float Vy = 5f;
		float hitBox = height/80;
		Items mun;
		// switch to choose randomly witch bonus is going to give this item
		switch (r.nextInt(3)){
		case 0:
			Items life = new Items(p,Vx,Vy,hitBox,Items.Utility.life);
			this.item.add(life);
			break;
		case 1:
			if(os.getLevel()!= OurSpaceShip.Level.LEVEL3){
				Items.Utility u = Items.Utility.munUpgrade;
				mun = new Items(p,Vx,Vy,hitBox,u);
			}
			else {
				Items.Utility u = Items.Utility.life;
				mun = new Items(p,Vx,Vy,hitBox,u);
				}
			
			this.item.add(mun);
			break;
		case 2:
			Items shield = new Items(p,Vx,Vy,GameCode.height/80,Items.Utility.shield);
			this.item.add(shield);
			break;
		}	
	}
	
	@Override
	// Method to initials
	public void onInit() {
		//Import image
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
		backGround = new BitmapImage("blue.png");
		
		HighScore.newHighScore = false;
		nBoss = 0;
	}

	@Override
	//Method to detect if a key is pressed
	public void onKeyDown(int keycode) {
		super.onKeyDown(keycode);
		os.onKeyDown(keycode);
		
	}
	//Method to detect if a key is released
	public void onKeyUp(int keycode) {
		os.onKeyUp(keycode);
	}
	//Method to stop the game
	public static void lost(){
		gameOver = true;
		
	}
	
	@Override
	// Graph and timer to generate all
	public void onGraphicRender(GdxGraphics g) {
		
		//Write GameOver and the number of enemy that u killed (1p for enemy, 5p for Boss)
		if(gameOver){
			g.setColor(Color.WHITE);
			g.clear();
			g.drawStringCentered(height/2, "GAME OVER");
			HighScore.ranking(score);
			g.drawStringCentered(height/2-50,"You killed "+ score + " enemys");
			if(HighScore.newHighScore){
			g.drawStringCentered(height/2-100, "Congratulation you did a new high Score !!!");
			}
		}
		else{
			g.setColor(Color.BLACK);
			//Draw life
			if(os.getHealth()> 3){
				g.drawString(30, 100, "Life : "+os.getHealth()+" <3");
			}
			else if(os.getHealth()==3){
				g.drawString(30, 100, "Life : <3 <3 <3");
				
			}
			else if(os.getHealth()==2){
				g.drawString(30, 100, "Life : <3 <3");
				
			}
			else if(os.getHealth()==1){
				g.drawString(30, 100, "Life : <3");
				
			}
			
			
			//Draw score
			g.drawString(width-100, 100, "Score :"+score);
		}
		//If there is no Boss increase the BossTimer
		if(boss.size()==0){
			bossTimer++;
		}
		//Generate Boss's bullets 
		else if(timer%20==0){
			if(boss.get(0).s.equals(Boss.State.HAPPY)){
				generateBullet(boss.get(0).getPosition(), 0, -10, Bullets.State.ENEMY);
			}else generateBullet(boss.get(0).getPosition(),(float) Math.random()*20, -10, Bullets.State.ENEMY);
		}	
		timer++;

		//Clear
		g.clear(Color.WHITE);
		
		g.drawTransformedPicture(width/2, height/2, 0, width, height, backGround);
		
		//Generate Boss
		if(bossTimer%1000 == 0){
			generateBoss();
			bossTimer++;
		}
		//Actualyse the timer of our shield
		if(timer% 10 ==0){
			os.timerShield--;
		}
		//Actualyse the timer of our bullets lvl
		if(timer%10 ==0){
			if(!os.getLevel().equals(OurSpaceShip.Level.LEVEL1))
			os.timerMun--;
		}
		
		//Generate Item
		if(timer%500 == 0){
			generateItem();
		}
		//Generate randomly enemy
		if(timer%50 == 0 && ves.size() < 4){
			switch(r.nextInt(2)){
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
		//Generate ally bullets lvl 1
		if(timer%20 == 0){
				if(os.getLevel() == OurSpaceShip.Level.LEVEL1)
					generateBullet(os.getPosition(),0,20,Bullets.State.FRIEND);
				
		//Generate ally bullets lvl 2
				if(os.getLevel() == OurSpaceShip.Level.LEVEL2){
					generateBullet(os.getPosition(),0,20,Bullets.State.FRIEND);
					generateBullet(os.getPosition(),10,20,Bullets.State.FRIEND);
					generateBullet(os.getPosition(),-10,20,Bullets.State.FRIEND);
				}
		}
		//Generate ally bullets lvl 3
		if(os.getLevel() == OurSpaceShip.Level.LEVEL3){
			if(timer%10==0){
				if(os.getLevel() == OurSpaceShip.Level.LEVEL3){
					generateBullet(os.getPosition(),0,20,Bullets.State.FRIEND);
					generateBullet(os.getPosition(),10,20,Bullets.State.FRIEND);
					generateBullet(os.getPosition(),-10,20,Bullets.State.FRIEND);
					
				}
			}
		}
		//Generate an enemy's bullet
		if(timer%100 == 0){
			for(int i = 0; i< ves.size(); i++){
				generateBullet(ves.get(i).getPosition(),0,-10,Bullets.State.ENEMY);
			}
		}
		// Draw boss
		if(boss.size()>0){
			g.drawTransformedPicture((float)boss.get(0).getPosition().getX(),(float)boss.get(0).getPosition().getY(), 0, boss.get(0).hitBox, boss.get(0).hitBox, theBoss );
			boss.get(0).ticks();
		}
		
		//Draw all the EnemyShip
		for(int i = 0; i< ves.size(); i++){
			switch(ves.get(i).category){
			case ENEMY1:
				g.drawTransformedPicture((float)ves.get(i).getPosition().getX(),(float)ves.get(i).getPosition().getY(), 0, ves.get(i).getHitBox(), ves.get(i).getHitBox(), enemy1);
				break;
			case ENEMY2:
				g.drawTransformedPicture((float)ves.get(i).getPosition().getX(),(float)ves.get(i).getPosition().getY(), 0, ves.get(i).getHitBox(), ves.get(i).getHitBox(), enemy2);
				break;
			case ENEMY3:
				g.drawTransformedPicture((float)ves.get(i).getPosition().getX(),(float)ves.get(i).getPosition().getY(), 0, ves.get(i).getHitBox(), ves.get(i).getHitBox(), enemy3);
				break;
			}
			ves.get(i).tick();
		}
		//Draw all the bullets
		for(int j = 0; j< bullets.size();j++){
			if(bullets.get(j).getState()== State.ENEMY){
				g.drawTransformedPicture((float)bullets.get(j).getPosition().getX(),(float)bullets.get(j).getPosition().getY(), 0, bullets.get(j).getHitBox()/2, bullets.get(j).getHitBox(), enemyBullets);
			}else
				g.drawTransformedPicture((float)bullets.get(j).getPosition().getX(),(float)bullets.get(j).getPosition().getY(), 0, bullets.get(j).getHitBox()/2, bullets.get(j).getHitBox(), ourBullets);
			bullets.get(j).tick();
		}
		// Draw Items
		for(int k = 0; k< item.size();k++){
			switch(item.get(k).getUtility()){
			case shield:
				g.drawTransformedPicture((float)item.get(k).getPosition().getX(),(float)item.get(k).getPosition().getY(), 0, item.get(k).getHitBox(), item.get(k).getHitBox(), shield);
				break;
			case life:
				g.drawTransformedPicture((float)item.get(k).getPosition().getX(),(float)item.get(k).getPosition().getY(), 0, item.get(k).getHitBox(), item.get(k).getHitBox(), life);
				break;
			case munUpgrade:
				g.drawTransformedPicture((float)item.get(k).getPosition().getX(),(float)item.get(k).getPosition().getY(), 0, item.get(k).getHitBox(), item.get(k).getHitBox(), munUp);
				break;
			}
			item.get(k).tick();
		}
		
		//Draw OurSpaceShip
		g.drawTransformedPicture((float)os.getPosition().getX(),(float) os.getPosition().getY(), 180, os.getHitBox(), os.getHitBox(), ourShip);
				
		//Draw a green circle for the shield
		if(os.shield){
			g.drawTransformedPicture((float)os.getPosition().getX(),(float) os.getPosition().getY(), 0, os.getHitBox()+20, os.getHitBox()+20, shieldOnShip);
		}os.ticks();
		
		//Call the method tick() from Collision at all refresh
		Collision.tick();
			}	
	
	
	//Main method to launch the game
	public static void main(String[] args) {
//		new GameMenu();
		new GameCode();
	}
	
	

}
