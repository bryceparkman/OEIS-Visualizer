import processing.core.PApplet;
import processing.core.PConstants;

public class NumberLine {
	PApplet p;
	String[] list;
	public NumberLine(PApplet p,String[] list) {
		this.p = p;
		this.list = list;
		create();
	}
	public void create() {
		long current;
		long next;
		long[] seq = new long[list.length];
		boolean flip = true;
		
		p.noFill();
		p.ellipseMode(PConstants.CENTER);
		
		for(int i = 0;i<list.length;i++) {
			seq[i] = Long.parseLong(list[i]); //Convert to list of longs
		}
		p.stroke(0); //Black
		p.line(0, p.height/2, p.width, p.height/2); //Draws number line
		
		for(int k=1;k<seq.length;k++) {
			current = seq[k-1];
			next = seq[k];
			if(current < next) { //If jump is forwards jump
				p.stroke(0,0,255); //Blue
				if(flip) { //Flips hop path every time for more of an aesthetic look
					p.arc((float)(Visualize.scale*(next+current)/2.0),(float) p.height/2, (float)(Visualize.scale*Math.abs((next-current))), (float)(Visualize.scale*Math.abs((next-current))), -(float) Math.PI, 0.0f);
				} //^ Path above number line
				else {
					p.arc((float)(Visualize.scale*(next+current)/2.0),(float) p.height/2, (float)(Visualize.scale*Math.abs((next-current))), (float)(Visualize.scale*Math.abs((next-current))), 0.0f, (float) Math.PI);
				} //^ Path below number line
			}
			else if(current > next) { //If jump is backwards jump
				p.stroke(255,165,0); //Orange
				if(flip) {
					p.arc((float)(Visualize.scale*(next+current)/2.0),(float) p.height/2, (float)(Visualize.scale*Math.abs((next-current))), (float)(Visualize.scale*Math.abs((next-current))), -(float) Math.PI, 0.0f);
				} //^ Path above number line
				else {
					p.arc((float)(Visualize.scale*(next+current)/2.0),(float) p.height/2, (float)(Visualize.scale*Math.abs((next-current))), (float)(Visualize.scale*Math.abs((next-current))), 0.0f, (float) Math.PI);
				} //^ Path below number line		
			}
			flip = !flip; //Flip side
		}
	}

}
