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
package homelet.Swing.JComplex;

import homelet.Swing.*;

import javax.swing.*;
import java.util.ArrayList;

/**
 * TODO
 *
 * @author HomeletWei
 * @date May 14, 2018
 */
public abstract class JComplex extends JComponent implements RestoreDefaultParent, EnableListenerParent{
	
	/** @Fields <b>serialVersionUID</b> */
	private static final long serialVersionUID = 1L;
	ArrayList<RestoreDefault> RDChildList = new ArrayList<>();
	ArrayList<EnableListener> ELChildList = new ArrayList<>();
	
	@Override
	public ArrayList<RestoreDefault> getRestoreDefaultChildList(){
		return RDChildList;
	}
	
	@Override
	public ArrayList<EnableListener> getEnableChildList(){
		return ELChildList;
	}
	
	private ArrayList<ChangeActionListener> changeActionListeners = new ArrayList<>();
	
	public void addChangeActionListener(ChangeActionListener changeActionListener){
		this.changeActionListeners.add(changeActionListener);
	}
	
	protected void onChangeActionInvoke(){
		for(ChangeActionListener c : changeActionListeners){
			if(c != null)
				c.onChange();
		}
	}
}
