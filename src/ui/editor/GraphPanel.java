package ui.editor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

import kernel.Rule;
import ui.analyzer.Arrow;
import ui.analyzer.FormulaGraph;
import ui.analyzer.GraphElement;
import formula.Formula;


public class GraphPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Vector<GraphElement> graphElementsVector = new Vector<GraphElement>();
	private int width = 1000;
	private int height = 400;
	int branchWidth = 160;
	public GraphPanel() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(this.width,this.height));
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		try {
			for(int i = 0;i != graphElementsVector.size();++i)
			{
				graphElementsVector.get(i).paintSelf(g);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
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

	public void display(ERulesTable eRulesTable) {
		this.graphElementsVector.clear();
		Rule eRule = eRulesTable.getSelectedERule();
		if(eRule == null)
		{
			repaint();
		}
		else {
			this.parseFormulaGraph(eRulesTable.getSelectedERule().getFormula(), this.width / 2, 20);
			repaint();
		}
	}
}
