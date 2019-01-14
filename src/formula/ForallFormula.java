/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package formula;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import kernel.Change;
import kernel.Context;


/**
 *
 * @author bingying
 * forall
 */
public class ForallFormula extends Formula {
    private String variable;//变量名
    private String contextName;
    private Context context;//context集合
    private Formula subFormula;//子公式
    
    public ForallFormula(String name) {
        super(name);
    }
    
    @Override
	public void printFormula(int indent) {
		// TODO Auto-generated method stub
    	for(int i = 0; i < indent; ++i){
    		System.out.print(" ");
    	}
    	System.out.println("forall" + " " + variable + " in " + contextName);
    	subFormula.printFormula(indent + 1);
	}
    
    @Override
	public String getFormulaString() {
		// TODO Auto-generated method stub
		return "forall" + " " + variable + " in " + contextName;
	}
    
    public String getVariable() {
    	return variable;
    }
    
    public String getContext() {
    	return contextName;
    }
    
    public void setContext(String var,String ctx) {
        variable = var;
        contextName = ctx;
    }
    
    
    public void setContext(String var,Context ctx) {
        variable = var;
        context = ctx;
        contextName = context.getContextname();
    }
    
    public Formula getSub() {
    	return subFormula;
    }
    
    public void setSubFormula(Formula sub_formula) {
        subFormula = sub_formula;
        addChlFml(sub_formula);
    }

	@Override
	public Set<String> findMFormula() {
		// TODO Auto-generated method stub
		Set<String> result = new HashSet<String>();
		result.addAll(subFormula.findMFormula());
		result.add(contextName);
		return result;
	}

	@Override
	public Vector<Change> getInc1Chg() {
		Vector<Change> subChanges = subFormula.getInc1Chg();
		Vector<Change> changes = new Vector<Change>();
		changes.addAll(subChanges);
		if(context.canAdd())
			changes.add(new Change("add",contextName));
		return changes;
	}

	@Override
	public Vector<Change> getInc2Chg() {
		Vector<Change> subChanges = subFormula.getInc2Chg();
		Vector<Change> changes = new Vector<Change>();
		changes.addAll(subChanges);
		if(context.canDel())
			changes.add(new Change("del",contextName));
		return changes;
	}
	
	@Override
	public Vector<Change> getInc3Chg() {
		Vector<Change> subChanges = subFormula.getInc3Chg();
		Vector<Change> changes = new Vector<Change>();
		changes.addAll(subChanges);
		if(context.canUpdate())
			changes.add(new Change("upd",contextName));
		return changes;
	}
}
