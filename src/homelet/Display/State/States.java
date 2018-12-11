/**
 * @author HomeletWei
 * @date Mar 25, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Display.State;

import homelet.QRCodeLuncher;
import homelet.Swing.JBasic.JTextButton;
import homelet.Swing.RestoreDefaultParent;
import homelet.Utile.Constants.Sizing;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;
import homelet.Utile.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Mar 25, 2018
 */
public abstract class States extends Container{
	
	/** @Fields <b>nullMessage</b> TODO */
	public static final  String nullMessage      = "No reference to display";
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long   serialVersionUID = 1L;
	/** @Fields <b>displayPanel</b> the displayPanel */
	protected            JPanel displayPanel;
	/** @Fields <b>controlBar</b> the control bar, flowLayout is added */
	private              JPanel controlBar;
	
	/**
	 * constructor for States
	 * TODO
	 *
	 * @param name the name
	 * @author HomeletWei
	 */
	public States(String name){
		this.controlBar = new JPanel(new FlowLayout());
		this.displayPanel = new JPanel();
		Layouter.GridBagLayouter layout = new GridBagLayouter(this);
		layout.put(layout.instanceOf(displayPanel, 0, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100).setInsets(0));
		layout.put(layout.instanceOf(controlBar, 0, 1).setAnchor(Anchor.BOTTOM).setFill(Fill.BOTH).setWeight(100, 0).setInsets(0));
		this.setName(name);
	}
	
	protected void addBackAction(States targetStates){
		addBackAction(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				QRCodeLuncher.getDisplay().showState(targetStates);
			}
		});
	}
	
	protected void addBackAction(ActionListener backAction){
		if(backAction != null){
			addControlBarButton(new JTextButton("<< Back", backAction));
		}
	}
	
	protected void addControlBarButton(AbstractButton button){
		addControlBarButton(button, Sizing.Display.controlBarButton);
	}
	
	protected void addControlBarButton(AbstractButton button, Dimension preferred){
		Utility.getUtility().setPreferredSize(button, preferred);
		this.controlBar.add(button);
	}
	
	protected void addTargetStateAction(States targetStates, String text){
		addControlBarButton(new JTextButton(text, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				QRCodeLuncher.getDisplay().showState(targetStates);
			}
		}));
	}
	
	protected void addRestoreDefault(RestoreDefaultParent parent){
		if(parent != null){
			addControlBarButton(new JTextButton("Restore Default", (ActionEvent e)->{
				parent.restoreDefault();
			}));
		}
	}
	
	public abstract void createDisplay();
	
	/**
	 * called when a state enters
	 *
	 * @author HomeletWei
	 * @see States#exitStateAction()
	 */
	public void enterStateAction(){
	}
	
	/**
	 * called when a state exits
	 *
	 * @author HomeletWei
	 * @see States#enterStateAction()
	 */
	public void exitStateAction(){
	}
}
