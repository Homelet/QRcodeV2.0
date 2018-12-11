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
package homelet.Display;

import homelet.Display.State.Decode.DecodeSettingState;
import homelet.Display.State.Decode.DecodeState;
import homelet.Display.State.Encode.EncodeSettingState;
import homelet.Display.State.Encode.EncodeState;
import homelet.Display.State.Manual.ManualState;
import homelet.Display.State.Menu.MenuSettingState;
import homelet.Display.State.Menu.MenuState;
import homelet.Display.State.States;
import homelet.Swing.JBasic.JBasePanel;
import homelet.Utile.Constants.Alignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Mar 25, 2018
 */
public class Display extends JFrame{
	
	public static final  int                SCREEN_WIDTH     = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final  int                SCREEN_HEIGHT    = Toolkit.getDefaultToolkit().getScreenSize().height;
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long               serialVersionUID = 1L;
	/** @Fields <b>currentState</b> current showing state */
	public static        States             currentState;
	/** @Fields <b>menuState</b> the states which does the menu */
	public static        MenuState          menuState;
	/** @Fields <b>menueSettingState</b> the states which does the menu setting */
	public static        MenuSettingState   menueSettingState;
	/** @Fields <b>encodeState</b> the states which does the encode */
	public static        EncodeState        encodeState;
	/** @Fields <b>encodeSettingState</b> the states which does the encode setting */
	public static        EncodeSettingState encodeSettingState;
	/** @Fields <b>decodeState</b> the states which does the decode */
	public static        DecodeState        decodeState;
	/** @Fields <b>decodeSettingState</b> the states which does the decode setting */
	public static        DecodeSettingState decodeSettingState;
	/** @Fields <b>manualState</b> the states which does the user manual */
	public static        ManualState        manualState;
	CardLayout layOut;
	Container  container;
	private String namePrefix = "QRCODE 2.0 - BETA";
	
	/**
	 * constructor for Display
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public Display(){
		//	this.setIconImage(icon_1.getImage());
		this.layOut = new CardLayout();
		this.container = new JBasePanel();
		this.container.setLayout(layOut);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(container, BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		// initalize States
		Display.menuState = new MenuState("Menu");
		Display.menueSettingState = new MenuSettingState("MenuSetting");
		Display.encodeState = new EncodeState("Encode");
		Display.encodeSettingState = new EncodeSettingState("EncodeSetting");
		Display.decodeState = new DecodeState("Decode");
		Display.decodeSettingState = new DecodeSettingState("Decode Setting");
		Display.manualState = new ManualState("Manual");
		// create display
		Display.menuState.createDisplay();
		Display.menueSettingState.createDisplay();
		Display.encodeState.createDisplay();
		Display.encodeSettingState.createDisplay();
		Display.decodeState.createDisplay();
		Display.decodeSettingState.createDisplay();
		Display.manualState.createDisplay();
		// add states
		addState(menuState);
		addState(menueSettingState);
		addState(encodeState);
		addState(encodeSettingState);
		addState(decodeState);
		addState(decodeSettingState);
		addState(manualState);
		showState(menuState);
		// ready
		this.setMinimumSize(new Dimension(1260, 705));
		this.setPreferredSize(this.getMinimumSize());
		this.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setLocation(Alignment.getDesiredVertex(Alignment.CENTER, new Point(0, 0), Toolkit.getDefaultToolkit().getScreenSize(), getSize()));
		// excute
		this.setVisible(true);
	}
	
	private void addState(States states){
		this.layOut.addLayoutComponent(states, states.getName());
		this.container.add(states);
	}
	
	/**
	 * @param states
	 * @author HomeletWei
	 */
	public void showState(States states){
		if(states == null)
			return;
		this.layOut.show(container, states.getName());
		this.setTitle(namePrefix + " - " + states.getName());
		this.pack();
		if(currentState != null)
			currentState.exitStateAction();
		currentState = states;
		currentState.enterStateAction();
	}
	
	@Override
	protected void processWindowEvent(WindowEvent e){
		if(e.getID() == WindowEvent.WINDOW_CLOSING)
			System.err.println("EXITING!");
		super.processWindowEvent(e);
	}
}
