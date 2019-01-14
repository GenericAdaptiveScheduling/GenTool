package ui.analyzer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Vector;

import formula.ExistsFormula;
import formula.ForallFormula;
import formula.Formula;

public class FormulaGraph implements GraphElement{
	Formula eFormula;
	int x,y;
	boolean highLight;
	int fontSize = 18;
	float fontWidth = 8.7f;
	int stringIndex;
	Font normalFont = new Font("TimesRoman",Font.BOLD,fontSize);
	Font italicFont = new Font("TimesRoman",Font.BOLD + Font.ITALIC,fontSize);//这里的字体在Ubuntu下是13
	public FormulaGraph(int x,int y,Formula eFormula) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.eFormula = eFormula;
	} 
	public void paintSelfForVerify(Graphics g,Vector<String> errorVector)
	{
		int stringWidth = Math.round(this.eFormula.getFormulaString().length()*fontWidth);
		if(this.eFormula instanceof ForallFormula )
		{
			ForallFormula formula = (ForallFormula) this.eFormula;
			if(errorVector.size()!=0)
			{
				if(errorVector.indexOf(formula.getContext()) != -1)
				{
					g.setColor(Color.white);
					g.fillRoundRect(x - stringWidth / 2 - 10, y, stringWidth + 20, 25,10,10);
					stringIndex = x - stringWidth / 2;
					normalInsert(g, "forall " + formula.getVariable() + " in ");
					redInsert(g, formula.getContext());
				}
			}
		}
		else if(this.eFormula instanceof ExistsFormula )
		{
			ExistsFormula formula = (ExistsFormula) this.eFormula;
			if(errorVector.size()!=0)
			{
				if(errorVector.indexOf(formula.getContext()) != -1)
				{
					g.setColor(Color.white);
					g.fillRoundRect(x - stringWidth / 2 - 10, y, stringWidth + 20, 25,10,10);
					stringIndex = x - stringWidth / 2;
					normalInsert(g, "exists " + formula.getVariable() + " in ");
					redInsert(g, formula.getContext());
				}
			}
		}
		else {
			g.setColor(Color.gray);
			g.fillRoundRect(x-stringWidth/2-10, y, stringWidth+20, 25,10,10);
			stringIndex=x-stringWidth/2;
			normalInsert(g, this.eFormula.getFormulaString());
		}
	}
	
	private void redInsert(Graphics g, String stringToDraw) {
		g.setFont(normalFont);
		g.setColor(Color.red);
		g.drawString(stringToDraw,stringIndex - 10, y + 20);//这里的-10是权宜之计，是为了消除空格
		stringIndex=stringIndex+Math.round(stringToDraw.length() * fontWidth);
	}
	
	private void normalInsert(Graphics g, String stringToDraw) {
		g.setFont(normalFont);
		g.setColor(Color.black);
		g.drawString(stringToDraw,stringIndex, y + 20);
		stringIndex = stringIndex + Math.round(stringToDraw.length() * fontWidth);
	}
	
	@Override
	public void paintSelf(Graphics g) {
		// TODO Auto-generated method stub
		int stringWidth = Math.round(this.eFormula.getFormulaString().length() * fontWidth);
		if(this.eFormula.isSelected())
			g.setColor(Color.white);
		else {
			g.setColor(Color.gray);
		}
		g.fillRoundRect(x - stringWidth / 2 - 10, y, stringWidth + 20, 25,10,10);
		stringIndex = x - stringWidth / 2;
		normalInsert(g, this.eFormula.getFormulaString());
	}

}
