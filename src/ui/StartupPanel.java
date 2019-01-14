package ui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import manager.EPatsTableManager;
import manager.ERulesTableManager;
import manager.FilesPathManager;
import manager.StateManger;


public class StartupPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JRadioButton loadDemoButton;
	JRadioButton loadCertainButton;
	JLabel label;
	JButton okButton;
	JButton cancelButton;
	public StartupPanel() {
		// TODO Auto-generated constructor stub
		loadDemoButton = new JRadioButton("Load a demo project");
		loadCertainButton = new JRadioButton("Load an existing project");
		label = new JLabel("Startup Options:");
		okButton = new JButton("  OK  ");
		cancelButton = new JButton("Exit");
		
		Box box = Box.createVerticalBox();
		box.add(label);
		box.add(loadDemoButton);
		box.add(loadCertainButton);
		
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(Box.createVerticalStrut(10));
		buttonBox.add(okButton);
		buttonBox.add(Box.createVerticalStrut(3));
		buttonBox.add(cancelButton);
		buttonBox.add(Box.createVerticalStrut(10));
		Box mainBox = Box.createVerticalBox();
		mainBox.add(box);
		mainBox.add(buttonBox);
		add(mainBox);
		
		loadDemoButton.addActionListener(this);
		loadCertainButton.addActionListener(this);
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == okButton) {
			if(loadDemoButton.isSelected())
			{
				//ERulesTableManager.init();
				//EPatsTableManager.init();
				StateManger.setInitState();//jump to edit panel
			}
			else if (loadCertainButton.isSelected()) {
				boolean loadNewPath = FilesPathManager.changWorkPlace();
				if(loadNewPath)
				{
					EPatsTableManager.init();
					ERulesTableManager.reload(EPatsTableManager.getEPatsTable().getPatterns());
					StateManger.setInitState();
				}
			}
			
		}
		else if (e.getSource() == cancelButton) {
			System.exit(0);
		}
	}
}
