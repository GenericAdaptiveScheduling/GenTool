package formula;

public class Param {
	public String var = null, field = null;

    public Param(String _var, String _field) {

        var = _var;
        field = _field;
    }
	
    public String getVar() {
		return var;
	}
	
	public String getField() {
		return field;
	}

    public static Param getParam(String paramString)
	{
		int index = paramString.indexOf(".");
		if(index == -1)
		{
			return new Param(paramString, "");
		}
		else {
			String var = paramString.substring(0, index);
			String field = paramString.substring(index+1);
			return new Param(var, field);
		}
		
	}
}