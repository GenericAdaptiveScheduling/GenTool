package kernel;

public class Pair {

	private String ruleId;
    private String fType;
    private String fName;
    private String sType;
    private String sName;
    
    public void setRuleId(String id) {
    	ruleId = id;
    }
    
    public String getRuleId() {
    	return ruleId;
    }
    
    public void setF(Change chg) {
    	fType = chg.getType();
    	fName = chg.getName();
    }
    
    public Change getF() {
    	return new Change(fType,fName);
    }
    
    public void setS(Change chg) {
    	sType = chg.getType();
    	sName = chg.getName();
    }
    
    public Change getS() {
    	return new Change(sType,sName);
    }
    
    public void setfType(String fType) {
    	this.fType = fType;
    }
    
    public String getfType() {
    	return fType;
    }
    
    public void setfName(String fName) {
    	this.fName = fName;
    }
    
    public String getfName() {
    	return fName;
    }
    
    public void setsType(String sType) {
    	this.sType = sType;
    }
    
    public String getsType() {
    	return sType;
    }
    
    public void setsName(String sName) {
    	this.sName = sName;
    }
    
    public String getsName() {
    	return sName;
    }
    
    public String toString() {
    	return ruleId + ":<" + fType + "," + fName + ">¡ª¡ª<" + sType + "," + sName + ">\n";
    }
}
