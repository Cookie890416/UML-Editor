import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class CompositeObject extends UMLObject{
	private HashMap<String, UMLObject> members = new HashMap<String, UMLObject>();
	
	public CompositeObject(String name, HashMap<String, UMLObject> members){
		ports = new Port[4];
		setName(name);
		this.members = members;
		this.x = Integer.MAX_VALUE;
		this.y = Integer.MAX_VALUE;
		this.width = Integer.MIN_VALUE;
		this.height = Integer.MIN_VALUE;
		
		refreshStartVertex();
		refreshWidthAndHeight();
	}
	
	public void setHeight(int value){this.height = value;}
	public int getHeight(){return this.height;}
	public HashMap<String, UMLObject> getAllMembers(){ return this.members;}
	
	public void drawInCanvas(Graphics2D g){
		// 
		for(Map.Entry<String, UMLObject> entry: members.entrySet())
		{
			entry.getValue().drawInCanvas(g);
		}
		
		refreshPorts();
		if(drawports){
			drawPorts(g);
		}
	}
	
	private void refreshStartVertex(){
		for(Map.Entry<String, UMLObject> entry: members.entrySet()){
			if(this.x > entry.getValue().getX()){
				this.x = entry.getValue().getX();
			}
			if(this.y > entry.getValue().getY()){
				this.y = entry.getValue().getY();
			}
		}
	}
	
	//更新group物件的寬度和高度
	private void refreshWidthAndHeight(){
		//迭代 members 內的每個 entry，對於每個 entry，計算該 entry 所代表的 UMLObject 與 UMLGroup 物件的左上角的距離
		for(Map.Entry<String, UMLObject> entry: members.entrySet()){
			//Group新的寬度=(UMLObject 的 x 坐標 - UMLGroup 物件的 x 坐標) + 該 UMLObject 的寬度
			int new_width = entry.getValue().getX() + entry.getValue().getWidth() - this.x;
			int new_height = entry.getValue().getY() + entry.getValue().getHeight() - this.y;
			if(this.width < new_width){
				this.width = new_width;
			}
			if(this.height < new_height){
				this.height = new_height;
			}
		}
	}
	
	public boolean isSelected(int x, int y){ 
		for(Map.Entry<String, UMLObject> entry: members.entrySet()){
			if(entry.getValue().isSelected(x, y)){
				return true;
			}
		}
		return false;
	}
	
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
	
	private void refreshPorts(){
		ports[0] = new Port(new Point((int)(x + width * 0.5 - 3.0), (int)(y - 3.0)));
		ports[1] = new Port(new Point((int)(x - 3.0), (int)(y + height * 0.5 - 3.0)));
		ports[2] = new Port(new Point((int)(x + width - 3.0), (int)(y + height * 0.5 - 3.0)));
		ports[3] = new Port(new Point((int)(x + width * 0.5 - 3.0), (int)(y + height - 3.0)));
	}
	
	protected void drawPorts(Graphics2D g){
		ports[0].draw(g);
		ports[1].draw(g);
		ports[2].draw(g);
		ports[3].draw(g);
	}
	
	public void move(int x_offset, int y_offset){
		this.x += x_offset;
		this.y += y_offset;
		
		//����members�̪��Ҧ�����
		for(Map.Entry<String, UMLObject> entry:members.entrySet())
		{
			entry.getValue().move(x_offset, y_offset);
		}
	}
	
}
