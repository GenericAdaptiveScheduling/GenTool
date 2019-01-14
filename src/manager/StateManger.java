package manager;

import java.util.Vector;

import javax.swing.JTabbedPane;
public class StateManger {
	public static enum EditorPanleState{to_init,to_verify,to_analyze,to_save,finish};
	public static EditorPanleState state;
	static Vector<Boolean> backState=new Vector<>();//用来保存中间3个JButton的状态
	public static void setStartupState()
	{
		state = EditorPanleState.to_init;
		JTabbedPane jTabbedPane=PanelsManager.getjTabbedPane();
		jTabbedPane.setEnabledAt(0, false);
		jTabbedPane.setEnabledAt(1, false);
		jTabbedPane.setEnabledAt(2, false);
		jTabbedPane.setEnabledAt(3, true);
		jTabbedPane.setSelectedIndex(3);
		jTabbedPane.setTitleAt(0, "Editor");
		jTabbedPane.setTitleAt(1, "Verifier");
		jTabbedPane.setTitleAt(2, "Analyzer");
		jTabbedPane.setTitleAt(3, "Startup Options");
	}
	public static void setInitState()
	{
		state = EditorPanleState.to_verify;
		JButtonsManager.getSaveButton().setEnabled(false);
		JButtonsManager.getRulesAnalyseButton().setEnabled(false);
		JButtonsManager.getRulesVerifyButton().setEnabled(true);
		
		JTabbedPane jTabbedPane = PanelsManager.getjTabbedPane();
		jTabbedPane.setEnabledAt(0, true);
		jTabbedPane.setEnabledAt(1, false);
		jTabbedPane.setEnabledAt(2, false);
		jTabbedPane.setEnabledAt(3, false);
		jTabbedPane.setSelectedIndex(0);
		jTabbedPane.setTitleAt(0, "Editor");
		jTabbedPane.setTitleAt(1, "Verifier(*)");
		jTabbedPane.setTitleAt(2, "Analyzer(*)");
		jTabbedPane.setTitleAt(3, "Startup Options");
	}
	public static void setCheckTrueState() {
		JButtonsManager.getRulesAnalyseButton().setEnabled(true);
	}
	public static void setCheckFalseState() {
		JButtonsManager.getRulesAnalyseButton().setEnabled(false);
	}
	public static void setVerifiedState()
	{
		state = EditorPanleState.to_analyze;
		JButtonsManager.getRulesVerifyButton().setEnabled(false);
		JButtonsManager.getRulesVerifyButton().setText("Verify rules");
		JButtonsManager.getRulesAnalyseButton().setEnabled(true);
		JButtonsManager.getSaveButton().setEnabled(false);
		
		JTabbedPane jTabbedPane=PanelsManager.getjTabbedPane();
		jTabbedPane.setEnabledAt(0, true);
		jTabbedPane.setEnabledAt(1, true);
		jTabbedPane.setEnabledAt(2, false);
		jTabbedPane.setEnabledAt(3, false);
		jTabbedPane.setSelectedIndex(1);
		jTabbedPane.setTitleAt(0, "Editor");
		jTabbedPane.setTitleAt(1, "Verifier");
		jTabbedPane.setTitleAt(2, "Analyzer(*)");
		jTabbedPane.setTitleAt(3, "Startup Options");
	}
	public static void setAnalyzedState()
	{
		state = EditorPanleState.to_save;
		JButtonsManager.getRulesVerifyButton().setEnabled(false);
		JButtonsManager.getRulesAnalyseButton().setEnabled(false);
		JButtonsManager.getRulesAnalyseButton().setText("Analyze rules");
		JButtonsManager.getSaveButton().setEnabled(true);
		
		JTabbedPane jTabbedPane=PanelsManager.getjTabbedPane();
		jTabbedPane.setEnabledAt(0, true);
		jTabbedPane.setEnabledAt(1, true);
		jTabbedPane.setEnabledAt(2, true);
		jTabbedPane.setEnabledAt(3, false);
		jTabbedPane.setSelectedIndex(2);
		jTabbedPane.setTitleAt(0, "Editor");
		jTabbedPane.setTitleAt(1, "Verifier");
		jTabbedPane.setTitleAt(2, "Analyzer");
		jTabbedPane.setTitleAt(3, "Startup Options");
	}
	public static void setSavedState()
	{
		state = EditorPanleState.finish;
		JButtonsManager.getSaveButton().setEnabled(false);
		JButtonsManager.getSaveButton().setText("Save to file");
	}
}
