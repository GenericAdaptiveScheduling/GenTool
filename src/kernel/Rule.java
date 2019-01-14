/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kernel;

import java.util.Set;
import java.util.Vector;

import formula.Formula;

/**
 *
 * @author bingying
 * 每一个constrain转换为Rule对象保存下来
 */
public class Rule {
    private String ruleName;
    private Formula formula;//一阶逻辑公式
	boolean isSelected = false;
    private Vector<Pair> pairs = new Vector<Pair>();
	
    public void setName(String name) {
    	ruleName = name;
    }
    
    public void printRule() {
    	System.out.println(ruleName + ":");
    	formula.printFormula(1);
    }
    
    public String getRuleName() {
    	return ruleName;
    }
    
    public void setFormula(Formula formula) {
    	this.formula = formula;
    }
    
    public Formula getFormula() {
    	return formula;
    }
    
    public void setPairs(Vector<Pair> pairs) {
    	this.pairs = pairs;
    }
    
    public Vector<Pair> getPairs() {
    	return pairs;
    }
    
    public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
    
	public boolean isSelected() {
		return this.isSelected;
	}
	
	public Set<String> findMFormula() {
		return formula.findMFormula();
	}
	
	public Vector<Change> getInc1Chg() {
		return formula.getInc1Chg();
	}
	
	public Vector<Change> getInc2Chg() {
		return formula.getInc2Chg();
	}
	
	public Vector<Change> getInc3Chg() {
		return formula.getInc3Chg();
	}
}
