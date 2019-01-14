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

/**
 *
 * @author bingying
 * and¹«Ê½
 */
public class AndFormula extends Formula {
    private Formula first; 
    private Formula second;
    
    public AndFormula(String name) {
        super(name);
    }
    
    @Override
	public void printFormula(int indent) {
		// TODO Auto-generated method stub
    	for(int i = 0; i < indent; ++i){
    		System.out.print(" ");
    	}
		System.out.println("and");
		first.printFormula(indent + 1);
		second.printFormula(indent + 1);
	}
    
    @Override
	public String getFormulaString() {
		// TODO Auto-generated method stub
		return "and";
	}
    
    public void setSubFormula(Formula left,Formula right) {
        first = left;
        second = right;
        addChlFml(left);
		addChlFml(right);
    }
    
    public Formula getFirst() {
    	return first;
    }

    public Formula getSecond() {
    	return second;
    }

	@Override
	public Set<String> findMFormula() {
		// TODO Auto-generated method stub
		Set<String> result = new HashSet<String>();
		result.addAll(first.findMFormula());
		result.addAll(second.findMFormula());
		return result;
	}

	@Override
	public Vector<Change> getInc1Chg() {
		Vector<Change> changes1 = first.getInc1Chg();
		Vector<Change> changes2 = second.getInc1Chg();
		Vector<Change> changes = new Vector<Change>();
		changes.addAll(changes1);
		changes.addAll(changes2);
		return changes;
	}

	@Override
	public Vector<Change> getInc2Chg() {
		Vector<Change> changes1 = first.getInc2Chg();
		Vector<Change> changes2 = second.getInc2Chg();
		Vector<Change> changes = new Vector<Change>();
		changes.addAll(changes1);
		changes.addAll(changes2);
		return changes;
	}
	
	@Override
	public Vector<Change> getInc3Chg() {
		Vector<Change> changes1 = first.getInc3Chg();
		Vector<Change> changes2 = second.getInc3Chg();
		Vector<Change> changes = new Vector<Change>();
		changes.addAll(changes1);
		changes.addAll(changes2);
		return changes;
	}
}
