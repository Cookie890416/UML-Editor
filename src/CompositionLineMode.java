import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class CompositionLineMode extends Mode{
	private String selected_object_number;
	private String end_selected_object_number;
	private Point start_point;
	private Point dragged_point;
private HashMap<String, UMLObject> umlObjectMap;//map裡面會存<1,class>,<2,UseCase>
	
	public void mousePressed(MouseEvent arg0) {
		String object_name = null;
		selected_object_number = null;
		start_point = new Point(arg0.getX(), arg0.getY());
		//first initializes the starting point of the mouse event and creates a clone of the current UML object map. 
		umlObjectMap = (HashMap<String, UMLObject>) UMLCanvas.getInstance().getUMLObjectMap().clone();
		
		//Then it iterates through the cloned map to check if any UML object 
		//(except the line objects) is selected at the current mouse location.
		for(Map.Entry<String, UMLObject> entry: umlObjectMap.entrySet()){
			
			object_name = umlObjectMap.get(entry.getKey()).getName();
			if(object_name == Configure.AssociationLineBut_title || object_name == Configure.CompositionLineBut_title
					|| object_name == Configure.GeneralizationLineBut_title){
				continue;
			}
			
			if(object_name == Configure.composite_object_name){
				continue;
			}
			//If an object is selected, it is stored in a variable named "selected_object_number".
			//entry.getValue()是一個物件
			if(entry.getValue().isSelected(arg0.getX(), arg0.getY())){
				
				if(selected_object_number == null){
					selected_object_number = entry.getKey();
					continue;
				}
				//If there are multiple objects at the same location, the object with the highest depth is selected.
				if(umlObjectMap.get(selected_object_number).getDepth() > entry.getValue().getDepth()){
					selected_object_number = entry.getKey();
				}
			}
		}
		//If an object is selected, the "draggedLineFlag" flag is set to true, 
		//indicating that a line can be dragged from this object.
		if(selected_object_number != null){
			UMLCanvas.getInstance().setDraggedLineFlag(true);
		}
	}
	public void mouseReleased(MouseEvent arg0){
		end_selected_object_number = null;
		String object_name = null;
		
		UMLCanvas.getInstance().setDraggedLineFlag(false);
				
		
		if(selected_object_number != null){
			//透過for迴圈來尋找滑鼠釋放時，所在位置是否有物件被選取，
			//如果是就儲存物件名稱到selected_object_number，以及設定DraggedLineFlag為true。
			for(Map.Entry<String, UMLObject> entry: umlObjectMap.entrySet()){
				
				//map裡面會存<1,class>,<2,UseCase>
				//object_name ex:class,UseCase,AssociationLine
				object_name = umlObjectMap.get(entry.getKey()).getName();
				
				//if(selected_object_number == entry.getKey())則表示選到自己,自己和自己無法連成一條線
				if(selected_object_number == entry.getKey() || object_name == Configure.AssociationLineBut_title
						|| object_name == Configure.CompositionLineBut_title || object_name == Configure.GeneralizationLineBut_title){
					continue;
				}
				
				if(object_name == Configure.composite_object_name){
					continue;
				}
				//如果selected_object_number不是null，
				//則進一步搜尋所有物件看是否有其他被選取的物件，並且將其中深度較淺的物件名稱儲存到end_selected_object_number中。
				if(entry.getValue().isSelected(arg0.getX(), arg0.getY())){
					
					if(end_selected_object_number == null){
						end_selected_object_number = entry.getKey();
						continue;
					}
					
					if(umlObjectMap.get(end_selected_object_number).getDepth() > entry.getValue().getDepth()){
						end_selected_object_number = entry.getKey();
					}
				}
			}
			//如果end_selected_object_number不是null，則建立CompositionLine物件並將它加入到UMLCanvas的物件Map中
			if(end_selected_object_number != null){
				CompositionLine composition_line = new CompositionLine(Configure.CompositionLineBut_title, umlObjectMap.get(selected_object_number), umlObjectMap.get(end_selected_object_number));
				UMLCanvas.getInstance().addObjectToMap(composition_line);
			}
		}
		//重繪UMLCanvas
		UMLCanvas.getInstance().repaint();
	}
	public void mouseDragged(MouseEvent arg0) {
		dragged_point = new Point(arg0.getX(), arg0.getY());
		UMLCanvas.getInstance().repaint();
	}
	public void drawDraggedLine(Graphics2D g2) {
		if(selected_object_number != null){
			//line_start_point代表離selected_object最近的點
			Point line_start_point = umlObjectMap.get(selected_object_number).getNearestPort(start_point);
			
			int x,x1,x2;
			int y,y1,y2;
			//劃一條Composition line
			x = (int)((dragged_point.getX() * 0.87 + line_start_point.getX()*0.12));
			y = (int)((dragged_point.getY() * 0.87 + line_start_point.getY()*0.12));
			
			x1 = (int)((x+dragged_point.getX())/2 + (dragged_point.getY()-y)/(Math.sqrt(3)*2));
			y1 = (int)((y+dragged_point.getY())/2 - (dragged_point.getX()-x)/(Math.sqrt(3)*2));
				
			x2 = (int)((x+dragged_point.getX())/2 - (dragged_point.getY()-y)/(Math.sqrt(3)*2));
			y2 = (int)((y+dragged_point.getY())/2 + (dragged_point.getX()-x)/(Math.sqrt(3)*2));
			
			g2.drawLine((int)dragged_point.getX(), (int)dragged_point.getY(), x1, y1);
			g2.drawLine((int)dragged_point.getX(), (int)dragged_point.getY(), x2, y2);
			g2.drawLine(x1, y1, x, y);
			g2.drawLine(x2, y2, x, y);
			g2.drawLine(x, y, (int)line_start_point.getX(), (int)line_start_point.getY());
			
			g2.setColor(Color.black);
			g2.fill(new Rectangle.Double(line_start_point.x, line_start_point.y, 6.0, 6.0));
		}
	}
}
