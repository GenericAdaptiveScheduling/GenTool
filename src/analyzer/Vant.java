package analyzer;

import java.io.FileNotFoundException;
import java.util.Vector;

import ui.analyzer.Combine_Rule_Message;
import kernel.Change;
import kernel.Context;
import kernel.Pair;
import kernel.Rule;
import loader.EPatsTableLoader;
import loader.ERulesTableLoader;

public class Vant {

	private Vector<Context> patList = null;  // Loaded patterns

	private static Vector<Rule> ruleList = null;  // Loaded rules

	Vector<Combine_Rule_Message> crmTrue = new Vector<>();
	Vector<Combine_Rule_Message> crmFalse = new Vector<>();
	
	String rulesFileName;
	String patternsFileName;
	
	public Vector<Combine_Rule_Message> getCrmTrue() {
		return this.crmTrue;
	}
	public Vector<Combine_Rule_Message> getCrmFalse() {
		return this.crmFalse;
	}
	public Vant(String rulesFileName_,String patternsFileName_) {
		// TODO Auto-generated constructor stub
		rulesFileName = rulesFileName_;
		patternsFileName = patternsFileName_;
		this.load();
		this.analyzeRules();
	}
	
	private void load() {
	
		// Load patterns and rules for consistency constraints
		patList = EPatsTableLoader.getPatsTable(patternsFileName).getPatterns();
		ruleList = ERulesTableLoader.getRulesTable(patList,rulesFileName).getEditorRules();
		//System.out.println(patList.size() + " pattern(s) loaded");
		//System.out.println(ruleList.size() + " rule(s) loaded");
		//System.currentTimeMillis();
	}
	
	private String watchvcs(Vector<Pair> pairs,Combine_Rule_Message crm) {
		assert pairs != null && pairs.size() > 0;
		String result = "";
		for (int i = 0;i < pairs.size();i++) {
			String order = "";
			if(i < 9)
				order = "  " + (i+1);
			else
				order = "" + (i+1);
			Pair pair = pairs.get(i);
			crm.addPattern(pair.getfName());
			crm.addPattern(pair.getsName());
			result += "      " + order + ": (<" + pair.getfType() + ", " + pair.getfName()
				+ ">, <" + pair.getsType() + ", " + pair.getsName() + ">)\n";
		}
		return result;
	}
	
	private void analyzeOne(Rule rule, Combine_Rule_Message crm) {
		
		assert rule != null;
		
		//System.out.println("----------------------------------------");
		
		// Find all formulae with patterns
		String errorString = "";
		//Set<String> mulFormula = rule.findMFormula();
		//int size = mulFormula.size();
		//errorString += rule.getRuleName() + ": " + size + " context set(s)" + "\n";
		
		Vector<Change> incAdd = rule.getInc1Chg();
		Vector<Change> incDelete = rule.getInc2Chg();
		Vector<Change> incUncertain = rule.getInc3Chg();
		
		Vector<Change> add = new Vector<Change>();
		Vector<Change> delete = new Vector<Change>();
		
		add.addAll(incAdd);
		add.addAll(incUncertain);
		delete.addAll(incDelete);
		delete.addAll(incUncertain);
		
		// Explore all combinations
		Vector<Pair> pairs = new Vector<Pair>();  // suspicious pairs set
		for(int i = 0;i < add.size();i++) {
			for(int j = 0;j < delete.size();j++) {
				Pair pair = new Pair();
				pair.setRuleId(rule.getRuleName());
				pair.setF(add.get(i));
				pair.setS(delete.get(j));
				pairs.add(pair);
			}
		}
		rule.setPairs(pairs);
		//System.out.println(rule.getRuleName() + ": " + size + " context set(s) " + pairs.size() + " suspicious pair(s)");
		
		errorString += rule.getRuleName() + ": " + pairs.size() + " s-condition(s)" + "\n";
		//int total = 9 * size * size;
		// Summary
		if (pairs.size() > 0) {
			crm.setTruthValue(false);
			//errorString += "      => there is(are) " + pairs.size() + " s-condition(s) of " + rule.getRuleName() + "\n";//suspicious pair(s)
			errorString += watchvcs(pairs,crm);
			crm.setMessage(errorString);
		} else {  // 0
			crm.setTruthValue(true);
			//errorString += "      => there is no suspicious pair of " + rule.getRuleName();
			crm.setMessage(errorString);
		}
	}

	private void analyzeRules() {
		
		long startTime = System.nanoTime();
		
		int pairNum = 0;
		for (int i = 0;i < ruleList.size();i++) {
			Rule eRule = ruleList.get(i);
			Combine_Rule_Message crm = new Combine_Rule_Message(eRule);
			analyzeOne(eRule, crm);
			pairNum += eRule.getPairs().size();
			if(crm.getTruthValue())
				this.crmTrue.add(crm);
			else {
				this.crmFalse.add(crm);
			}
		}
		long endTime = System.nanoTime();
		double time = (double)(endTime - startTime) / 1000000;
		System.out.println(ruleList.size() + " rule(s)  " + patList.size() + " context set(s)  " + pairNum + " suspicious pair(s)");
		System.out.println("total time: " + time + "ms");
	}
	
	public static void save() throws FileNotFoundException {
		ERulesTableLoader.getAndSave(ruleList);
	}
	
}