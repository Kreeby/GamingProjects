package Levels;

import Objects.*;
import Entity.*;
import acm.*;
import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRectangle;
//import keyEvents.KeyBoard;
import marioGame.Main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class LevelOne extends GCompound {

	public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public Background bck;
	public Background bck2;
	public Floor floor;
	public static GRect invWall;
	public static GRect invWall1;
	public Coinbox coinbox1;
	public Coinbox coinbox2;
	public Coinbox coinbox3;
	public Coinbox coinbox4;
	
	public ArrayList <Coinbox> coinboxes;
	public ArrayList <Platform> platforms;
	public static ArrayList<Tube> tubes;
	
	public Platform platform1;
	public Platform platform2;
	public Platform platform3;
	
	public Tube tube1;
	public Tube tube2;


	public LevelOne() {

		bck = new Background(screenSize.getWidth(), screenSize.getHeight());
		add(bck, 0, 0);

		bck2 = new Background(screenSize.getWidth(), screenSize.getHeight());
		add(bck2, screenSize.getWidth(), 0);

		floor = new Floor(100);
		add(floor, 0, screenSize.getHeight() - floor.getHeight() * 2.6);
		
		coinbox1 = new Coinbox();
		add(coinbox1, 200, screenSize.getHeight() - floor.getHeight() * 2.6 - 145);
	
		
		coinbox2 = new Coinbox();
		add(coinbox2, 425, screenSize.getHeight() - floor.getHeight() * 2.6 - 145);

		
		coinbox3 = new Coinbox();
		add(coinbox3, 515, screenSize.getHeight() - floor.getHeight() * 2.6 - 145);

		
		coinbox4 = new Coinbox();
		add(coinbox4, 470, screenSize.getHeight() - floor.getHeight() * 2.6 - 280);

		coinboxes = new ArrayList<Coinbox>();
		platforms = new ArrayList<Platform>();
		tubes = new ArrayList<Tube>();
		
		platform1 = new Platform();
		add(platform1, 380, screenSize.getHeight() - floor.getHeight() * 2.6 - 145);

		platform2 = new Platform();
		add(platform2, 470, screenSize.getHeight() - floor.getHeight() * 2.6 - 145);
		
		platform3 = new Platform();
		add(platform3, 560, screenSize.getHeight() - floor.getHeight() * 2.6 - 145);
		
		invWall = new GRect(screenSize.getWidth() / 2, screenSize.getHeight());
		invWall.setVisible(false);
		add(invWall, screenSize.getWidth() / 2.05, 0);
		
		tube1 = new Tube("tube1.png");
		add(tube1, 740, screenSize.getHeight() - floor.getHeight() * 2.6 - 70);
		
		tube2 = new Tube("tube2.png");
		add(tube2, 970, screenSize.getHeight() - floor.getHeight() * 2.6 - 100);
		tube2.setSize(70, 100);
		
		
		invWall1 = new GRect(tube1.getWidth(), tube1.getHeight());
		invWall1.setVisible(true);
		add(invWall1, tube1.getX(), tube1.getY());
		
		coinboxes.add(coinbox1);
		coinboxes.add(coinbox2);
		coinboxes.add(coinbox3);
		coinboxes.add(coinbox4);
		
		platforms.add(platform1);
		platforms.add(platform2);
		platforms.add(platform3);
		
		
		tubes.add(tube1);
		tubes.add(tube2);

	}



	public double getFloorHeight() {
//		System.out.println(floor.getHeight());
		return screenSize.getHeight() - floor.getHeight() * 2.6;
	}
	
	
	
}
