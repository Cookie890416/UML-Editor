import java.awt.Graphics2D;
import java.awt.Point;

public class UMLObject {
	protected int width, height;//�N��y��(X,Y)�B�ewidth�B��height;
	protected String name;//UML Object���W��
	protected Port[] ports;
	protected boolean drawports;
	protected int depth;//UML Object���`��
	protected int x, y;
	
	public void setHeight(int value){this.height = value;}
	public int getHeight(){return this.height;}
	
	public void setWidth(int value){this.width = value;}
	public int getWidth(){return this.width;}
	
	public Port[] getPorts(){return this.ports;}
	
	public void setX(int value){this.x = value;}
	public int getX(){return this.x;}
	
	public void setY(int value){this.y = value;}
	public int getY(){return this.y;}
	
	public void setName(String value){this.name = value;}
	public String getName(){return this.name;}
	
	public void setDepth(int value){this.depth = value;}
	public int getDepth(){ return this.depth;}

	public void setDrawPortsFlag(boolean value){this.drawports = value;}
	
	public void drawInCanvas(Graphics2D g2) {}

	public boolean isSelected(int x, int y) {return false;}
	public boolean isContained(Point start_point, Point end_point){return false;}
	public void move(int x_offset, int y_offset){
		this.x += x_offset;
		this.y += y_offset;
	}

	protected int calculateDistance(Port start_point, Port end_point){
		int x_distance = start_point.getX() - end_point.getX();
		int y_distance = start_point.getY() - end_point.getY();
		return (int)Math.sqrt(Math.pow(x_distance, 2) + Math.pow(y_distance, 2));
	}
	
	public Point getNearestPort(Point point){
		Port port = new Port(point);
		int distance = Integer.MAX_VALUE;
		Port nearest_port = null;
		//利用迴圈去找和point最近的port
		for(int i = 0; i < ports.length; i++){
			int new_distance = calculateDistance(ports[i], port);
			if(distance>new_distance){
				distance = new_distance;
				nearest_port = ports[i];
			}
		}
		//創建一個新的 Point 物件 p，其座標值為 nearest_port 的座標值，並將其回傳
		Point p = new Point(nearest_port.getX(), nearest_port.getY()); 
		return p;
	}
	
}
