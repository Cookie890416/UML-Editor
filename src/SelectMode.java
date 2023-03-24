import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectMode extends Mode{
	private String selected_object_number;
	private Point selected_start_point;
	private ArrayList<String> selected_objects;
	private HashMap<String, UMLObject> umlObjectMap;//map裡面會存<1,class>,<2,UseCase>
	private Point released_point;
	private Point dragged_point;
	
	public void mousePressed(MouseEvent arg0) {
		String object_name = null;
		selected_object_number = null;
		selected_start_point = new Point(arg0.getX(), arg0.getY());
		selected_objects = new ArrayList<String>();
		//拿到UML map
		umlObjectMap = (HashMap<String, UMLObject>) UMLCanvas.getInstance().getUMLObjectMap();
		
		//疊代 UML 物件地圖中的每個物件，並將物件名稱儲存在 object_name 變數中
		for(Map.Entry<String, UMLObject> entry: umlObjectMap.entrySet()){
			
			object_name = umlObjectMap.get(entry.getKey()).getName();
			if(object_name == Configure.AssociationLineBut_title || object_name == Configure.CompositionLineBut_title
					|| object_name == Configure.GeneralizationLineBut_title){
				continue;
			}
			//如果已經選擇select mode
			if(entry.getValue().isSelected(arg0.getX(), arg0.getY())){
				
				//如果之前沒有選其他物件,則selected_object_number設定成現在的物件
				if(selected_object_number == null){
					selected_object_number = entry.getKey();
					continue;
				}
				
				//如果 selected_object_number 變數已經有值，則比較此物件和目前 selected_object_number 所代表的物件深度，
				//選擇較淺的物件儲存其編號在 selected_object_number 變數中。
				if(umlObjectMap.get(selected_object_number).getDepth() > entry.getValue().getDepth()){
					selected_object_number = entry.getKey();
				}
			}
		}
		if(selected_object_number != null){
			selected_objects.add(selected_object_number);
			//把有選中的物件drawPortsFlag設定成true,之後再重新繪圖,把選中的物件旁邊的port著色
			refreshDrawPortsFlag();
			UMLCanvas.getInstance().repaint();
		}
	}
	
	public void mouseReleased(MouseEvent arg0) {
		//利用arg0.getX(), arg0.getY()得到released_point的x,y座標
		released_point = new Point(arg0.getX(), arg0.getY());
		for(Map.Entry<String, UMLObject> entry: umlObjectMap.entrySet()){
			if(entry.getValue().isContained(selected_start_point, released_point)){
				selected_objects.add(entry.getKey());
			}
		}
		//把有選中的物件drawPortsFlag設定成true,把setDraggedRectangleFlag設定成false(因為沒有拖拉了),之後再重新繪圖,把選中的物件旁邊的port著色
		refreshDrawPortsFlag();
		UMLCanvas.getInstance().setDraggedRectangleFlag(false);
		UMLCanvas.getInstance().repaint();
	}
	
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		dragged_point = new Point(arg0.getX(), arg0.getY());
		
		if(selected_objects.size() == 0){
			UMLCanvas.getInstance().setDraggedRectangleFlag(true);
		}else if(selected_objects.size() == 1){
			UMLObject object = umlObjectMap.get(selected_objects.get(0));
			int x_offset = dragged_point.x - selected_start_point.x;
			int y_offset = dragged_point.y - selected_start_point.y;
			//x=x+x_offset,y=y+y_offset
			object.move(x_offset, y_offset);
			
			selected_start_point.x = dragged_point.x;
			selected_start_point.y = dragged_point.y;
		}
		
		UMLCanvas.getInstance().repaint();
	}
	
	//如果有選重的物件,透過將drawPortsFlag設置為true,以後直接把port著色
	private void refreshDrawPortsFlag(){
		for(Map.Entry<String, UMLObject> entry: umlObjectMap.entrySet())
		{
			//對於選中的物件，將它的 drawPortsFlag 設置為 true，否則設置為 false
			if(selected_objects.contains(entry.getKey()))
			{
				umlObjectMap.get(entry.getKey()).setDrawPortsFlag(true);
			}else
			{
				umlObjectMap.get(entry.getKey()).setDrawPortsFlag(false);
			}
		}
	}
	
	public void groupObjects(){
		if(selected_objects.size() > 1){
			HashMap<String, UMLObject> members = new HashMap<String, UMLObject>();
			UMLObject object;
			for(int i = 0; i < selected_objects.size(); i++){
				object = umlObjectMap.get(selected_objects.get(i));
				object.setDrawPortsFlag(false);
				members.put(selected_objects.get(i), object);
				umlObjectMap.remove(selected_objects.get(i));
			}
			CompositeObject composite_object = new CompositeObject(Configure.composite_object_name, members);
			UMLCanvas.getInstance().addObjectToMap(composite_object);
		}
	}
	
	public void ungroupObjects(){
		
		if(selected_objects.size() == 1){
			if(umlObjectMap.get(selected_objects.get(0)).getName() == Configure.composite_object_name){
				CompositeObject composite_object = (CompositeObject)umlObjectMap.get(selected_objects.get(0));
				//�Ncomposite_object�����Ҧ�members���s�[�^uml_object_map�A�ñN�쥻��composite_object�quml_object_map������
				umlObjectMap.putAll(composite_object.getAllMembers());
				umlObjectMap.remove(selected_objects.get(0));
				UMLCanvas.getInstance().repaint();
			}
		}
	}
	
	public void renameObject(){
		if(selected_objects.size() == 1){
			if(umlObjectMap.get(selected_objects.get(0)).getName() == Configure.composite_object_name){
				return;
			}
			RenameDialog dialog = new RenameDialog(umlObjectMap.get(selected_objects.get(0)));
			int x = (int)(UMLCanvas.getInstance().getX()+UMLCanvas.getInstance().getWidth()*0.7);
			int y = (int)(UMLCanvas.getInstance().getY()+UMLCanvas.getInstance().getHeight()*0.4);
			dialog.setLocation(x,y);
			dialog.setVisible(true);
			UMLCanvas.getInstance().repaint();
		}
	}
}
