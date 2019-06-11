package marioGame;

import acm.graphics.GCompound;
import acm.program.GraphicsProgram;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import Entity.Mario;
import Levels.*;
import Objects.Coinbox;
import Objects.Tube;

public class World extends GCompound {

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	LevelOne lvl1;
	public double floor_height;
	public boolean isWorldMoving = false;

	public double vy = 10;
	private double vx = 0;
	public double counter2 = 4;
	private int flag = 0;
	public boolean isJumped;

	public ArrayList<Coinbox> coinboxes;
	
	public World() {

		lvl1 = new LevelOne();
		add(lvl1);

		floor_height = lvl1.getFloorHeight();

		
		this.coinboxes = lvl1.coinboxes;
		
		System.out.println(coinboxes);
		
	}

	public void moveElements(boolean pressed, double dvx, boolean isJumping) {
		if (pressed == true) {

			if (flag != 1) {
				this.vx = dvx;
			}

			if (vx != 0.006 && flag == 0) {
				lvl1.bck.move(-vx * 2, 0);
				lvl1.floor.move(-vx * 2, 0);
				lvl1.bck2.move(-vx * 2, 0);

				for (int i = 0; i < lvl1.coinboxes.size(); i++) {
					lvl1.coinboxes.get(i).move(-vx * 2, 0);
					
				}

				for (int i = 0; i < lvl1.platforms.size(); i++) {
					lvl1.platforms.get(i).move(-vx * 2, 0);
				}
				for (int i = 0; i < lvl1.tubes.size(); i++) {
					lvl1.tubes.get(i).move(-vx * 2  , 0);
				}
			} else {
				flag = 1;
				vx += 0.2;
				if (vx >= 12) {
					vx = 12;
				}
				lvl1.bck.move(-vx, 0);
				lvl1.floor.move(-vx, 0);
				lvl1.bck2.move(-vx, 0);
				for (int i = 0; i < lvl1.coinboxes.size(); i++) {
					lvl1.coinboxes.get(i).move(-vx, 0);
				}

				for (int i = 0; i < lvl1.platforms.size(); i++) {
					lvl1.platforms.get(i).move(-vx, 0);
				}
				
				for (int i = 0; i < lvl1.tubes.size(); i++) {
					lvl1.tubes.get(i).move(-vx , 0);
				}
			}

			if (lvl1.bck.getX() <= -screenSize.getWidth()) {
				lvl1.bck.setLocation(0, 0);
				System.out.println("Birinci");
			}

			if (lvl1.bck2.getX() <= 0) {
				lvl1.bck2.setLocation(lvl1.bck2.getWidth(), 0);
				System.out.println("Ikinci");
			}
			isWorldMoving = true;
		}
	}

	public void update(int x) {
		
			
			this.vx = x;
			Runnable m2 = new Runnable() {

				@Override
				public void run() {
					while (vx > 0) {

						lvl1.bck.move(-vx, 0);
						lvl1.bck2.move(-vx, 0);
						lvl1.floor.move(-vx, 0);
						for (int i = 0; i < lvl1.coinboxes.size(); i++) {
							lvl1.coinboxes.get(i).move(-vx , 0);
						}

						for (int i = 0; i < lvl1.platforms.size(); i++) {
							lvl1.platforms.get(i).move(-vx , 0);
						}
						
						for (int i = 0; i < lvl1.tubes.size(); i++) {
							lvl1.tubes.get(i).move(-vx , 0);
						}
						vx -= 0.4;
						pause(30);
						if (lvl1.bck.getX() <= -screenSize.getWidth()) {
							lvl1.bck.setLocation(0, 0);
							System.out.println("Birinci");
						}

						if (lvl1.bck2.getX() <= 0) {
							lvl1.bck2.setLocation(lvl1.bck2.getWidth(), 0);
							System.out.println("Ikinci");
						}
					}

					if (vx < 0) {
						vx = 0;
					}
				}
			};
			Thread t2 = new Thread(m2);
			t2.start();
		
	}

	

}
