/**
 * @author HomeletWei
 * @date Apr 9, 2018
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
 * @date Apr 9, 2018
 */
public class JInputField extends JInput{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	JTextField   textField;
	StringDrawer drawer;
	
	/**
	 * constructor for JInputField
	 * TODO
	 *
	 * @param placeHolder
	 * @param textfieldDI
	 * @param editable
	 * @author HomeletWei
	 */
	public JInputField(String placeHolder, Dimension textfieldDI, boolean editable){
		super();
		drawer = new StringDrawer(placeHolder);
		drawer.setColor(Color.GRAY);
		drawer.setAlign(Alignment.LEFT);
		drawer.setTextAlign(Alignment.LEFT);
		drawer.setInsets(1, 1, 0, 0);
		this.textField = new JTextField(){
			/** @Fields <b>serialVersionUID</b> TODO */
			private static final long serialVersionUID = 1L;
			
			@Override
			public void paint(Graphics g){
				super.paint(g);
				if(getContent().isEmpty()){
					try{
						drawer.updateGraphics((Graphics2D) g);
						drawer.setFrame(getBounds());
						drawer.validate();
						drawer.draw();
					}catch(StringDrawerException e){
						e.printStackTrace();
					}
				}
			}
		};
		this.textField.setEditable(editable);
		setUpAssistant(editable);
		Utility.getUtility().setPreferredSize(textField, textfieldDI);
		this.add(textField, BorderLayout.CENTER);
	}
	
	@Override
	public JTextComponent getTextComponent(){
		return textField;
	}
}
