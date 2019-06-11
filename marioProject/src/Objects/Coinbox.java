package Objects;

import acm.graphics.GCompound;
import acm.graphics.GImage;

public class Coinbox extends GCompound{
	
	GImage coinbox;
	private int count;
	
	public Coinbox() {
		coinbox = new GImage("coin_box.gif");
		add(coinbox);
		coinbox.setSize(45,45);
	}
	
	
	
	
	
	
	
	

}
