package ui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import analyzer.Vant;
import manager.FilesPathManager;
import ui.analyzer.AnalyseGraphPanel;
import ui.analyzer.Combine_Rule_Message;

import java.awt.*;
import java.util.Vector;



public class AnalyzerPanel extends JPanel implements TreeSelectionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Combine_Rule_Message> crmTrueVector = new Vector<Combine_Rule_Message>();
	private Vector<Combine_Rule_Message> crmFalseVector = new Vector<Combine_Rule_Message>();
	JTextPane jTextPane = new JTextPane();
	JTree jTree = new JTree();
	AnalyseGraphPanel graphPanel = new AnalyseGraphPanel();
	public AnalyzerPanel() {
		// TODO Auto-generated constructor stub
		
		jTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		jTree.setEditable(false);
		jTree.addTreeSelectionListener(this);
		jTextPane.setEditable(false);

		JScrollPane jtt = new JScrollPane(jTree);
		JScrollPane jgp = new JScrollPane(graphPanel);
		JScrollPane jTextPanelJScrollPane = new JScrollPane(this.jTextPane);
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		jtt.setMinimumSize(new Dimension(300,350));
		jgp.setPreferredSize(new Dimension(800,350));
		jTextPanelJScrollPane.setMinimumSize(new Dimension(800,80));
		JSplitPane jsTop = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jtt,jgp);
		JSplitPane jsMain = new JSplitPane(JSplitPane.VERTICAL_SPLIT,jsTop,jTextPanelJScrollPane);
		jsTop.setResizeWeight(0);
		jsMain.setResizeWeight(0.5);
		this.add(jsMain);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
	}
	public void refresh()
	{
		String rulesFileName = FilesPathManager.getRulesFileName();
		String patternsFileName = FilesPathManager.getPatternsFileName();
		Vant vant = new Vant(rulesFileName, patternsFileName);
		this.crmFalseVector = vant.getCrmFalse();
		this.crmTrueVector = vant.getCrmTrue();
		DefaultMutableTreeNode rootTreeNode = new DefaultMutableTreeNode("rules");
		DefaultMutableTreeNode righTreeNode = new DefaultMutableTreeNode("s-condition unrealated rules");
		DefaultMutableTreeNode wrongTreeNode = new DefaultMutableTreeNode("s-condition related rules");
		for(int i = 0;i != crmFalseVector.size();++i)
		{
			wrongTreeNode.add((MutableTreeNode)new DefaultMutableTreeNode(crmFalseVector.get(i).getId()));
		}
		for(int i = 0;i != crmTrueVector.size();++i)
		{
			righTreeNode.add((MutableTreeNode)new DefaultMutableTreeNode(crmTrueVector.get(i).getId()));
		}
		rootTreeNode.add((MutableTreeNode) wrongTreeNode);
		rootTreeNode.add((MutableTreeNode) righTreeNode);
		jTree.setModel(new DefaultTreeModel(rootTreeNode));
		jTree.updateUI();
		
		if(!crmFalseVector.isEmpty())
		{
			graphPanel.refresh(crmFalseVector.get(0).getThisRule(),crmFalseVector.get(0).patterns);
			jTextPane.setText(crmFalseVector.get(0).getMessage());
		}
		else if (!crmTrueVector.isEmpty()) {
			graphPanel.refresh(crmTrueVector.get(0).getThisRule(),crmTrueVector.get(0).patterns);
			jTextPane.setText(crmTrueVector.get(0).getMessage());
		}
		else {
			graphPanel.refresh(null, null);
			jTextPane.setText("");
		}
		jTextPane.setCaretPosition(0);
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		DefaultMutableTreeNode selectedNode = null;
		if(jTree.isSelectionEmpty())
		{
			return;
		}
		else {
			selectedNode = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
		}
		if(selectedNode.isRoot())
		{
			return;
		}
		else {
			if(((DefaultMutableTreeNode)selectedNode.getParent()).isRoot())
				return;
		}
		DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();
		Combine_Rule_Message crm;
		if(parentNode.getParent().getIndex(parentNode) == 0)
		{
			crm = crmFalseVector.get(parentNode.getIndex(selectedNode));
		}
		else {
			crm = crmTrueVector.get(parentNode.getIndex(selectedNode));
		}
		graphPanel.refresh(crm.getThisRule(),crm.patterns);
		jTextPane.setText(crm.getMessage());
		jTextPane.setCaretPosition(0);

	}
}
