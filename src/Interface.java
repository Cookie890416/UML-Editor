import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import javax.swing.JToolBar;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import java.awt.Canvas;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Color;

public class Interface extends JFrame {
	private JPanel contentPane;
	private ButtonFactory button_factory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//https://blog.csdn.net/weixin_48419914/article/details/121048320
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interface() {
		button_factory = new ButtonFactory();
		initGUI();
	}
	
	private void initGUI() {
		//用戶單擊窗口的關閉按鈕時程序執行的操作
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 設定我們的視窗在螢幕上的(100, 100)位置出現，寬720高580
		//物件.setBounds(int x, int y,int width, int height)
		setBounds(100, 100, 720, 580); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);// 設定contentPane為容器
		contentPane.setLayout(null);// 不使用版面配置
		this.setTitle("UML Editor");
		
		setButtons();
		setMenuBar();
		setCanvas();
	}
	
	private void setMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 704, 32);
		contentPane.add(menuBar);
		
		JMenu file = new JMenu("File");
		file.setPreferredSize(new java.awt.Dimension(40, 25));
		file.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(file);
		
		JMenu edit = new JMenu("Edit");
		edit.setPreferredSize(new java.awt.Dimension(40, 25));
		edit.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(edit);
		
		JMenuItem group = new JMenuItem("Group");
		group.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				UMLCanvas.getInstance().groupObjects();
			}
		});
		edit.add(group);
		
		JMenuItem ungroup = new JMenuItem("Ungroup");
		ungroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				UMLCanvas.getInstance().ungroupObjects();
			}
		});
		edit.add(ungroup);
		
		JMenuItem change_name = new JMenuItem("Change Name");
		change_name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				UMLCanvas.getInstance().renameObject();
			}
		});
		edit.add(change_name);
	}
	
	private void setButtons(){
		JPanel panel = new JPanel();
		panel.setBounds(0, 38, 110, 504);
		contentPane.add(panel);
		// add all of buttons into JPanel
		panel.add(button_factory.getButton(Configure.SelectBut_title));
		panel.add(button_factory.getButton(Configure.AssociationLineBut_title));
		panel.add(button_factory.getButton(Configure.GeneralizationLineBut_title));
		panel.add(button_factory.getButton(Configure.CompositionLineBut_title));
		panel.add(button_factory.getButton(Configure.ClassBut_title));
		panel.add(button_factory.getButton(Configure.UseCaseBut_title));
	}
	
	private void setCanvas(){
		contentPane.add(UMLCanvas.getInstance());
	}

	
}

