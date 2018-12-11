/**
 * @author HomeletWei
 * @date Apr 13, 2018
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

import homelet.Swing.ChangeActionListener;
import homelet.Swing.EnableListener;
import homelet.Swing.RestoreDefault;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 13, 2018
 */
public abstract class JFlipAblePanel extends JPanel implements RestoreDefault, EnableListener{
	
	/** @Fields <b>STATE_ONE</b> */
	public static final  boolean    STATE_ONE        = Boolean.TRUE;
	/** @Fields <b>STATE_TWO</b> */
	public static final  boolean    STATE_TWO        = Boolean.FALSE;
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long       serialVersionUID = 1L;
	/**
	 * usually true is state one;
	 * false is state two
	 */
	protected            boolean    currentState     = true;
	/** state one panel */
	public               Component  truePanel;
	/** state two panel */
	public               Component  falsePanel;
	private              CardLayout layOut;
	
	/**
	 * constructor for JFlipAblePanel
	 *
	 * @author HomeletWei
	 */
	public JFlipAblePanel(){
		layOut = new CardLayout();
		this.setLayout(layOut);
		truePanel = new JPanel();
		falsePanel = new JPanel();
		this.addState(truePanel, true);
		this.addState(falsePanel, false);
	}
	
	private void addState(Component c, boolean state){
		this.add(c);
		layOut.addLayoutComponent(c, String.valueOf(state));
	}
	
	public void setState(Component component, boolean state){
		layOut.removeLayoutComponent(getState(state));
		addState(component, state);
	}
	
	public Component getState(boolean state){
		if(state)
			return truePanel;
		else
			return falsePanel;
	}
	
	public void flip(){
		flipTo(!currentState);
	}
	
	public void flipTo(boolean bool){
		if(!currentState || bool)
			this.exitStateAction();
		currentState = bool;
		showState(currentState);
	}
	
	protected abstract void exitStateAction();
	
	private void showState(boolean bool){
		layOut.show(this, String.valueOf(bool));
	}
	
	public boolean getCurrentState(){
		return currentState;
	}
	
	public Component getCurrent(){
		return getState(getCurrentState());
	}
	
	private ArrayList<ChangeActionListener> changeActionListeners = new ArrayList<>();
	
	public void addChangeActionListener(ChangeActionListener changeActionListener){
		this.changeActionListeners.add(changeActionListener);
	}
	
	private void onChangeActionInvoke(){
		for(ChangeActionListener c : changeActionListeners){
			if(c != null)
				c.onChange();
		}
	}
	
	@Override
	public void onEnableChange(boolean newEnable){
	}
	
	@Override
	public void restoreDefault(){
	}
}
