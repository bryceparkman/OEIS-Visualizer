import processing.core.PApplet;
import processing.core.PConstants;

public class Binary {
	PApplet p;
	String[] list;
	float y;
	float x;
	public Binary(PApplet p, String[] list) {
		this.p = p;
		this.list = list;
		x = -1;
		create();
	}
	public void create() {
		p.background(255);
		p.rectMode(PConstants.CORNERS);
		p.fill(0);
		for(int i = 0; i <list.length; i++) {
			list[i] = Long.toBinaryString(Long.parseLong(list[i])); //Convert to list of binary strings
		}
		for (int j = 0; j < list.length; j++) {
			y = p.height;
			x+=Visualize.scale;
			for (int k = list[j].length()-1; k >= 0; k--) {
				if(list[j].substring(k, k+1).equals("1")) {
					p.rect(x, y, x+Visualize.scale, y-Visualize.scale);
				}
				y-=Visualize.scale;
			}
		}
	}
}
