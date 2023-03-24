import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;


public class UMLCanvas extends JPanel{
	private static UMLCanvas instance = new UMLCanvas();
	private Mode currentMode = new Mode();
	private int object_number = 1;
	private int depth_count = 99;
	private HashMap<String, UMLObject> uml_object_map = new HashMap<String, UMLObject>();
	private Point selected_start_point;
	private Point dragged_point;
	private boolean dragged_rectangle_flag = false;
	private boolean dragged_line_flag = false;
	
	private UMLCanvas(){
		setBounds(116, 38, 578, 494);
		
		addMouseListener(new CanvasMouseListener());
	    addMouseMotionListener(new CanvasMouseMotionListener());
	    
	    setVisible(true);
	}
	
	
	public static UMLCanvas getInstance(){return instance;}
	protected void setMode(Mode mode){currentMode = mode;}
	protected Mode getMode(){return currentMode;}
	
	public HashMap<String, UMLObject> getUMLObjectMap(){return this.uml_object_map;}
	
	
	public void setDraggedRectangleFlag(boolean value){ this.dragged_rectangle_flag = value;}
	
	public boolean getDraggedRectangleFlag(){ return this.dragged_rectangle_flag;}
	
	
	public void setDraggedLineFlag(boolean value){ this.dragged_line_flag = value;}
	
	public boolean getDraggedLineFlag(){ return this.dragged_line_flag;}
	
	private void setBackground(Graphics2D g)
	{
		//設定繪圖筆的顏色為白色（Color.white），接著使用 fillRect() 方法來填滿整個畫布區域，使其成為白色背景。
		g.setColor(Color.white);
	    g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		
		//呼叫 setBackground() 方法設定畫布的背景色為白色
		setBackground(g2);
		
        //設定繪圖筆的顏色為黑色（Color.BLACK）
        g2.setColor(Color.BLACK);
        
        //並且使用迴圈遍歷 uml_object_map 中的所有物件，呼叫其 drawInCanvas() 方法在畫布上繪製對應的圖形。
		for(Map.Entry<String, UMLObject> entry: uml_object_map.entrySet()){
			entry.getValue().drawInCanvas(g2);
		}
		
		//呼叫 this.drawDraggedRectangle() 方法，這個方法會繪製正在被拖曳的矩形，如果沒有正在被拖曳的矩形，則這個方法不會進行任何操作
        this.drawDraggedRectangle(g2);
        //如果目前的模式是畫線模式且有線段正在被拖曳，則呼叫 currentMode 的 drawDraggedLine() 方法繪製拖曳中的線段。
        if(dragged_line_flag){
        	currentMode.drawDraggedLine(g2);
        }
	}
	
	public void addObjectToMap(UMLObject uml_object){
		
		uml_object.setDepth(depth_count);
		
		//當新增一個物件的時候,把一組key,value丟進map中
		//把key代表現在是第幾個物件,value代表這個物件是甚麼圖形
		uml_object_map.put(Integer.toString(object_number), uml_object);
		
		
		object_number++;
		depth_count--;
		
		repaint();//呼叫 repaint() 方法重繪畫布，以顯示新增的 UMLObject 物件
	}
	
	private void drawDraggedRectangle(Graphics2D g){
		int rectangle_width, rectangle_height;
		int start_x, start_y;
		
		//如果 dragged_rectangle_flag 的值為 true，表示正在拖曳一個矩形，則執行以下操作：
		 if(dragged_rectangle_flag == true){
		    //設定繪圖筆的粗細為 1.25f，形狀為圓角線，連接處形狀為圓角，線條樣式為虛線（由 new float[]{2,2,2,2} 決定），無偏移
	    	g.setStroke(new BasicStroke(1.25f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{2,2,2,2}, 0));
	    	
	    	//計算矩形的起點座標和寬高，起點座標為拖曳的起點和終點座標的 x 和 y 座標的最小值，寬和高分別為拖曳終點座標和起點座標的差值的絕對值。
	    	start_x = Math.min(selected_start_point.x, dragged_point.x);
	    	start_y = Math.min(selected_start_point.y, dragged_point.y);
	    	rectangle_width = Math.abs(dragged_point.x - selected_start_point.x);
	    	rectangle_height = Math.abs(dragged_point.y - selected_start_point.y);
	    	
	    	//使用 Graphics2D 類別的 drawRect() 方法繪製矩形。
	    	g.drawRect(start_x, start_y, rectangle_width, rectangle_height);
	     }
	}
	
	public void groupObjects(){currentMode.groupObjects();}
	public void ungroupObjects(){currentMode.ungroupObjects();}
	public void renameObject(){currentMode.renameObject();}
	
	class CanvasMouseListener extends MouseAdapter{
		private String selected_object_number;
		private String end_selected_object_number;
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			currentMode.mouseClicked(arg0);
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			selected_start_point = new Point(arg0.getX(), arg0.getY());
			currentMode.mousePressed(arg0);
		}
		
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			currentMode.mouseReleased(arg0);
		}
	}
	
	class CanvasMouseMotionListener extends MouseMotionAdapter{
		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			dragged_point = new Point(arg0.getX(), arg0.getY());
			currentMode.mouseDragged(arg0);
		}	
	}
}
