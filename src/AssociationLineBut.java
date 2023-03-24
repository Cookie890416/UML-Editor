import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssociationLineBut extends UMLbutton{
	UMLbutton this_button = null;
	public AssociationLineBut(){
		this_button = this;
		setIconPath("Icon/AssociationLine.jpg", "Icon/AssociationLine_gray.jpg");
		setName(Configure.AssociationLineBut_title);
		unlock();
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(getLocked_button() != null){
					getLocked_button().unlock();
				}
				lock();
				setLocked_button(this_button);
				UMLCanvas.getInstance().setMode(new AssociationLineMode());
			}
		});
	}
}
