/**
 * @author HomeletWei
 * @date Apr 7, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Swing.JEditable;

import homelet.Swing.JBasic.JTextLabel;
import homelet.Swing.JInput.JInputField;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.Constants.Sizing;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 7, 2018
 */
public class JEditableTextFields extends JEdit{
	
	/** @Fields <b>templateIdentifyer</b> Identifyer */
	public static final  String templateIdentifyer = "#MIF#";
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long   serialVersionUID   = 1L;
	JTextLabel    displayLabel;
	JInputField[] inputFields;
	
	/**
	 * constructor for JEditableTextFields
	 *
	 * @param placeHolders
	 * @param discriptions
	 * @author HomeletWei
	 */
	public JEditableTextFields(String[] placeHolders, String discriptions){
		this(placeHolders, discriptions, Sizing.EncodeTemplate.singleLine);
	}
	
	/**
	 * constructor for JEditableTextFields
	 *
	 * @param placeHolders
	 * @param discriptions
	 * @param DI
	 * @author HomeletWei
	 */
	public JEditableTextFields(String[] placeHolders, String discriptions, Dimension DI){
		super();
		//	Utility.setPreferredSize(this, Sizing.EncodeTemplate.templatePieceTwo);
		this.displayLabel = new JTextLabel(discriptions, Alignment.CENTER, Alignment.CENTER, 0, null, true);
		addEnableChild(displayLabel, true);
		addRestoreDefaultChild(displayLabel);
		Layouter.GridBagLayouter trueLayout = new GridBagLayouter((JPanel) truePanel);
		trueLayout.put(trueLayout.instanceOf(displayLabel, 0, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		this.inputFields = new JInputField[placeHolders.length];
		int                      adder       = 0;
		Layouter.GridBagLayouter falseLayout = new GridBagLayouter((JPanel) falsePanel);
		for(int index = 0; index < inputFields.length; index++){
			inputFields[index] = new JInputField(placeHolders[index], DI, true);
			falseLayout.put(falseLayout.instanceOf(inputFields[index], 0, adder++).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
			addEnableChild(inputFields[index], true);
			addRestoreDefaultChild(inputFields[index]);
		}
		falseLayout.put(falseLayout.instanceOf(getBackButton(), 0, adder).setAnchor(Anchor.CENTER).setFill(Fill.HORIZONTAL).setWeight(100, 0));
		addAction();
		flipTo(true);
	}
	
	private void addAction(){
		this.displayLabel.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 2 && isEnabled()){
					flip();
				}
			}
		});
	}
	
	@Override
	public boolean isReady(){
		//	flipTo(true);
		for(JInputField j : inputFields){
			if(!j.isChanged())
				return false;
		}
		return true;
	}
	
	@Override
	public String[] getContent(){
		return getContents();
	}
	
	private String[] getContents(){
		String[] buffer = new String[inputFields.length];
		for(int index = 0; index < inputFields.length; index++){
			buffer[index] = inputFields[index].getContent();
		}
		return buffer;
	}
	
	/**
	 * @see homelet.Swing.JBasic.JFlipAblePanel#exitStateAction()
	 */
	@Override
	protected void exitStateAction(){
		String  buffer  = null;
		boolean isReady = true;
		for(JInputField j : inputFields){
			if(!j.isChanged()){
				isReady = false;
			}else{
				buffer = buffer == null ? j.getContent() : buffer + "\n" + j.getContent();
			}
		}
		this.displayLabel.setHighLight(!isReady);
		if(buffer != null)
			this.displayLabel.setText(buffer);
	}
}
