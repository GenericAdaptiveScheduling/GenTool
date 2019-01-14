package listener;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import manager.ERulesTableManager;

public class MyJTreeListener implements TreeSelectionListener{

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		ERulesTableManager.saveERulesTableFromJTreeAndRefresh();
	}
	

}
