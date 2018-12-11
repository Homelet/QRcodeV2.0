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
 * @date Jun 1, 2018
 */
package homelet.Swing.JBasic;

import homelet.Swing.RemoveActionListener;
import homelet.Utile.Constants.Sizing;
import homelet.Utile.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Jun 1, 2018
 */
public class JRemoveButton extends JTextButton{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long  serialVersionUID = 1L;
	private static       Color backGround       = new Color(227, 75, 59);
	private static       Color foreGround       = Color.RED.darker();
	
	/**
	 * constructor for JRemoveButton
	 *
	 * @param removeAction
	 * @param compToRemove
	 * @author HomeletWei
	 */
	public JRemoveButton(RemoveActionListener removeAction, JComponent compToRemove){
		this((ActionEvent e)->{
			removeAction.removeActionPerformed(compToRemove);
		});
	}
	
	/**
	 * constructor for JRemoveButton
	 *
	 * @param remove
	 * @author HomeletWei
	 */
	public JRemoveButton(ActionListener remove){
		super("x", remove);
		init();
	}
	
	private void init(){
		Utility.getUtility().setPreferredSize(this, Sizing.removeButton);
		setForeground(foreGround);
		this.setEnabled(true);
	}
	
	@Override
	public void setEnabled(boolean b){
		if(b){
			setBackground(backGround);
		}else{
			setBackground(Utility.disableColor);
		}
		super.setEnabled(b);
	}
	
	/**
	 * @param newEnable
	 * @see homelet.Swing.JBasic.JTextButton#onEnableChange(boolean)
	 */
	@Override
	public void onEnableChange(boolean newEnable){
		super.onEnableChange(newEnable);
	}
}
