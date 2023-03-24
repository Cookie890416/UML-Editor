import java.awt.Graphics2D;

public class CompositionLine extends ConnectionLine{

	public CompositionLine(String name, UMLObject start_object, UMLObject end_object) {
		super(name, start_object, end_object);
		// TODO Auto-generated constructor stub
	}
	
	public void drawInCanvas(Graphics2D g){
		int x,x1,x2;
		int y,y1,y2;
		
		//去算說兩個物件中要用哪兩個點連結物件的距離可以最短
		refreshConnectionPoint();
		
		//Composition Line
		x = (int)((ports[1].getX() * 0.87 + ports[0].getX()*0.12));
		y = (int)((ports[1].getY() * 0.87 + ports[0].getY()*0.12));
		
		x1 = (int)((x+ports[1].getX())/2 + (ports[1].getY()-y)/(Math.sqrt(3)*2));
		y1 = (int)((y+ports[1].getY())/2 - (ports[1].getX()-x)/(Math.sqrt(3)*2));
			
		x2 = (int)((x+ports[1].getX())/2 - (ports[1].getY()-y)/(Math.sqrt(3)*2));
		y2 = (int)((y+ports[1].getY())/2 + (ports[1].getX()-x)/(Math.sqrt(3)*2));
		
		g.drawLine(ports[1].getX(), ports[1].getY(), x1, y1);
		g.drawLine(ports[1].getX(), ports[1].getY(), x2, y2);
		g.drawLine(x1, y1, x, y);
		g.drawLine(x2, y2, x, y);
		g.drawLine(x, y, ports[0].getX(), ports[0].getY());
		
		
		ports[0].draw(g);
		ports[1].draw(g);
	}
}
