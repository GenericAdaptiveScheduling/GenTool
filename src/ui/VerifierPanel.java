package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import formula.BFunc;
import formula.ExistsFormula;
import formula.ForallFormula;
import formula.Formula;
import formula.Param;
import kernel.Rule;
import loader.EPatsTableLoader;
import loader.ERulesTableLoader;
import manager.FilesPathManager;
import manager.StateManger;
import ui.editor.EPatsTable;
import ui.editor.ERulesTable;


public class VerifierPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextPane textPane = new JTextPane();
	Vector<String> errorLogs = new Vector<String>();
	public VerifierPanel() {
		// TODO Auto-generated constructor stub
		textPane.setPreferredSize(new Dimension(800,600));
		textPane.setEditable(false);
		this.add(textPane);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		String text=new String();
		for(String string:this.errorLogs)
		{
			text += string+"\n";
		}
		if(this.errorLogs.isEmpty())
		{
			text = "All rules are correct!";
		}
		this.textPane.setText(text);
	}
	public void refresh()
	{
		this.errorLogs.clear();
		EPatsTable ePatsTable = EPatsTableLoader.getPatsTable(FilesPathManager.getPatternsFileName());
		ERulesTable eRulesTable = ERulesTableLoader.getRulesTable(ePatsTable.getPatterns(),FilesPathManager.getRulesFileName());
		for(Rule eRule: eRulesTable.getEditorRules())
		{
			this.checkOneFormula(eRule.getFormula(), ePatsTable, eRule);
		}
		if(this.errorLogs.isEmpty())
		{
			StateManger.setCheckTrueState();
		}
		else {
			StateManger.setCheckFalseState();
		}
	}
	private void checkOneFormula(Formula formula,EPatsTable ePatsTable,Rule eRule)
	{
		if (formula instanceof ForallFormula) {
			ForallFormula forall = (ForallFormula) formula;
			String pat = forall.getContext();
			if(!ePatsTable.getPatNames().contains(pat))
				this.errorLogs.add("In rule:\"" + eRule.getRuleName()+"\", formula:\"" + formula.getFormulaString()+"\", pattern " + pat + " doesn,t exits in patsTable!");
		}
		else if (formula instanceof ExistsFormula) {
			ExistsFormula exists = (ExistsFormula)formula;
			String pat = exists.getContext();
			if(!ePatsTable.getPatNames().contains(pat))
				this.errorLogs.add("In rule:\"" + eRule.getRuleName() + "\", formula:\"" + formula.getFormulaString() + "\", pattern " + pat + " doesn,t exits in patsTable!");
		}
		else if (formula instanceof BFunc) {
			Vector<String> definedVars = new Vector<String>();
			Formula parentEFormula = formula.getParentFormula();
			while(parentEFormula != null)
			{
				if(parentEFormula instanceof ForallFormula)
				{
					ForallFormula forall = (ForallFormula) parentEFormula;
					definedVars.add(forall.getVariable());
				}
				else if(parentEFormula instanceof ExistsFormula) {
					ExistsFormula exists = (ExistsFormula) parentEFormula;
					definedVars.add(exists.getVariable());
				}
				parentEFormula = parentEFormula.getParentFormula();
			}
			BFunc efBfunc = (BFunc) formula;
			HashMap<String,Param> params = efBfunc.getParam(); 
			for (Iterator<String> it =  params.keySet().iterator();it.hasNext();) {
				
				Object key = it.next();
				String var = params.get(key).var;
				if(!definedVars.contains(var) && !params.get(key).getField().isEmpty())
				{
					this.errorLogs.add("In rule:\""+eRule.getRuleName()+"\", formula:\""+formula.getFormulaString()+"\", pattern "+var+" isn't defined in above nodes!");
				}
			}
		}
		for(Formula chlFml: formula.getChlFmls())
		{
			this.checkOneFormula(chlFml, ePatsTable, eRule);
		}
	}
	public void reset(String str)
	{
		textPane.setText(str);
	}
}
