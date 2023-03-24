import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class ClassObject extends BasicObject{

	public ClassObject(String name, int x, int y) {
		super(name, x, y, 100, 75);//設定x,y寬度和高度
		// TODO Auto-generated constructor stub
	}
	
	private void refreshPorts(){
		//port 0~3代表 上,左,右,下
		ports[0] = new Port(new Point((int)(x + width * 0.5 - 3.0), (int)(y - 3.0)));
		ports[1] = new Port(new Point((int)(x - 3.0), (int)(y + height * 0.5 - 3.0)));
		ports[2] = new Port(new Point((int)(x + width - 3.0), (int)(y + height * 0.5 - 3.0)));
		ports[3] = new Port(new Point((int)(x + width * 0.5 - 3.0), (int)(y + height - 3.0)));
	}
	
	public void drawInCanvas(Graphics2D g){
		//Class Object
		Rectangle2D rectangle;
		rectangle = new Rectangle2D.Double(x, y, width, height);
		//劃一個長方形,然後在長方形裡面畫兩條線
		g.draw(rectangle);
		g.drawLine(x, y+25, x+100, y+25);
		g.drawLine(x, y+50, x+100, y+50);
		g.drawString(getName(), (int)(x+(width-getName().length()*7)*0.5), (int)(y+(height*0.25)));
		
		refreshPorts();
		
		//畫四個點
		if(drawports){
			drawPorts(g);
		}
	}
}
