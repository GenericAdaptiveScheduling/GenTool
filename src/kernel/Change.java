package kernel;

public class Change {
    private String type;
    private String name;
    
    public Change(String type,String name) {
    	this.type = type;
    	this.name = name;
    }
    
    public void setType(String type) {
    	this.type = type;
    }
    
    public String getType() {
    	return type;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getName() {
    	return name;
    }
    
    public String toString() {
    	return type + "," + name + "\n";
    }
}
