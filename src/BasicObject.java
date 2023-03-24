import java.awt.Graphics2D;
import java.awt.Point;

public class BasicObject extends UMLObject{
	
	public BasicObject(String name, int x, int y, int width, int height){
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		ports = new Port[4];
		drawports = false;
	}

	public void drawInCanvas(Graphics2D g){}

	private void refreshPorts(){}
	//劃一個物件的旁邊四個點
	protected void drawPorts(Graphics2D g){
		ports[0].draw(g);
		ports[1].draw(g);
		ports[2].draw(g);
		ports[3].draw(g);
	}

	public boolean isSelected(int x, int y){ 
		return ( x >= this.x && y >= this.y && x < this.x + this.width && y < this.y + this.height);
	}
	
	//判斷矩形是否被包含在指定的起點和終點所形成的範圍內的方法
	public boolean isContained(Point start_point, Point end_point){
		int[][] vertices = {{x, y}, {x+width, y}, {x, y+height}, {x+width, y+height}};
		boolean result = true;
		for(int i = 0; i < vertices.length; i++){
			if(!pointIsContained(start_point, end_point, vertices[i][0], vertices[i][1])){
				result = false;
			}
		}
		return result;
	}
	
	
	private boolean pointIsContained(Point start_point, Point end_point, int x, int y){
		if((start_point.x <= x && x <= end_point.x) || (start_point.x >= x && x >= end_point.x)){
			if((start_point.y <= y && y <= end_point.y) || (start_point.y >= y && y >= end_point.y)){
				return true;
			}
		}
		return false;
	}
}
