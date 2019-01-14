package listener;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import manager.PanelsManager;

public class JTabbedPaneListener implements ChangeListener{

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		JTabbedPane jTabbedPane = (JTabbedPane) e.getSource();
		if(jTabbedPane.getSelectedIndex() == 2)//AnalyzerPanel
		{
			PanelsManager.getAnalyseShowPanel().refresh();
			PanelsManager.getAnalyseShowPanel().repaint();
		}
		else if (jTabbedPane.getSelectedIndex() == 1) //VerifierPanel
		{
			PanelsManager.getCheckShowPanel().refresh();
			PanelsManager.getCheckShowPanel().repaint();
		}
	}
}
