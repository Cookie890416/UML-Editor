import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RenameDialog extends JDialog{
	UMLObject object;
	Container container;
	JTextField text_field;
	public RenameDialog(UMLObject obj) {
		super(new JFrame(), Configure.rename_dailog_title, true);
		object = obj;
		container = getContentPane();
		init();
	}
	
	private void init(){
		try{
			this.setResizable(false);
			this.setSize(270, 150);
			container.setLayout(null);
			setDialogContent();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void setDialogContent(){
		addLabel();
		addTextField();
		addOKButton();
		addCancelButton();
	}
	
	public void addLabel(){
		JLabel label = new JLabel();
		label.setText("please enter the new object name:");
		label.setBounds(9, 10, 250, 25);
		container.add(label);
	}
	
	public void addTextField(){
		text_field = new JTextField();
		text_field.setBounds(9, 40, 250, 25);
		container.add(text_field);
	}
	
	public void addOKButton(){
		JButton ok_button = new JButton();
		ok_button.setText("OK");
		ok_button.setBounds(97, 80, 75, 25);
		ok_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				runOKButton(evt);
			}
		});
		container.add(ok_button);
	}
	public void addCancelButton(){
		JButton cancel_button = new JButton();
		cancel_button.setText("Cancel");
		cancel_button.setBounds(177, 80, 75, 25);
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				runCancelButton(evt);
			}
		});
		container.add(cancel_button);
	}
	
	private void runOKButton(ActionEvent evt){
		object.setName(text_field.getText());
		this.dispose();
	}
	public void runCancelButton(ActionEvent evt){
		this.dispose();
	}
}
