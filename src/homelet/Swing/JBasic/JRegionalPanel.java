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
 * @date Jun 2, 2018
 */
package homelet.Swing.JBasic;

import homelet.Swing.EnableListener;
import homelet.Swing.RestoreDefault;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.Layouter.SpringLayouter;
import homelet.Utile.Layouter.SpringLayouter.Position;
import homelet.Utile.StringDrawer.StringDrawer;
import homelet.Utile.StringDrawer.StringDrawerException;
import homelet.Utile.Utility;

import javax.swing.*;
import java.awt.*;

/**
 * the regional panel
 *
 * @author HomeletWei
 * @date Jun 2, 2018
 */
public class JRegionalPanel extends JPanel implements RestoreDefault, EnableListener{
	
	/** @Fields <b>serialVersionUID</b> */
	private static final long  serialVersionUID         = 1L;
	private final        int   borderThickness;
	private final        float defaultOffsetStartFactor = 0.1f;
	Color        borderColor;
	Color        textColor;
	StringDrawer drawer;
	JPanel       nestedPanel;
	
	/**
	 * constructor for JHighLightPanel
	 *
	 * @param title
	 * @param borderWidth
	 * @param fontSize
	 * @param borderColor
	 * @param textColor
	 * @author HomeletWei
	 */
	public JRegionalPanel(String title, int borderWidth, int fontSize, Color borderColor, Color textColor){
		System.out.println(title);
		this.borderThickness = borderWidth;
		this.borderColor = borderColor;
		this.textColor = textColor;
		nestedPanel = new JPanel();
		SpringLayouter layout = new SpringLayouter(this);
		layout.put(Position.CONSTRAIN_X, nestedPanel, borderWidth + 1, Position.CONSTRAIN_X, this);
		layout.put(Position.CONSTRAIN_Y, nestedPanel, borderWidth + 1, Position.CONSTRAIN_Y, this);
		layout.put(Position.CONSTRAIN_X_WIDTH, nestedPanel, -(borderWidth + 1), Position.CONSTRAIN_X_WIDTH, this);
		layout.put(Position.CONSTRAIN_Y_HEIGHT, nestedPanel, -(borderWidth + 1), Position.CONSTRAIN_Y_HEIGHT, this);
		drawer = new StringDrawer(title);
		drawer.setColor(textColor);
		drawer.setAlign(Alignment.KEEP_Y_ON_TOP);
		drawer.setTextAlign(Alignment.CENTER);
		drawer.setFont(StringDrawer.defaultFont.deriveFont(fontSize));
	}
	
	/**
	 * <pre>
	 *      |<- start from 0.1 of the width ->|
	 * +----------------------------------------------------+
	 * | +--+   the title area                +-----------+ |
	 * | | +--------------------------------------------+ | | 5
	 * | | |                                            | | |
	 * | | |                                            | | |
	 * | | |   Nested Panel                             | | |
	 * | | |                                            | | |
	 * | | |                                            | | |
	 * | | |                                            | | |
	 * | | |                                            | | |
	 * | | |                                            | | |
	 * | | +--------------------------------------------+ | |
	 * | +------------------------------------------------+ |
	 * +----------------------------------------------------+
	 *    5
	 * </pre>
	 *
	 * @param g
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2        = (Graphics2D) g;
		int        width     = this.getWidth();
		int        height    = this.getHeight();
		int        textStart = (int) (Integer.valueOf(width).floatValue() * defaultOffsetStartFactor);
		//// draw Title /////
		try{
			drawer.updateGraphics(g2);
			if(this.isEnabled())
				drawer.setColor(textColor);
			else
				drawer.setColor(Utility.disableColor);
			drawer.setFrame(this.getBounds());
			drawer.setTextFrameVertex(new Point(textStart, 0));
			drawer.validate();
			drawer.draw();
		}catch(StringDrawerException e){
			e.printStackTrace();
		}
		//// border ////
		int       textEnd = textStart + drawer.getTextWidth();
		Rectangle topOne  = new Rectangle(0, 0, textStart, borderThickness);
		Rectangle topTwo  = new Rectangle(textEnd, 0, width - textEnd, borderThickness);
		Rectangle left    = new Rectangle(0, borderThickness, borderThickness, height - borderThickness * 2);
		Rectangle right   = new Rectangle(width - borderThickness, borderThickness, borderThickness, height - borderThickness * 2);
		Rectangle bottom  = new Rectangle(0, height - borderThickness, width, borderThickness);
		if(this.isEnabled())
			g2.setColor(borderColor);
		else
			g2.setColor(Utility.disableColor);
		g2.fill(topOne);
		g2.fill(topTwo);
		g2.fill(left);
		g2.fill(right);
		g2.fill(bottom);
	}
	
	/**
	 * @return the borderWidth
	 * @author HomeletWei
	 */
	public int getBorderWidth(){
		return borderThickness;
	}
	
	/**
	 * the nested panel
	 *
	 * @return the nestedPanel
	 * @author HomeletWei
	 */
	public JPanel getNestedPanel(){
		return nestedPanel;
	}
	
	/**
	 * @param newEnable
	 * @see homelet.Swing.EnableListener#onEnableChange(boolean)
	 */
	@Override
	public void onEnableChange(boolean newEnable){
		this.setEnabled(newEnable);
	}
	
	/**
	 * @see homelet.Swing.RestoreDefault#restoreDefault()
	 */
	@Override
	public void restoreDefault(){
	}
}
