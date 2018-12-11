/**
 * @author HomeletWei
 * @date Mar 29, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Swing.JBasic;

import homelet.Swing.EnableListener;
import homelet.Swing.RestoreDefault;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.Utility;
import homelet.Utile.Utility.Orientation;

import javax.swing.*;
import java.awt.*;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Mar 29, 2018
 */
public class JDividerLine extends JComponent implements RestoreDefault, EnableListener{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID            = 1L;
	/** @Fields <b>defaultDividerLineThickness</b> the default div thinkness */
	public static        int  defaultDividerLineThickness = 3;
	////
	Color       apperenceColor;
	int         lineThick;
	Orientation orientation = null;
	private DividerMode currentMode;
	
	/**
	 * constructor for JDividerLine
	 * TODO
	 *
	 * @param mode
	 * @author HomeletWei
	 */
	public JDividerLine(DividerMode mode){
		this(mode, defaultDividerLineThickness, null);
	}
	
	/**
	 * constructor for JDividerLine
	 * TODO
	 *
	 * @param mode
	 * @param lineThick
	 * @param DI
	 * @author HomeletWei
	 */
	public JDividerLine(DividerMode mode, int lineThick, Dimension DI){
		this(mode, lineThick, DI, new Color(220, 220, 220), null);
	}
	
	/**
	 * constructor for JDividerLine
	 * TODO
	 *
	 * @param mode
	 * @param lineThick
	 * @param DI
	 * @param color
	 * @param orientation
	 * @author HomeletWei
	 */
	public JDividerLine(DividerMode mode, int lineThick, Dimension DI, Color color, Orientation orientation){
		this.apperenceColor = color;
		this.currentMode = mode;
		this.lineThick = lineThick;
		Utility.getUtility().setPreferredSize(this, DI);
		this.orientation = orientation;
	}
	
	/**
	 * constructor for JDividerLine
	 * TODO
	 *
	 * @param DI
	 * @author HomeletWei
	 */
	public JDividerLine(Dimension DI){
		this(DividerMode.DEFAULT, defaultDividerLineThickness, DI);
	}
	
	/**
	 * constructor for JDividerLine
	 * TODO
	 *
	 * @param DI
	 * @param orientation
	 * @author HomeletWei
	 */
	public JDividerLine(Dimension DI, Orientation orientation){
		this(DividerMode.DEFAULT, defaultDividerLineThickness, DI);
		this.orientation = orientation;
	}
	
	/**
	 * constructor for JDividerLine
	 * TODO
	 *
	 * @param mode
	 * @param DI
	 * @author HomeletWei
	 */
	public JDividerLine(DividerMode mode, Dimension DI){
		this(mode, defaultDividerLineThickness, DI);
	}
	
	/**
	 * constructor for JDividerLine
	 * TODO
	 *
	 * @param mode
	 * @param DI
	 * @param orientation
	 * @author HomeletWei
	 */
	public JDividerLine(DividerMode mode, Dimension DI, Orientation orientation){
		this(mode, defaultDividerLineThickness, DI);
		this.orientation = orientation;
	}
	
	/**
	 * constructor for JDividerLine
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public JDividerLine(){
		this(DividerMode.DEFAULT, defaultDividerLineThickness, null);
	}
	
	/**
	 * @param g
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g){
		super.paint(g);
		if(this.isEnabled()){
			g.setColor(apperenceColor);
		}else{
			g.setColor(Utility.disableColor);
		}
		int         width        = this.getWidth();
		int         height       = this.getHeight();
		int         dominate     = 0;
		Orientation cOrientation = orientation;
		if(cOrientation == null)
			cOrientation = Utility.getUtility().getOrientation(getSize());
		switch(cOrientation){
			case HORIZONTAL:
				height = lineThick;
				dominate = width;
				break;
			case VERTICAL:
				width = lineThick;
				dominate = height;
				break;
			case EQUILATERAL:
				height = lineThick;
				width = lineThick;
				dominate = 0;
				break;
			default:
				break;
		}
		Point p = Alignment.getDesiredVertex(Alignment.CENTER, new Point(0, 0), this.getSize(), new Dimension(width, height));
		switch(currentMode){
			case RECTANGLE:
				g.fillRect(p.x, p.y, width, height);
				break;
			case DOT:
				for(int pos = currentMode.offset; pos < dominate - currentMode.offset; pos += (lineThick * 2)){
					if(dominate == width)
						g.fillOval(p.x + pos, p.y, lineThick, lineThick);
					else
						g.fillOval(p.x, p.y + pos, lineThick, lineThick);
				}
				break;
			default:
			case DEFAULT:
				g.fillOval(p.x, p.y, width, height);
				break;
		}
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
	
	public enum DividerMode{
		/** @Fields <b>DEFAULT</b> the default mode */
		DEFAULT(0),
		/** @Fields <b>RECTANGLE</b> the rectangle mode */
		RECTANGLE(5),
		/** @Fields <b>DOT</b> the dots mode */
		DOT(10);
		int offset;
		
		private DividerMode(int offset){
			this.offset = offset;
		}
	}
}
