import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralizationLineBut extends UMLbutton{
	UMLbutton this_button = null;
	public GeneralizationLineBut(){
		this_button = this;
		setIconPath("Icon/GeneralizationLine.jpg", "Icon/GeneralizationLine_gray.jpg");
		setName(Configure.GeneralizationLineBut_title);
		unlock();
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(getLocked_button() != null){
					getLocked_button().unlock();
				}
				lock();
				setLocked_button(this_button);
				UMLCanvas.getInstance().setMode(new GeneralizationLineMode());
			}
		});
	}
}
