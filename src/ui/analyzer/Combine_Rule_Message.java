package ui.analyzer;

import java.util.Vector;

import kernel.Rule;

public class Combine_Rule_Message {
	Rule eRule = null;
	public Vector<String> patterns = new Vector<String>();
	String errorMessage;
	boolean truthValue;
	
	public Combine_Rule_Message(Rule rl) {
		// TODO Auto-generated constructor stub
		this.eRule = rl;
	}
	
	public String getId() {
		return eRule.getRuleName();
	}
	
	public void addPattern(String str) {
		patterns.add(str);
	}
	
	void setThisRule(Rule rl) {
		this.eRule = rl;
	}
	
	public Rule getThisRule() {
		return this.eRule;
	}
	
	public boolean getTruthValue() {
		return this.truthValue;
	}
	
	public void setTruthValue(boolean truthValue) {
		this.truthValue = truthValue;
	}

	public void setMessage(String str) {
		this.errorMessage = str;
	}
	
	public String getMessage() {
		return this.errorMessage;
	}
}
