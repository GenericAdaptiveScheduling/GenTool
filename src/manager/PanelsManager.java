package manager;

import javax.swing.JTabbedPane;

import ui.AnalyzerPanel;
import ui.VerifierPanel;
import ui.EditorPanel;
import ui.StartupPanel;

public class PanelsManager {
	static StartupPanel initPanel;
	static EditorPanel editorMainPanel;
	static VerifierPanel checkShowPanel;
	static AnalyzerPanel analyseShowPanel;
	static JTabbedPane jTabbedPane;
	public static void init() {
		initPanel = new StartupPanel();
		editorMainPanel = new EditorPanel();
		checkShowPanel = new VerifierPanel();
		analyseShowPanel = new AnalyzerPanel();
		jTabbedPane = new JTabbedPane();
		jTabbedPane.add("Editor",editorMainPanel);
		jTabbedPane.add("Verifier", checkShowPanel);
		jTabbedPane.add("Analyzer", analyseShowPanel);
		jTabbedPane.add("Startup Options",initPanel);
	}
	public static StartupPanel getInitPanel() {
		return initPanel;
	}
	public static void setInitPanel(StartupPanel initPanel) {
		PanelsManager.initPanel = initPanel;
	}
	public static EditorPanel getEditorMainPanel() {
		return editorMainPanel;
	}
	public static void setEditorMainPanel(EditorPanel editorMainPanel) {
		PanelsManager.editorMainPanel = editorMainPanel;
	}
	public static VerifierPanel getCheckShowPanel() {
		return checkShowPanel;
	}
	public static void setCheckShowPanel(VerifierPanel checkShowPanel) {
		PanelsManager.checkShowPanel = checkShowPanel;
	}
	public static AnalyzerPanel getAnalyseShowPanel() {
		return analyseShowPanel;
	}
	public static JTabbedPane getjTabbedPane() {
		return jTabbedPane;
	}
	public static void setjTabbedPane(JTabbedPane jTabbedPane) {
		PanelsManager.jTabbedPane = jTabbedPane;
	}
	
}
