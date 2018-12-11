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
 * @date May 14, 2018
 */
package homelet.Swing.JEditable;

import homelet.Swing.EnableListener;
import homelet.Swing.EnableListenerParent;
import homelet.Swing.JBasic.JFlipAblePanel;
import homelet.Swing.JBasic.JTextButton;
import homelet.Swing.RestoreDefault;
import homelet.Swing.RestoreDefaultParent;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 13, 2018
 */
public abstract class JEdit extends JFlipAblePanel implements RestoreDefaultParent, EnableListenerParent{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	ArrayList<EnableListener> enableList     = new ArrayList<>();
	ArrayList<RestoreDefault> restoreDefault = new ArrayList<>();
	
	/**
	 * constructor for JFlipAblePanel
	 *
	 * @author HomeletWei
	 */
	public JEdit(){
		super();
	}
	
	protected JTextButton getBackButton(){
		return new JTextButton("Apply", (ActionEvent e)->{
			flipTo(true);
		});
	}
	
	/**
	 * @return is ready for output
	 * @author HomeletWei
	 */
	public abstract boolean isReady();
	
	/**
	 * @return the content of the edit
	 * @author HomeletWei
	 */
	public abstract Object getContent();
	
	@Override
	public void onEnableChange(boolean newEnable){
		EnableListenerParent.super.onEnableChange(newEnable);
	}
	
	@Override
	public void restoreDefault(){
		flipTo(true);
		RestoreDefaultParent.super.restoreDefault();
	}
	
	@Override
	public ArrayList<EnableListener> getEnableChildList(){
		return enableList;
	}
	
	@Override
	public ArrayList<RestoreDefault> getRestoreDefaultChildList(){
		return restoreDefault;
	}
}
