import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UseCaseBut extends UMLbutton{
	UMLbutton this_button = null;
	public UseCaseBut(){
		this_button = this;
		setIconPath("Icon/UseCase.jpg", "Icon/UseCase_gray.jpg");
		setName(Configure.UseCaseBut_title);
		unlock();
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(getLocked_button() != null){
					getLocked_button().unlock();
				}
				lock();
				setLocked_button(this_button);
				UMLCanvas.getInstance().setMode(new UseCaseMode());
			}
		});
	}
}
