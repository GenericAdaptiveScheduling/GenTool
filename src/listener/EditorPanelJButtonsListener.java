package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import analyzer.Vant;
import manager.JButtonsManager;
import manager.StateManger;


public class EditorPanelJButtonsListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton) e.getSource();
		if(button == JButtonsManager.getRulesVerifyButton())
		{
			StateManger.setVerifiedState();
		}
		else if (button == JButtonsManager.getRulesAnalyseButton()) {
			StateManger.setAnalyzedState();
		}
		else if (button == JButtonsManager.getSaveButton()) {
			try {
				Vant.save();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			StateManger.setSavedState();
		}
		else if (button == JButtonsManager.getExitButton()) {
			//System.out.println(StateManger.state);
			switch (StateManger.state) { 
				case to_init:
					if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,"��һ���ǳ�ʼ������ȷ��Ҫ�˳���","����",JOptionPane.YES_NO_OPTION))
						System.exit(0);
					break;
				case to_verify:
					if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,"��һ������֤(verify)����ȷ��Ҫ�˳���","����",JOptionPane.YES_NO_OPTION))
						System.exit(0);
					break;
				case to_analyze:
					if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,"��һ���Ƿ���(analyze)����ȷ��Ҫ�˳���","����",JOptionPane.YES_NO_OPTION))
						System.exit(0);
					break;
				case to_save:
					if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,"��һ���Ǵ洢(save)����ȷ��Ҫ�˳���","����",JOptionPane.YES_NO_OPTION))
						System.exit(0);
					break;
				case finish:
					if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,"�����ɰ���pairs��rule�ļ�����ȷ��Ҫ�˳���","��Ϣ",JOptionPane.YES_NO_OPTION))
						System.exit(0);
		            break;
	        }  
		}
	}

}
