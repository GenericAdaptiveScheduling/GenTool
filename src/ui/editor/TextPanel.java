package ui.editor;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import formula.Formula;
import kernel.Rule;

public class TextPanel extends JTextPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int startIndex = 0;
	int endIndex = 0;
	int count = 0;
	int fontSize = 18;
	Rule eRule;
	public TextPanel() {
		super();
		// TODO Auto-generated constructor stub
		this.setEditable(false);
	}
	public void insert(String str,SimpleAttributeSet attrset){
		  Document docs = this.getDocument();//利用getDocument()方法取得JTextPane的Document instance.0
		  try{
		    docs.insertString(docs.getLength(),str,(javax.swing.text.AttributeSet) attrset);    
		  }catch(BadLocationException ble){
		     System.out.println("BadLocationException:"+ble);
		  }
	}
	public void setYellow_Bold_20(String str)
	{
		 SimpleAttributeSet attrset = new SimpleAttributeSet();
		 StyleConstants.setForeground(attrset,Color.yellow);
		 StyleConstants.setBold(attrset,true);
		 insert(str,attrset);
		
	}
	public void setSelected(String str){
		  SimpleAttributeSet attrset = new SimpleAttributeSet();
		  StyleConstants.setBackground(attrset, Color.pink);
		  StyleConstants.setItalic(attrset,false);
		  StyleConstants.setFontSize(attrset, fontSize);
		  insert(str,attrset);
	}	
	public void setNormal(String str,boolean selected){
		  SimpleAttributeSet attrset = new SimpleAttributeSet();
		  StyleConstants.setFontFamily(attrset, "TimesRoman");
		  if(selected)
			  StyleConstants.setBackground(attrset, Color.green);
		  StyleConstants.setBold(attrset, true);
		  StyleConstants.setItalic(attrset,false);
		  StyleConstants.setFontSize(attrset, fontSize);
		  insert(str,attrset);
	}	
	public void setItalic(String str,boolean selected){
		  SimpleAttributeSet attrset = new SimpleAttributeSet();
		  StyleConstants.setFontFamily(attrset, "TimesRoman");
		  if(selected)
			  StyleConstants.setBackground(attrset, Color.green);
		  StyleConstants.setBold(attrset, true);
		  StyleConstants.setItalic(attrset,true);
		  StyleConstants.setFontSize(attrset, fontSize);
		  insert(str,attrset);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		try {
			this.getDocument().remove(0, this.getDocument().getLength());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(this.eRule == null)
			return;
		startIndex = 0;
		endIndex = 0;
		parseRuleString(this.eRule.getFormula());
	}
	void parseRuleString(Formula rootFormula)
	{
		int count = rootFormula.getChlFmls().size();
		if(count == 0)
		{
			setNormal(rootFormula.getFormulaString(), rootFormula.isSelected());
		}
		else if (count == 1) {
			setNormal(rootFormula.getFormulaString(), rootFormula.isSelected());
			setNormal("  (",false);
			parseRuleString(rootFormula.getChlFmls().get(0));
			setNormal(")",false);
		}
		else {
			setNormal("(",false);
			parseRuleString(rootFormula.getChlFmls().get(0));
			setNormal(") ",false);
			
			setStartIndex(rootFormula);
			setNormal(rootFormula.getFormulaString(),rootFormula.isSelected());
			setEndIndex(rootFormula);

			setNormal("  (",false);
			parseRuleString(rootFormula.getChlFmls().get(1));
			setNormal(")",false);
		}
	}
	private void setEndIndex(Formula rootFormula) {
		if(rootFormula.isSelected())
		{
			endIndex = this.getDocument().getLength();
		}
	}
	private void setStartIndex(Formula rootFormula) {
		if(rootFormula.isSelected())
		{
			startIndex = this.getDocument().getLength();
		}
	}

	public void display(ERulesTable eRulesTable) {
		// TODO Auto-generated method stub
		this.eRule = eRulesTable.getSelectedERule();
		this.repaint();
	}
}
