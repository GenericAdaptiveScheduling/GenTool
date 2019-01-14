package ui.analyzer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Vector;

public class Arrow implements GraphElement{
	int x1,y1,x2,y2;
	public Arrow(int x1,int y1,int x2,int y2) {
		// TODO Auto-generated constructor stub
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	void drawArrow(Graphics g,int x1,int y1,int x2,int y2)//ÒªÇóy1<y2
	{
		if(x1 == x2)
		{
			g.fillRect(x1 - 1, y1, 2,y2 - y1 - 10);
			Polygon trianglePolygon=new Polygon();
			trianglePolygon.addPoint(x1 - 7, y2 - 10);
			trianglePolygon.addPoint(x1, y2);
			trianglePolygon.addPoint(x1 + 7,y2 - 10);
			g.fillPolygon(trianglePolygon);
		}
		else 
		{
			g.fillRect(x1 - 1, y1, 2, 10);
			if(x1 > x2)
			{
				g.fillRect(x2 - 1, y1 + 8, (x1 - x2)+2, 2);
			}
			else 
			{
				g.fillRect(x1 - 1, y1 + 8, (x2 - x1)+2, 2);
			}
			drawArrow(g, x2, y1 + 10, x2, y2);
		}
	}
	@Override
	public void paintSelf(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.black);
		drawArrow(g, x1, y1, x2, y2);
	}
	@Override
	public void paintSelfForVerify(Graphics g, Vector<String> errorVector) {
		// TODO Auto-generated method stub
		g.setColor(Color.black);
		drawArrow(g, x1, y1, x2, y2);
	}

}
