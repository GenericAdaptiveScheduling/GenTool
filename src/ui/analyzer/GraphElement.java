package ui.analyzer;
import java.awt.Graphics;
import java.util.Vector;
public interface GraphElement {
	void paintSelf(Graphics g);
	void paintSelfForVerify(Graphics g,Vector<String> errorVector);
}
