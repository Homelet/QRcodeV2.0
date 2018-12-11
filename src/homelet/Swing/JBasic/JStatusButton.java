/**
 * @author HomeletWei
 * @date Apr 1, 2018
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

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 1, 2018
 */
public class JStatusButton extends JToggleButton implements RestoreDefault, EnableListener{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	boolean      defaultOnOff;
	StringDrawer drawer;
	
	/**
	 * constructor for JStatusButton
	 * TODO
	 *
	 * @param text
	 * @param defaultOnOff
	 * @param listner
	 * @author HomeletWei
	 */
	public JStatusButton(String[] text, boolean defaultOnOff, ActionListener listner){
		this(text, defaultOnOff);
		this.addActionListener(listner);
	}
	
	/**
	 * constructor for JStatusButton
	 * TODO
	 *
	 * @param text
	 * @param defaultOnOff
	 * @author HomeletWei
	 */
	public JStatusButton(String[] text, boolean defaultOnOff){
		this.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e){
				drawer.initializeContents(JStatusButton.this.isSelected() ? text[0] : text[1]);
			}
		});
		drawer = new StringDrawer();
		this.defaultOnOff = defaultOnOff;
		restoreDefault();
	}
	
	@Override
	public void restoreDefault(){
		this.setSelected(defaultOnOff);
	}
	
	/**
	 * constructor for JStatusButton
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public JStatusButton(String text, boolean defaultOnOff){
		drawer = new StringDrawer(text);
		drawer.setAlign(Alignment.CENTER);
		drawer.setTextAlign(Alignment.CENTER);
		this.defaultOnOff = defaultOnOff;
		restoreDefault();
	}
	
	/**
	 * @param g
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g){
		super.paint(g);
		try{
			drawer.updateGraphics(g);
			drawer.setFrame(this.getSize());
			drawer.validate();
			drawer.draw();
		}catch(StringDrawerException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void onEnableChange(boolean newEnable){
		this.setEnabled(newEnable);
	}
}
