import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassBut extends UMLbutton{
	UMLbutton this_button = null;
	public ClassBut(){
		this_button = this;
		setIconPath("Icon/Class.jpg", "Icon/Class_gray.jpg");
		setName(Configure.ClassBut_title);
		unlock();
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(getLocked_button() != null){
					getLocked_button().unlock();
				}
				lock();
				setLocked_button(this_button);
				UMLCanvas.getInstance().setMode(new ClassMode());
			}
		});
	}
}
