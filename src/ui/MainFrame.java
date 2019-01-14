package ui;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import manager.ListenersManager;
import manager.PanelsManager;
import manager.StateManger;


public class MainFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MainFrame() {
		// TODO Auto-generated constructor stub
		this.setBigFont();//将全局字体设为大的
		PanelsManager.init();
		PanelsManager.getjTabbedPane().setFont(new Font("TimesRoman",Font.BOLD,18));	
		add(PanelsManager.getjTabbedPane());              
		StateManger.setStartupState();
		ListenersManager.init();
	}
	private void setBigFont() {
		Font fnt = new Font("TimesRoman",Font.BOLD,18);
		FontUIResource fontRes = new FontUIResource(fnt);
		for(Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();){
			Object key = keys.nextElement();
		    Object value = UIManager.get(key);
		    if(value instanceof FontUIResource)    
		            UIManager.put(key, fontRes);
		}
		UIManager.getDefaults().put(   "TitledBorder.font ",fnt);
	}
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		Dimension screenSizeDimension = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		int width = 1200;
		int height = 700;
		mainFrame.setSize(width, height);
		mainFrame.setLocation((screenSizeDimension.width - width) / 2, 
				(screenSizeDimension.height - height) / 2);
		mainFrame.setTitle("S-condition Analysis Tool (SAT)");//Suspicious-pair Analysis Tool (SAT)
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
}
