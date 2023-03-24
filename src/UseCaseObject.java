import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class UseCaseObject extends BasicObject{
	public UseCaseObject(String name, int x, int y) {
		super(name, x, y, 100, 75);
		// TODO Auto-generated constructor stub
	}
	
	private void refreshPorts(){
		ports[0] = new Port(new Point((int)(x + width * 0.5 - 3.0), (int)(y - 3.0)));
		ports[1] = new Port(new Point((int)(x - 3.0), (int)(y + height * 0.5 - 3.0)));
		ports[2] = new Port(new Point((int)(x + width - 3.0), (int)(y + height * 0.5 - 3.0)));
		ports[3] = new Port(new Point((int)(x + width * 0.5 - 3.0), (int)(y + height - 3.0)));
	}
	
	public void drawInCanvas(Graphics2D g){
		//Class Object
		Ellipse2D ellipse;
		ellipse = new Ellipse2D.Double(x, y, width, height);
		g.draw(ellipse);
		g.drawString(getName(), (int)(x+(width-getName().length()*7)*0.5), (int)(y+(height*0.5)));
		
		refreshPorts();
		if(drawports){
			drawPorts(g);
		}
	}
}
