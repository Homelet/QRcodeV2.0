/**
 * @author HomeletWei
 * @date Apr 5, 2018
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
import homelet.Utile.StringDrawer.StringDrawer;
import homelet.Utile.StringDrawer.StringDrawerException;
import homelet.Utile.Utility;

import javax.swing.*;
import java.awt.*;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 5, 2018
 */
public class JTextLabel extends JComponent implements RestoreDefault, EnableListener{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	String       defaultString;
	StringDrawer stringDrawer;
	Color        drawColor;
	
	/**
	 * constructor for JTextLabel
	 * TODO
	 *
	 * @param text
	 * @param aligns
	 * @param textAligns
	 * @param insets
	 * @param font
	 * @param hightLight
	 * @author HomeletWei
	 */
	public JTextLabel(String text, Alignment aligns, Alignment textAligns, int insets, Font font, boolean hightLight){
		this(text, aligns, textAligns, insets, font);
		stringDrawer.setDrawFrameBorder(hightLight);
	}
	
	/**
	 * constructor for JTextLabel
	 * TODO
	 *
	 * @param text
	 * @param aligns
	 * @param textAligns
	 * @param insets
	 * @param font
	 * @author HomeletWei
	 */
	public JTextLabel(String text, Alignment aligns, Alignment textAligns, int insets, Font font){
		stringDrawer = new StringDrawer(text);
		this.drawColor = Color.BLACK;
		stringDrawer.setAlign(aligns);
		stringDrawer.setTextAlign(textAligns);
		stringDrawer.setInsets(insets);
		initalize(text);
	}
	
	/**
	 * TODO
	 *
	 * @param text
	 * @author HomeletWei
	 */
	public void initalize(String text){
		this.defaultString = text;
		restoreDefault();
	}
	
	@Override
	public void restoreDefault(){
		setText(defaultString);
	}
	
	/**
	 * constructor for JTextLabel
	 * TODO
	 *
	 * @param text
	 * @author HomeletWei
	 */
	public JTextLabel(String text){
		stringDrawer = new StringDrawer();
		this.drawColor = Color.BLACK;
		initalize(text);
	}
	
	public String getText(){
		return stringDrawer.getStringMatrix().getContents()[0];
	}
	
	/** @param text : text */
	public void setText(String text){
		if(stringDrawer.initializeContents(text))
			this.repaint();
	}
	
	@Override
	public void paint(Graphics g){
		stringDrawer.updateGraphics((Graphics2D) g);
		if(this.isEnabled())
			stringDrawer.setColor(drawColor);
		else
			stringDrawer.setColor(Utility.disableColor);
		stringDrawer.setFrame(this.getSize());
		Dimension prefDI;
		try{
			prefDI = stringDrawer.validate();
			if(prefDI != null){
				this.onSizeChange(prefDI);
				return;
			}
			stringDrawer.draw();
		}catch(StringDrawerException e){
			e.printStackTrace();
		}
	}
	
	private void onSizeChange(Dimension newDI){
		Utility.getUtility().setPreferredSize(this, newDI);
		this.getParent().revalidate();
		this.repaint();
	}
	
	/**
	 * @return is Highlight
	 * @author HomeletWei
	 */
	public boolean isHighLight(){
		return stringDrawer.isDrawFrameBorder();
	}
	
	/**
	 * set the hightLight
	 *
	 * @param hightLight
	 * @author HomeletWei
	 */
	public void setHighLight(boolean hightLight){
		stringDrawer.setDrawFrameBorder(hightLight);
	}
	
	@Override
	public void onEnableChange(boolean newEnable){
		this.setEnabled(newEnable);
	}
}
