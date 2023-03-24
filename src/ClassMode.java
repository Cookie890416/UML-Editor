import java.awt.event.MouseEvent;

public class ClassMode extends Mode{
	public void mouseClicked(MouseEvent arg0){
		ClassObject class_object = new ClassObject(Configure.ClassBut_title, arg0.getX(), arg0.getY());
		UMLCanvas.getInstance().addObjectToMap(class_object);
	}
}
