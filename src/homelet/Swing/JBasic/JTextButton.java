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
 * @date May 11, 2018
 */
package homelet.Swing.JBasic;

import homelet.Swing.EnableListener;
import homelet.Swing.RestoreDefault;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.StringDrawer.StringDrawer;
import homelet.Utile.StringDrawer.StringDrawerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * TODO
 *
 * @author HomeletWei
 * @date May 11, 2018
 */
public class JTextButton extends JButton implements RestoreDefault, EnableListener{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	StringDrawer drawer;
	
	/**
	 * constructor for JButton
	 *
	 * @param text   the text
	 * @param action the action
	 * @author HomeletWei
	 */
	public JTextButton(String text, ActionListener action){
		this(text);
		this.addActionListener(action);
	}
	
	/**
	 * constructor for JButton
	 *
	 * @param text
	 * @author HomeletWei
	 */
	public JTextButton(String text){
		this();
		setText(text);
	}
	
	/**
	 * constructor for JButton
	 *
	 * @author HomeletWei
	 */
	public JTextButton(){
		this(Color.BLACK);
	}
	
	@Override
	public void setText(String text){
		drawer.initializeContents(text);
	}
	
	public String getTextContent(){
		return drawer.getStringMatrix().getContents()[0];
	}
	
	/**
	 * constructor for JButton
	 *
	 * @param drawColor
	 * @author HomeletWei
	 */
	public JTextButton(Color drawColor){
		super();
		drawer = new StringDrawer();
//		drawer.setDrawFrameBorder(true);
//		drawer.setDrawTextFrameBorder(true);
		drawer.setAlign(Alignment.CENTER);
		drawer.setTextAlign(Alignment.TOP);
		drawer.setColor(drawColor);
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		try{
			drawer.updateGraphics(g);
			drawer.setColor(this.getForeground());
			drawer.setFrame(this.getSize());
			drawer.validate();
			drawer.draw();
		}catch(StringDrawerException e){
			e.printStackTrace();
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
}
