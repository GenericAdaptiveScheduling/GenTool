package manager;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class FilesPathManager {
	static String curDir = System.getProperty("user.dir").replace("\\", "/")+"/resource/demo/";
	public static void reset()
	{
		curDir = System.getProperty("user.dir")+"/resource/demo/";
	}
	public static String getRulesFileName()
	{
		return curDir + "rules.xml";
	}
	public static String getSaveRulesFileName()
	{
		return curDir + "rules_save.xml";
	}
	public static String getPatternsFileName()
	{
		return curDir + "patterns.xml";
	}
	public static void setDir(String dir)
	{
		curDir = dir;
	}
	public static String getDir()
	{
		curDir = System.getProperty("user.dir").replace("\\", "/")+"/resource/";
		return curDir;
	}
	private static int selectDir()
	{
		
		String lastDir = curDir;
		JFileChooser c = new JFileChooser();
		c.setCurrentDirectory(new File(getDir()));
		c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		c.setDialogTitle("Select new workplace:");
		int result = c.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			String dir= c.getSelectedFile().getAbsolutePath()+"/";
			setDir(dir);
		}
		else {
			return -1;
		}
		File rulesFile = new File(getRulesFileName());
		File patternsFile = new File(getPatternsFileName());
		if(!rulesFile.exists() && !patternsFile.exists())
		{
			setDir(lastDir);
			return 0;
		}
		else if (rulesFile.exists() && !patternsFile.exists()) {
			setDir(lastDir);
			return 1;
		}
		else if (!rulesFile.exists() && patternsFile.exists()) {
			setDir(lastDir);
			return 2;
		}
		else {
			return 3;
		}
	}
	public static boolean changWorkPlace() {
		int result = selectDir();
		if(result == -1)
		{
		}
		else if(result == 3)
		{
			//��һ��ԭ����ˢ�²�ͬ�������� ������thisRulesˢ���ˣ�����thisTree�е�model���Ǿɵģ����ҳ�����һЩˢ��ʱ�䣬��thisRules��rulesVector��ˢ��ȥ��
			return true;
		}
		else if (result == 2) {
			JOptionPane.showMessageDialog(null, "Rules file(rules.xml) does not exist in the dir!");
		}
		else if (result == 1) {
			JOptionPane.showMessageDialog(null, "Patterns file(patterns.xml) does not exist in the dir!");
		}
		else {
			JOptionPane.showMessageDialog(null, "Patterns file(patterns.xml) and Rules File(rules.xml) do not exist in the dir!");
		}
		return false;
	}
}
