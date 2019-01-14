package manager;

import java.util.Vector;

import kernel.Context;
import loader.ERulesTableLoader;
import ui.editor.ERulesTable;

public class ERulesTableManager {
	public static ERulesTable eRulesTable;
	public static void init(Vector<Context> patList) {
		eRulesTable = ERulesTableLoader.getRulesTable(patList,FilesPathManager.getRulesFileName());
		eRulesTable.setPanel();
		eRulesTable.setFirstSlected();
		eRulesTable.repaintRelatedPanels(eRulesTable);
		eRulesTable.getGraph().updateUI();
		eRulesTable.getTree().getJTree().updateUI();
		eRulesTable.getText().updateUI();
	}
	
	public static void reload(Vector<Context> patList) {
		eRulesTable.getTree().display(ERulesTableLoader.getRulesTable(patList,FilesPathManager.getRulesFileName()));
		eRulesTable.repaintRelatedPanels(ERulesTableLoader.getRulesTable(patList,FilesPathManager.getRulesFileName()));
	}
	
	public static void saveERulesTableFromJTreeAndRefresh() {
		eRulesTable.repaintRelatedPanels(eRulesTable.getTree().getERulesTable());
	}
	
	public static ERulesTable getERulesTable() {
		return eRulesTable;
	}
	
}
