package Objects;

import acm.graphics.GCompound;
import acm.graphics.GImage;
public class Tube extends GCompound{
	
	private GImage tube;
	
	public Tube(String str) {
		tube = new GImage(str);
		add(tube);
		tube.setSize(70,70);
	}
	
	
	public void setSize(double width, double height) {
		tube.setSize(width, height);
	}
	
}
