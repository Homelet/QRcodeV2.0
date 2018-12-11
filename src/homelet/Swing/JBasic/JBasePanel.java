/**
 * <pre>
 * ****************************************************
 * Name: TODO
 * Date: TODO
 * Description: TODO
 * *****************************************************
 * </pre>
 *
 * @author HomeletWei
 * @date May 13, 2018
 */
package homelet.Swing.JBasic;

import homelet.Swing.EnableListener;
import homelet.Swing.RestoreDefault;
import homelet.Utile.ColorBank;
import homelet.Utile.Constants.BorderStyle;
import homelet.Utile.GraphicsHandler;
import homelet.Utile.Utility;

import javax.swing.*;
import java.awt.*;

/**
 * TODO
 *
 * @author HomeletWei
 * @date May 13, 2018
 */
public class JBasePanel extends JPanel implements EnableListener, RestoreDefault{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	boolean   showGrid = false;
	ColorBank colorBank;
	
	/**
	 * constructor for JPanel
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public JBasePanel(){
		this.colorBank = new ColorBank();
	}
	
	/**
	 * @author HomeletWei
	 * @see #dismissGrid()
	 */
	public void showGrid(){
		this.showGrid = true;
	}
	
	/**
	 * @author HomeletWei
	 * @see #showGrid()
	 */
	public void dismissGrid(){
		this.showGrid = false;
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		if(showGrid)
			paintInner((Graphics2D) g, this, new Point(0, 0), 0);
	}
	
	private void paint(Graphics2D g, Point vertex, Component c, int layerIndex){
		Point     innerVertex = Utility.getUtility().sumPoint(vertex, c.getLocation());
		Dimension size        = c.getSize();
		GraphicsHandler.getGraphicsHandler().drawBorder(g, innerVertex, size, 1, BorderStyle.RECTANGULAR, colorBank.pollColor(layerIndex));
		GraphicsHandler.getGraphicsHandler().drawLine(g, innerVertex.x, innerVertex.y, size.width, size.height);
		GraphicsHandler.getGraphicsHandler().drawLine(g, innerVertex.x + size.width, innerVertex.y, -size.width, size.height);
	}
	
	private void paintInner(Graphics2D g, Container c, Point vertex, int layerIndex){
		paint(g, vertex, c, layerIndex);
		layerIndex++;
		synchronized(c.getTreeLock()){
			for(Component comp : c.getComponents()){
				if(!comp.isVisible())
					continue;
				if(comp instanceof Container)
					paintInner(g, ((Container) comp), Utility.getUtility().sumPoint(vertex, c.getLocation()), layerIndex);
				else
					paint(g, vertex, c, layerIndex);
			}
		}
	}
	
	@Override
	public void restoreDefault(){
	}
	
	@Override
	public void onEnableChange(boolean newEnable){
		this.setEnabled(newEnable);
	}
}
