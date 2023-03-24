import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class Port {
	private Point point;
	
	public Port(Point p){
		point = p;
	}
	
	//設定連線中的兩個點,每一個點都是正方形,然後長度和寬度都是6
	public void draw(Graphics2D g2){
		g2.setColor(Color.black);
		g2.fill(new Rectangle.Double(point.x, point.y, 6.0, 6.0));
	}
	
	public int getX(){return this.point.x;}
	public int getY(){return this.point.y;}
}
