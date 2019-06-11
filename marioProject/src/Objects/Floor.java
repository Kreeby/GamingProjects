package Objects;

import acm.*;
import acm.graphics.GCompound;
import acm.graphics.GImage;

public class Floor extends GCompound {

	private GImage[] floor_bricks;

	public Floor(int num) {
		createFloor(num);
		// System.out.println(width + "" + height);
	}

	private void createFloor(int num) {
		floor_bricks = new GImage[100];
		for (int i = 0; i < num; i++) {
			floor_bricks[i] = new GImage("floor.png");
			floor_bricks[i].setSize(55, 55);
			add(floor_bricks[i], i * 55, 0);
		}
	}
}
