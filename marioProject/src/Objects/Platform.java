package Objects;

import acm.graphics.GCompound;
import acm.graphics.GImage;

public class Platform extends GCompound {
	
	public GImage platform;
	
	public Platform() {
		
		platform = new GImage("floor_brick.png");
		add(platform);
		platform.setSize(45, 45);
	}
	
	
	
	
}
