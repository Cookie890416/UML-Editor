import java.awt.event.MouseEvent;

public class UseCaseMode extends Mode{
	public void mouseClicked(MouseEvent arg0){
		UseCaseObject usecase_object = new UseCaseObject(Configure.UseCaseBut_title, arg0.getX(), arg0.getY());
		UMLCanvas.getInstance().addObjectToMap(usecase_object);
	}
}
