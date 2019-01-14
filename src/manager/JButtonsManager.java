package manager;

import javax.swing.JButton;

public class JButtonsManager {
	static JButton rulesVerifyButton;
	static JButton rulesAnalyseButton;
	static JButton saveButton;
	static JButton exitButton;
	public static void init() {
		rulesVerifyButton = new JButton( "Verify  rules(*)");
		rulesAnalyseButton = new JButton("Analyze rules(*)");
		saveButton = new JButton(        "Save to file(*)");
		exitButton = new JButton(        "Exit");
	}
	public static JButton getRulesVerifyButton() {
		return rulesVerifyButton;
	}
	public static void setRulesVerifyButton(JButton rulesVerifyButton) {
		JButtonsManager.rulesVerifyButton = rulesVerifyButton;
	}
	public static JButton getRulesAnalyseButton() {
		return rulesAnalyseButton;
	}
	public static void setRulesAnalyseButton(JButton rulesAnalyseButton) {
		JButtonsManager.rulesAnalyseButton = rulesAnalyseButton;
	}
	
	public static JButton getSaveButton() {
		return saveButton;
	}
	public static void setSaveButton(JButton saveButton) {
		JButtonsManager.saveButton = saveButton;
	}
	
	public static JButton getExitButton() {
		return exitButton;
	}
	public static void setExitButton(JButton saveButton) {
		JButtonsManager.exitButton = saveButton;
	}
}
