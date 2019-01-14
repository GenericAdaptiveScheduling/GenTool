package manager;

import ui.editor.EPatsTable;
import loader.EPatsTableLoader;


public class EPatsTableManager {
	public static EPatsTable ePatsTable;
	public static void init() {
		ePatsTable = EPatsTableLoader.getPatsTable(FilesPathManager.getPatternsFileName());
	}
	public static EPatsTable getEPatsTable() {
		return ePatsTable;
	}
}
