package ui.analyzer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;
import formula.Formula;
import kernel.Rule;

public class AnalyseGraphPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width = 1000;
	private int height = 400;
	Vector<GraphElement> graphElementsVector = new Vector<GraphElement>();
	int branchWidth = 160;
	Vector<String> errorVector = new Vector<String>();
	public AnalyseGraphPanel()
	{
		this.setPreferredSize(new Dimension(this.width,this.height));
		graphElementsVector.clear();
	}
	public AnalyseGraphPanel(Rule rl,Vector<String> errorVector) {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(this.width,this.height));
		graphElementsVector.clear();
		parseFormulaGraph(rl.getFormula(), this.width / 2, 20);
		this.errorVector = errorVector;
	}
	public void refresh(Rule rl,Vector<String> errorVector)
	{
		graphElementsVector.clear();
		if(rl == null)
			return;
		parseFormulaGraph(rl.getFormula(), this.width / 2, 20);
		this.errorVector = errorVector;
		repaint();
	}
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		for(int i = 0;i != graphElementsVector.size();++i)
		{
			graphElementsVector.get(i).paintSelfForVerify(g, errorVector);
			
		}
	}
	void parseFormulaGraph(Formula root,int x,int y)
	{
		GraphElement fg = new FormulaGraph(x, y, root);
		graphElementsVector.add(fg);
		if(root.getChlFmls().size() == 1)
		{
			GraphElement arw = new Arrow(x,y + 25,x,y + 55);
			graphElementsVector.add(arw);
			parseFormulaGraph(root.getChlFmls().get(0),x,y + 55);
		}
		else if (root.getChlFmls().size() == 2) {
			GraphElement arw = new Arrow(x,y + 25,x - branchWidth,y + 55);
			graphElementsVector.add(arw);
			parseFormulaGraph(root.getChlFmls().get(0),x - branchWidth,y + 55);
			GraphElement arw2 = new Arrow(x,y + 25,x + branchWidth,y + 55);
			graphElementsVector.add(arw2);
			parseFormulaGraph(root.getChlFmls().get(1),x + branchWidth,y + 55);
		}
	}
}
