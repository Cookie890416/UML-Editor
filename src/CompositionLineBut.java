import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompositionLineBut extends UMLbutton{
	UMLbutton this_button = null;
	public CompositionLineBut(){
		this_button = this;
		setIconPath("Icon/CompositionLine.jpg", "Icon/CompositionLine_gray.jpg");
		setName(Configure.CompositionLineBut_title);
		unlock();
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(getLocked_button() != null){
					getLocked_button().unlock();
				}
				lock();
				setLocked_button(this_button);
				UMLCanvas.getInstance().setMode(new CompositionLineMode());
			}
		});
	}
}
