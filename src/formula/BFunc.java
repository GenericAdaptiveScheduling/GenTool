/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package formula;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import kernel.Change;

/**
 *
 * @author bingying
 * 各种函数
 */
public class BFunc extends Formula {
    //bfunc的参数存储
    private HashMap<String,Param> params = new HashMap<String,Param>();
    
    public BFunc(String name) {
        super(name);
    }
    
    public static BFunc getBfunc(String formulaName)
	{
		int indexLow = formulaName.indexOf("(");
		if(indexLow == -1)
			return new BFunc(formulaName);
		BFunc efBfunc = new BFunc(formulaName.substring(0, indexLow));
		int indexHign;
		int position = 1;
		while(formulaName.indexOf(",", indexLow + 1) != -1)
		{
			indexHign = formulaName.indexOf(",", indexLow+1);
			Param param = Param.getParam(formulaName.substring(indexLow + 1, indexHign));
			efBfunc.setParam(String.valueOf(position++), param.getVar(), param.getField());
			indexLow = indexHign;
		}
		indexHign = formulaName.indexOf(")");
		Param param = Param.getParam(formulaName.substring(indexLow+1, indexHign));
		efBfunc.setParam(String.valueOf(position++), param.getVar(), param.getField());
		return efBfunc;
	}
    
    @Override
	public void printFormula(int indent) {
		// TODO Auto-generated method stub
    	for(int i = 0; i < indent; ++i){
    		System.out.print(" ");
    	}
		System.out.println(name);
		HashMap<String,Param> params = getParam(); 
		for (Iterator<String> it =  params.keySet().iterator();it.hasNext();) {
			for(int i = 0; i < indent + 1; ++i){
	    		System.out.print(" ");
	    	}
			Object key = it.next();
        	System.out.println(key + " " + params.get(key).var + " " + params.get(key).field);
		}
	}
    
    @Override
	public String getFormulaString() {
		// TODO Auto-generated method stub
		String fmlText = new String();
		fmlText += name;
		if(!this.params.isEmpty())
		{
			fmlText += "(";
			HashMap<String,Param> params = getParam(); 
			for (Iterator<String> it =  params.keySet().iterator();it.hasNext();) {
				
				Object key = it.next();
				fmlText += params.get(key).var + "." + params.get(key).field;
				fmlText += ",";
			}
			fmlText = fmlText.substring(0, fmlText.length() - 1);
			fmlText += ")";
			
		}
		return fmlText;
	}
    
    public HashMap<String,Param> getParam() {
    	return params;
    }
    
    public void setParam(HashMap<String,Param> params) {
    	this.params = params;
    }
    
    public void setParam(String pos, String var, String field) {
        if (params.get(pos) == null) {
            params.put(pos, new Param(var, field));
        } else {  // pos should be unique
            System.out.println("incorrect position");
            System.exit(1);
        }
    }

    @Override
	public Set<String> findMFormula() {
		// TODO Auto-generated method stub
		Set<String> result = new HashSet<String>();
		return result;
	}
	
	@Override
	public Vector<Change> getInc1Chg() {
		return new Vector<Change>();
	}

	@Override
	public Vector<Change> getInc2Chg() {
		return new Vector<Change>();
	}
	
	@Override
	public Vector<Change> getInc3Chg() {
		return new Vector<Change>();
	}
}
