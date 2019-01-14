/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kernel;



/**
 *
 * @author bingying
 * 每一个对象都是一类contexts
 */
public class Context {
    public String name;
    private boolean add;
    private boolean delete;
    private boolean update;
    
    public void setName(String contextsName) {
    	name = contextsName;
    }
    
    public String getContextname() {
    	return name;
    }
    
    public void setOp(boolean isAdd, boolean isDelete, boolean isUpdate) {
    	add = isAdd;
    	delete = isDelete;
    	update = isUpdate;
    }
    
    public boolean canAdd() {
		return add;
	}
	public boolean canDel() {
		return delete;
	}
	public boolean canUpdate() {
		return update;
	}
    
    public void printContext() {
    	System.out.println(name + ":" + add + " " + delete + " " + update);
    }
   
    @Override
    public String toString() {
        String str = new String();
        str += " ";
        str += name;
        return str;
    }
}
