package marioGame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import Entity.Mario;
import Levels.LevelOne;
import acm.*;
import acm.program.GraphicsProgram;

public class Main extends GraphicsProgram {

	public boolean isOnCenter = false;
	public boolean isMoving = false;
	public static boolean keyRight = false;
	public boolean isKeyPressed = false;
	private static final long serialVersionUID = 1L;

	// static Dimension appSize = Toolkit.getDefaultToolkit().getScreenSize();

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public double counter;
	LevelOne lvl1;
	Mario mario;
	World world;

	public static void main(String[] args) {

		new Main().start();
	}

	public void run() {

		world = new World();
		add(world);

		addKeyListeners();

		mario = new Mario();
		add(mario, 0, world.floor_height - mario.getHeight() + 4);

		// mario.y = mario.getY();

		mario.gravitation();
	}

	public void keyPressed(KeyEvent e) {
		isKeyPressed = true;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			mario.moveRight = true;
//			if (!mario.collideTube()) {
				if (mario.isNotCenter()) {
					mario.moveRight();

				} else {
					mario.changePicR();
					world.moveElements(isKeyPressed, 6, false);
					mario.moveRight = false;
				}

//			}
			break;

		case KeyEvent.VK_LEFT:
//			if (!mario.collideTube()) {
				mario.moveLeft();
//			}

			break;

		case KeyEvent.VK_SPACE:
			if (mario.mario_getY() == mario.limit) {
				if (mario.isNotCenter() == true) {
					mario.isJumping = true;
					if(mario.isOnCoinBox(world.coinboxes) == true) {
						System.out.println("ASASASAS");
						mario.setLimit(-400);
					}
					else { 
						mario.setLimit(0);;
					}
					
				}

				if (mario.isNotCenter() == false) {

					mario.isJumping = true;
					Runnable run = new Runnable() {

						@Override
						public void run() {
							while (mario.isJumping == true) {
								world.moveElements(isKeyPressed, 6, mario.isJumping);
								try {
									Thread.sleep(60);
								} catch (Exception e) {
									// TODO: handle exception
								}
							}
						}

					};

					Thread thread = new Thread(run);
					thread.start();
				}

			} else {
				mario.isJumping = false;
			}

		}

	}

	public void keyReleased(KeyEvent e) {
		isKeyPressed = false;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			mario.moveRight = false;
			world.isWorldMoving = false;
			if (mario.isNotCenter()) {
				mario.update();
			} else {
//				if (!mario.collideTube()) {
					mario.update();
					world.update(6);
//				}
			}

			break;
		case KeyEvent.VK_LEFT:
			mario.moveLeft = false;
//			if (!mario.collideTube()) {

				mario.update();
//			}
			break;
		case KeyEvent.VK_SPACE:
			mario.counterJump = 4;
			mario.isJumping = false;
			mario.dvx = 0;
		}
	}

	public boolean isKeyPressed() {
		return isKeyPressed;
	}

}
