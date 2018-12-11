/**
 * @author HomeletWei
 * @date Apr 4, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Swing.JInput;

import homelet.Utile.Constants.Alignment;
import homelet.Utile.StringDrawer.StringDrawer;
import homelet.Utile.StringDrawer.StringDrawerException;
import homelet.Utile.Utility;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 4, 2018
 */
public class JInputArea extends JInput{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	JTextArea    textField;
	JScrollPane  scrollPane;
	StringDrawer drawer;
	
	/**
	 * constructor for JInputField
	 * TODO
	 *
	 * @param placeHolder
	 * @param editable
	 * @author HomeletWei
	 */
	public JInputArea(String placeHolder, Dimension DI, boolean editable){
		super();
		drawer = new StringDrawer(placeHolder);
		drawer.setColor(Color.GRAY);
		drawer.setAlign(Alignment.TOP_LEFT);
		drawer.setTextAlign(Alignment.LEFT);
		drawer.setInsets(0, 1, 0, 0);
		this.textField = new JTextArea(){
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				if(getContent().isEmpty()){
					try{
						drawer.updateGraphics(g);
						drawer.setFrame(getBounds());
						drawer.validate();
						drawer.draw();
					}catch(StringDrawerException e){
						e.printStackTrace();
					}
				}
			}
			
			@Override
			public void setEnabled(boolean enabled){
				super.setEnabled(enabled);
				getInputAssistant().setModifyEnable(enabled);
			}
		};
		this.textField.setLineWrap(true);
		this.textField.setWrapStyleWord(true);
		this.textField.setEditable(editable);
		this.textField.setRequestFocusEnabled(true);
		this.scrollPane = new JScrollPane(textField, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.scrollPane.setAutoscrolls(true);
		Utility.getUtility().setPreferredSize(scrollPane, DI);
		setUpAssistant(editable);
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
	@Override
	public JTextComponent getTextComponent(){
		return textField;
	}
}
