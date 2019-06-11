package Entity;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.util.ArrayList;

import Levels.LevelOne;
import Objects.Coinbox;
import Objects.Tube;
import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.graphics.GRectangle;
import marioGame.World;
//import keyEvents.*;

/*
 * 
 * Mario class which is about the object
 * that is called Mario and is main charachter
 * of the game. 
 * 
 * 
 */

public class Mario extends Player {

	public boolean isOnAir = true;
	public boolean isCollided = false;
	public boolean isFalling = true;
	public boolean isJumping = false;
	private boolean isStoppedR = false;
	private boolean flagMore = false;
	private boolean isOnCenter = false;
	private double dx;
	public static GImage mario;
	public boolean moveRight = false;
	public boolean moveLeft = false;
	public boolean isMoving;
	private int counter = 0;
	private int flag = 0;
	public double counterMove = 0;
	public double vxR = 0.006;
	public double vxL = 0.006;
	public double vy = 0;
	public double dvx = 0;
	public double dy = 0;
	public double y;
	private double time = 0;
	public double counterJump = 4;
	public boolean keyPressed = false;
	public double limit = 0;
	public static boolean isWorldMoving = false;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public Mario() {
		mario = new GImage("mario_standing.png");
		add(mario);
		mario.setSize(60, 60);

	}

	public boolean collisionWall() {
		if (getBounds().intersects(LevelOne.invWall.getBounds()) && moveLeft == false) {
			if (flag == 0)
				dvx = vxR;
			flag = 1;
			vxR = 0.006;
			return true;
		}
		return false;
	}

	public boolean isNotCenter() {
		if (collisionWall() == false)
			return true;
		return false;
	}

	public void gravitation() {

		while (true) {
			y = mario.getY();

			if (mario.getY() <= limit && isJumping != true) {
				mario.setLocation(mario.getX(), mario.getY() + 10);
				isOnAir = true;

			}

			if (isJumping == true) {
				isOnAir = true;
				counterJump += 0.05;
				mario.setLocation(mario.getX(),
						(mario.getY() + (int) ((Math.sin(counterJump) + Math.cos(counterJump)) * 6)));

				if (counterJump >= 20) {
					isJumping = false;
					counterJump = 4;

				}

				if (moveRight == true && isNotCenter()) {
					moveRight();
				}

				// if(moveRight == )

				if (moveLeft == true) {
					moveLeft();
				}
			}
			if (mario.getY() >= limit) {
				mario.setLocation(mario.getX(), limit);
				isJumping = false;
				isOnAir = false;
			}

			repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void changePicR() {
		counter++;
		if (counter == 0) {
			mario.setImage("mario_r_1.png");
			this.mario.setSize(60, 60);
		}

		if (counter == 10) {
			mario.setImage("mario_r_2.png");
			mario.setSize(60, 60);
		}

		if (counter == 20) {
			mario.setImage("mario_r_3.png");
			mario.setSize(60, 60);
		}
		if (counter > 21) {
			counter = 0;
		}
	}

	public void moveRight() {
		update();
		changePicR();
		dx += vxR;
		isMoving = true;
		moveRight = true;
		moveLeft = false;

		/* SMOOTH ANIMATION OF THE MOVEMENT */
		Runnable m1 = new Runnable() {

			@Override
			public void run() {

				mario.move(vxR, 0);
				if (vxR > 6) {
					vxR = 6;
					dvx = 6;
					mario.move(vxR, 0);
				}
				pause(15);

			}

		};

		vxR += 0.4;
		dvx += 0.4;

		Thread t1 = new Thread(m1);

		t1.start();

	}

	public void changePicL() {
		counter++;
		if (counter == 0) {
			mario.setImage("mario_l_1.png");
			this.mario.setSize(60, 60);
		}

		if (counter == 10) {
			mario.setImage("mario_l_2.png");
			mario.setSize(60, 60);
		}

		if (counter == 20) {
			mario.setImage("mario_l_3.png");
			mario.setSize(60, 60);
		}

		if (counter > 21) {
			counter = 0;
		}
	}

	public void moveLeft() {

		update();
		moveLeft = true;
		isMoving = true;
		changePicL();
		Runnable m4 = new Runnable() {

			@Override
			public void run() {
				if (moveLeft == true) {
					mario.move(vxL, 0);
					if (vxL < -6) {
						vxL = -6;
						dvx = 6;
						mario.move(vxL, 0);
					}
					pause(15);

				}

			}

		};

		vxL -= 0.4;
		dvx += 0.4;

		Thread t4 = new Thread(m4);

		t4.start();

	}

	@Override
	public boolean die() {
		return true;

	}

	public void setImage(String str) {
		mario.setImage(str);
		mario.setSize(60, 60);
	}

	public void update() {
		if (moveRight == false && isStoppedR == false) {

			/* SMOOTH ANIMATION OF THE STOPPING */
			Runnable m2 = new Runnable() {

				@Override
				public void run() {
					while (vxR > 0 && isNotCenter() == true) {
						
						vxR -= 0.2;
						dvx -= 0.2;
						if (isNotCenter() == true) {
							mario.move(vxR, 0);
							pause(15);
						}

					}
					if (vxR <= 0.006 && flagMore == false) {
						dvx = 0.006;
						vxR = 0.006;
						isStoppedR = !isStoppedR;
						flagMore = !flagMore;
						mario.setImage("mario_standing.png");
						mario.setSize(60, 60);
					}

				}
			};
			Thread t2 = new Thread(m2);
			t2.start();

		}
		if (isStoppedR == true && moveLeft == false) {

			Runnable m3 = new Runnable() {

				@Override
				public void run() {
					while (vxL < 0) {
						vxL += 0.2;
						mario.move(vxL, 0);
						pause(15);
						// dvx -= 0.2;

						// if (moveLeft == true) {
						// vxR -= 0.1;
						// }
					}
					if (vxL >= 0 && flagMore) {
						// dvx = 0.006;

						isStoppedR = !isStoppedR;
						vxL = 0.006;
						flagMore = !flagMore;
						mario.setImage("mario_standing_l.png");
						mario.setSize(60, 60);
						counter = 0;

					}
				}
			};
			Thread t3 = new Thread(m3);
			t3.start();

		}
	}

	public double getX() {
		return dx;
	}

	public double mario_getY() {
		return y;
	}

	public void setLimit(double limit) {
		this.limit = limit;
	}

	public boolean isOnCoinBox(ArrayList<Coinbox> coinboxes) {
		for (int i = 0; i < coinboxes.size(); i++) {
			System.out.println(getBounds().intersects(coinboxes.get(i).getBounds()));
			if (mario.getBounds().intersects(coinboxes.get(i).getBounds())) {
				isJumping = false;
				System.out.println(limit +" MY LIMIT" ) ;
				return true;
			}
		}
		return false;
	}

}
