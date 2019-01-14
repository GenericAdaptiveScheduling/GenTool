package manager;

import listener.EditorPanelJButtonsListener;
import listener.JTabbedPaneListener;
import listener.MyJTreeListener;


public class ListenersManager {
	public static EditorPanelJButtonsListener editPanelJButtonsListener;
	public static JTabbedPaneListener jTabbedPaneListener;
	public static MyJTreeListener myJTreeListener;
	public static void init() {
		editPanelJButtonsListener = new EditorPanelJButtonsListener();
		jTabbedPaneListener = new JTabbedPaneListener();
		myJTreeListener = new MyJTreeListener();
		JButtonsManager.getRulesAnalyseButton().addActionListener(editPanelJButtonsListener);
		JButtonsManager.getRulesVerifyButton().addActionListener(editPanelJButtonsListener);
		JButtonsManager.getSaveButton().addActionListener(editPanelJButtonsListener);
		JButtonsManager.getExitButton().addActionListener(editPanelJButtonsListener);
		
		PanelsManager.getjTabbedPane().addChangeListener(jTabbedPaneListener);
		
		ERulesTableManager.getERulesTable().getTree().getJTree().addTreeSelectionListener(myJTreeListener);
	}
	public static MyJTreeListener getJTreeListener() {
		return myJTreeListener;
	}
}
