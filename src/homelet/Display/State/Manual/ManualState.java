/**
 * @author HomeletWei
 * @date Mar 26, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Display.State.Manual;

import homelet.Display.Display;
import homelet.Display.State.States;
import homelet.Swing.JBasic.JTextButton;

import java.awt.*;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Mar 26, 2018
 */
public class ManualState extends States{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	
	/**
	 * constructor for ManualState
	 * TODO
	 *
	 * @param name
	 * @author HomeletWei
	 */
	public ManualState(String name){
		super(name);
	}
	
	/**
	 * @param g
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g){
		super.paint(g);
		//	GraphicsHandler.drawBorder((Graphics2D) g, new Rectangle(100, 100, 500, 300), 10, BorderStyle.CONNERS, Color.RED);
	}
	
	@Override
	public void createDisplay(){
		this.addBackAction(Display.menuState);
		JTextButton openInBrowser = new JTextButton("Open in System Browser", (e)->{
		});
		addControlBarButton(openInBrowser);
	}
}
