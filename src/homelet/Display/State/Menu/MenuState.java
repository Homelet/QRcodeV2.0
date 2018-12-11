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
package homelet.Display.State.Menu;

import homelet.Display.Display;
import homelet.Display.State.States;
import homelet.QRCodeLuncher;
import homelet.Swing.JBasic.JTextButton;

import javax.swing.*;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Mar 25, 2018
 */
public class MenuState extends States{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	
	/**
	 * constructor for MenuState
	 * TODO
	 *
	 * @param name
	 * @author HomeletWei
	 */
	public MenuState(String name){
		super(name);
	}
	
	@Override
	public void createDisplay(){
		JLabel lable = new JLabel("File Missing");
		lable.setHorizontalAlignment(SwingConstants.CENTER);
		lable.setVerticalAlignment(SwingConstants.CENTER);
		JTextButton gotoEncode = new JTextButton("Encode", (e)->{
			QRCodeLuncher.getDisplay().showState(Display.encodeState);
		});
		JTextButton gotoDecode = new JTextButton("Decode", (e)->{
			QRCodeLuncher.getDisplay().showState(Display.decodeState);
		});
		JTextButton gotoSetting = new JTextButton("Setting", (e)->{
			QRCodeLuncher.getDisplay().showState(Display.menueSettingState);
		});
		JTextButton gotoManual = new JTextButton("User Manual", (e)->{
			QRCodeLuncher.getDisplay().showState(Display.manualState);
		});
		this.displayPanel.add(lable);
		addControlBarButton(gotoManual);
		addControlBarButton(gotoEncode);
		addControlBarButton(gotoDecode);
		addControlBarButton(gotoSetting);
	}
}
