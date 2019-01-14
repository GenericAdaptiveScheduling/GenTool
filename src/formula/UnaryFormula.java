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
 * 一元操作公式，not
 */
public class UnaryFormula extends Formula {
    private Formula formula;
    
    public UnaryFormula(String name) {
        super(name);
    
    }
    
    @Override
	public void printFormula(int indent) {
		// TODO Auto-generated method stub
    	for(int i = 0; i < indent; ++i){
    		System.out.print(" ");
    	}
		System.out.println("not");
		formula.printFormula(indent + 1);
	}

	@Override
	public String getFormulaString() {
		// TODO Auto-generated method stub
		return "not";
	}
    
    public void setSubFormula(Formula sub_formula) {
        formula = sub_formula;
        addChlFml(sub_formula);
    }
    
    public Formula getFormula() {
    	return formula;
    }

    @Override
	public Set<String> findMFormula() {
		// TODO Auto-generated method stub
		Set<String> result = new HashSet<String>();
		result.addAll(formula.findMFormula());
		return result;
	}
	
	@Override
	public Vector<Change> getInc1Chg() {
		return formula.getInc2Chg();
	}

	@Override
	public Vector<Change> getInc2Chg() {
		return formula.getInc1Chg();
	}
	
	@Override
	public Vector<Change> getInc3Chg() {
		return formula.getInc3Chg();
	}
}
