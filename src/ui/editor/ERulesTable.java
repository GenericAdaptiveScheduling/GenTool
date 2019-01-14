package ui.editor;

import java.util.Hashtable;
import java.util.Vector;

import kernel.Rule;


public class ERulesTable{
	Hashtable<String, Rule> editorRuleHashtable = new Hashtable<>();
	GraphPanel graph = null;
	TextPanel textpane = null;
	MyJTree tree = null;
	public ERulesTable() {
		// TODO Auto-generated constructor stub
		graph = new GraphPanel();
		textpane = new TextPanel();
	}

	public void setPanel() {
		tree = new MyJTree(this);
	}
	
	public GraphPanel getGraph() {
		return graph;
	}
	
	public TextPanel getText() {
		return textpane;
	}
	
	public MyJTree getTree() {
		return tree;
	}
	
	public void addEditorRule(String ruleName,Rule editorRule)
	{
		this.editorRuleHashtable.put(ruleName, editorRule);
	}
	
	public void repaintRelatedPanels(ERulesTable table)
	{
		graph.display(table);
		textpane.display(table);
		//tree.display(table);
	}
	
	public Rule getSelectedERule() {
		for(Rule eRule: this.editorRuleHashtable.values())
		{
			if(eRule.isSelected())
				return eRule;
		}
		if(editorRuleHashtable.isEmpty())
		{
			return null;
		}
		else {
			return this.getEditorRules().get(0);
		}
	}
	
	public Rule getEditorRule(String ruleName) {
		return this.editorRuleHashtable.get(ruleName);
	}
	
	public Vector<Rule> getEditorRules()
	{
		Vector<Rule> resultEditorRules = new Vector<>();
		for(Rule editorRule:this.editorRuleHashtable.values())
			resultEditorRules.add(editorRule);
		return resultEditorRules;
	}
	
	public Rule getEditorRuleByIndex(int index)
	{
		return this.getEditorRules().get(index);
	}
	
	public void setFirstSlected() {
		if(!this.editorRuleHashtable.isEmpty())
			this.setSelected(0);
	}
	
	public void setSelected(int index) {
		for(Rule eRule:editorRuleHashtable.values())
		{
			eRule.setSelected(false);
		}
		this.getEditorRuleByIndex(index).setSelected(true);
	}
	
	public void printERulesTable() {
		for(Rule eRule: this.editorRuleHashtable.values())
		{
			System.out.println("--------------------------------");
			System.out.println(eRule.getRuleName());
			eRule.printRule();
		}
	}
}
