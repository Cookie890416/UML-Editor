import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectBut extends UMLbutton{
	UMLbutton this_button = null;
	public SelectBut(){
		this_button = this;
		setIconPath("Icon/Select.jpg", "Icon/Select_gray.jpg");
		setName(Configure.SelectBut_title);
		unlock();//設定按鈕為可用狀態
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//首先檢查當前是否已經有被鎖定的按鈕，如果有，就將其解鎖。然後將當前按鈕鎖定，並設置被鎖定的按鈕為當前按鈕。
				if(getLocked_button() != null){
					getLocked_button().unlock();
				}
				lock();
				setLocked_button(this_button);
				//將畫布的模式設置為選擇模式（SelectMode）
				UMLCanvas.getInstance().setMode(new SelectMode());
			}
		});
	}
}
