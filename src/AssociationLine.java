import java.awt.Graphics2D;

public class AssociationLine extends ConnectionLine{

	public AssociationLine(String name, UMLObject start_object, UMLObject end_object) {
		super(name, start_object, end_object);
		// TODO Auto-generated constructor stub
	}
	
	public void drawInCanvas(Graphics2D g){
		//去計算兩個物件中最相近的兩個port
		refreshConnectionPoint();
				
		//draw Association Line
		g.drawLine(ports[0].getX(), ports[0].getY(), ports[1].getX(), ports[1].getY());
				
		//draw each port
		ports[0].draw(g);
		ports[1].draw(g);
	}
}
