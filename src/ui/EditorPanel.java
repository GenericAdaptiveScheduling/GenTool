package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import manager.EPatsTableManager;
import manager.ERulesTableManager;
import manager.JButtonsManager;


//public class EditorMainPanel extends JPanel implements TableModelListener,TreeSelectionListener,MouseListener,ActionListener,CellEditorListener,TreeModelListener{
	/**
	 * 
	 */
public class EditorPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public EditorPanel() {
		// TODO Auto-generated constructor stub
		JButtonsManager.init();
		EPatsTableManager.init();
		ERulesTableManager.init(EPatsTableManager.getEPatsTable().getPatterns());
		mainLayout();
	}
	private void mainLayout() {
		JScrollPane jTreeScrollPane = new JScrollPane(ERulesTableManager.getERulesTable().getTree().getJTree());
		jTreeScrollPane.setMinimumSize(new Dimension(300,400));
		JScrollPane graphPane = new JScrollPane(ERulesTableManager.getERulesTable().getGraph());
		graphPane.setPreferredSize(new Dimension(800,400));
		JTextPane textPane = ERulesTableManager.getERulesTable().getText();
		this.setLayout(new BorderLayout());
		this.add(textPane, BorderLayout.NORTH);
		
		int width = 200;
		int height = 36;
		setButtonSize(JButtonsManager.getRulesAnalyseButton(), width, height);
		setButtonSize(JButtonsManager.getRulesVerifyButton(), width, height);
		setButtonSize(JButtonsManager.getSaveButton(), width, height);
		setButtonSize(JButtonsManager.getExitButton(), width, height);
		
		Box box = Box.createHorizontalBox();
		box.setBorder(new TitledBorder("Rules"));
		box.add(Box.createHorizontalStrut(78));
		box.add(JButtonsManager.getRulesVerifyButton());
		box.add(Box.createHorizontalStrut(78));
		box.add(JButtonsManager.getRulesAnalyseButton());
		box.add(Box.createHorizontalStrut(78));
		box.add(JButtonsManager.getSaveButton());	
		box.add(Box.createHorizontalStrut(78));
		box.add(JButtonsManager.getExitButton());		
		
		JSplitPane jsTop = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jTreeScrollPane,graphPane);
		JSplitPane jsMain = new JSplitPane(JSplitPane.VERTICAL_SPLIT,jsTop,box);
		this.add(jsMain);
	}
	void setButtonSize(JButton button,int width,int height)
	{
		button.setPreferredSize(new Dimension(width,height));
		button.setMaximumSize(new Dimension(width,height));
		button.setMinimumSize(new Dimension(width,height));
	}
}
