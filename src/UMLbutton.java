import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class UMLbutton extends JButton{
	private String picture_path;
	private String lock_picture_path;
	private static UMLbutton locked_button;
	
	protected void setIconPath(String pict_path, String lock_pict_path){
		picture_path = pict_path;
		lock_picture_path = lock_pict_path;
	}
	public String getPicture(){return picture_path;}
	public String getLockPicture(){return lock_picture_path;}
	protected void unlock(){
		if(picture_path != null){
			//setIcon(new ImageIcon("C:\\Users\\Max\\OneDrive\\桌面\\image\\" + imgName[i] + ".jpg"));
			//https://blog.csdn.net/xiongmaodeguju/article/details/70871801
			//getClass().getClassLoader().getResource(picture_path)取得現在檔案的位置
			this.setIcon(new ImageIcon(getClass().getClassLoader().getResource(picture_path)));
			//new ImageIcon(getClass().getClassLoader().getResource(picture_path))
			//當按下另外一個button的時候,取得本來button的檔案位置 ex: icon/1.jpg
		}
	}
	protected void lock(){
		if(lock_picture_path != null){
			this.setIcon(new ImageIcon(getClass().getClassLoader().getResource(lock_picture_path)));
			//new ImageIcon(getClass().getClassLoader().getResource(lock_picture_path))
			//當按下現在這個button的時候,印出本來button的檔案位置 ex: icon/1.jpg
		}
	}
	
	public static UMLbutton getLocked_button() {
//		if(locked_button != null) {
//			System.out.println(locked_button.picture_path+" "+locked_button.lock_picture_path);
//		}
		return locked_button;
	}

	public static void setLocked_button(UMLbutton button) {
		locked_button = button;
	}
}
