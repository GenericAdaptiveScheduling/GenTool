/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package formula;

import java.util.Set;
import java.util.Vector;

import kernel.Change;


/**
 *
 * @author bingying
 * formula
 */
public abstract class Formula {
    protected String name;//公式类型
    private boolean isSelected = false;
    Vector<Formula> chlFmls = new Vector<Formula>();//子公式
	Formula parentEFormula;//父公式

    public Formula(String fName) {
        name = fName;
    }
    
    public String getName() {
    	return name;
    }
    
    public void setSelected(boolean isSelected) {
    	this.isSelected = isSelected;
    }
    
    public boolean isSelected() {
    	return isSelected;
    }
    
    public Formula getParentFormula() {
		return this.parentEFormula;
	}
    
	public void setParentFormula(Formula parentEFormula) {
		this.parentEFormula = parentEFormula;
	}
	
	public void addChlFml(Formula chlFormula) {
		chlFormula.setParentFormula(this);
		this.chlFmls.add(chlFormula);
	}
	
	public Vector<Formula> getChlFmls() {
		return this.chlFmls;
	}
	
	public static Formula getFormula(String formulaName) {
		if(formulaName.equals("and"))
		{
			return new AndFormula("and");
		}
		else if (formulaName.equals("or")) {
			return new OrFormula("or");
		}
		else if (formulaName.equals("not")) {
			return new UnaryFormula("not");
		}
		else if (formulaName.equals("implies")) {
			return new ImpliesFormula("implies");
		}
		else if(formulaName.indexOf("exists") != -1)
		{
			ExistsFormula resultFormula = new ExistsFormula("exists");
			resultFormula.setContext(getVar(formulaName), getPat(formulaName));
			return resultFormula;
		}
		else if (formulaName.indexOf("forall")!=-1) {
			ForallFormula resultFormula = new ForallFormula("forall");
			resultFormula.setContext(getVar(formulaName), getPat(formulaName));
			return resultFormula;
		}
		else {
			return BFunc.getBfunc(formulaName);
		}
	}
	private static String getVar(String formulaName) {
		int indexOne = formulaName.indexOf(" ");
		int indexTwo = formulaName.indexOf("in", indexOne + 1);
		return formulaName.substring(indexOne + 1, indexTwo - 1);
	}
	private static String getPat(String formulaName) {
		int index = formulaName.indexOf("in");
		return formulaName.substring(index + 3);
	}
	
    public abstract void printFormula(int indent);
    public abstract Set<String> findMFormula();
    public abstract String getFormulaString();
    public abstract Vector<Change> getInc1Chg();
	public abstract Vector<Change> getInc2Chg();
	public abstract Vector<Change> getInc3Chg();

}
