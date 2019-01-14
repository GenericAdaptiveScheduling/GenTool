package ui.editor;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import kernel.Rule;
import formula.Formula;

public class MyJTree{
	/**
	 * 
	 */
	private JTree tree = null;
	public MyJTree(ERulesTable eRulesTable) {
		// TODO Auto-generated constructor stub
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("rules");
		for(Rule eRule: eRulesTable.getEditorRules())
		{
			//System.out.println("name:" + eRule.getRuleName());
			DefaultMutableTreeNode ruleNode = new DefaultMutableTreeNode(eRule.getRuleName());
			Formula formula = eRule.getFormula();
			ruleNode.add(parse(formula));
			root.add(ruleNode);
		}
		tree = new JTree();
		tree.setModel(new DefaultTreeModel(root));
	}
	
	public JTree getJTree() {
		return tree;
	}
	
	public void display(ERulesTable eRulesTable) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("rules");
		for(Rule eRule: eRulesTable.getEditorRules())
		{
			//System.out.println("name:" + eRule.getRuleName());
			DefaultMutableTreeNode ruleNode = new DefaultMutableTreeNode(eRule.getRuleName());
			Formula formula = eRule.getFormula();
			ruleNode.add(parse(formula));
			root.add(ruleNode);
		}
		tree.setModel(new DefaultTreeModel(root));
	}
	
	private DefaultMutableTreeNode parse(Formula eFormula)//parse nodes of rules
	{
		//System.out.println(root.getUserObject().toString() + "  " + eFormula.getFormulaString());
		DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(eFormula.getFormulaString());
		
		for(Formula chlFml: eFormula.getChlFmls())
		{
			treeNode.add(parse(chlFml));
		}
		return treeNode;
	}
	
	public ERulesTable getERulesTable() {
		ERulesTable eRulesTable = new ERulesTable();
		DefaultMutableTreeNode rulesNode = (DefaultMutableTreeNode) this.getJTree().getModel().getRoot();
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) this.getJTree().getLastSelectedPathComponent();
		//System.out.println("selecte:"+selectedNode.getUserObject());
		for(int i = 0;i != rulesNode.getChildCount();++i)
		{
			DefaultMutableTreeNode ruleNode = (DefaultMutableTreeNode) rulesNode.getChildAt(i);
			Rule eRule = new Rule();
			eRule.setName((String) ruleNode.getUserObject());
			if(ruleNode.equals(selectedNode))
			{
				eRule.setSelected(true);
			}
			Formula rootFormula = this.parseTreeNode((DefaultMutableTreeNode) ruleNode.getChildAt(0), eRule, selectedNode);
			eRule.setFormula(rootFormula);
			eRulesTable.addEditorRule(eRule.getRuleName(), eRule);
		}
		return eRulesTable;
	}
	private Formula parseTreeNode(DefaultMutableTreeNode node,Rule eRule,DefaultMutableTreeNode selectedNode) {
		String nodeText = (String) node.getUserObject();
		Formula fml = Formula.getFormula(nodeText);
		if(node.equals(selectedNode))
		{
			fml.setSelected(true);
			eRule.setSelected(true);
		}
		for(int i = 0;i != node.getChildCount();++i)
		{
			fml.addChlFml(parseTreeNode((DefaultMutableTreeNode)node.getChildAt(i),eRule,selectedNode)); 
		}
		return fml;
	}
}
