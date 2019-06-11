package Objects;

import acm.graphics.GCompound;
import acm.graphics.GImage;

public class Background extends GCompound{
	
	private GImage bck;
	
	public Background(double width, double height) {
		
		bck = new GImage("background.jpg");
		bck.setSize(width, height - 55);
		add(bck);
	}
}
