package ui.editor;

import java.util.Vector;
import kernel.Context;




public class EPatsTable {
	Vector<Context> pats = new Vector<>();
	public EPatsTable() {
		// TODO Auto-generated constructor stub
	}

	public void addPattern(Context pattern) {
		this.pats.add(pattern);
	}
	public Vector<Context> getPatterns() {
		return this.pats;
	}
	public Vector<String> getPatNames() {
		Vector<String> result = new Vector<>();
		for(Context pattern: this.pats)
			result.add(pattern.getContextname());
		return result;
	}
	

	public void printPatTable() {
		for(Context pattern: this.pats)
			pattern.printContext();
	}
}
