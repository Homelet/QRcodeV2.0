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
package homelet.Swing.JEditable;

import homelet.Swing.JBasic.JTextLabel;
import homelet.Swing.JInput.JInputArea;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.Constants.Sizing;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 4, 2018
 */
public class JEditableTextArea extends JEdit{
	
	/** @Fields <b>templateIdentifyer</b> */
	public static final  String templateIdentifyer = "#IF#";
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long   serialVersionUID   = 1L;
	JTextLabel displayLabel;
	JInputArea inputField;
	
	/**
	 * constructor for JEditableTextLabel
	 * TODO
	 *
	 * @param placeHolder
	 * @param discription
	 * @author HomeletWei
	 */
	public JEditableTextArea(String placeHolder, String discription){
		super();
		//	Utility.setPreferredSize(this, Sizing.EncodeTemplate.templatePieceTwo);
		this.displayLabel = new JTextLabel(discription, Alignment.CENTER, Alignment.CENTER, 0, null, true);
		this.inputField = new JInputArea(placeHolder, Sizing.EncodeTemplate.templatePieceTwo, true);
		Layouter.GridBagLayouter trueLayout = new GridBagLayouter((JPanel) truePanel);
		trueLayout.put(trueLayout.instanceOf(displayLabel, 0, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		Layouter.GridBagLayouter falseLayout = new GridBagLayouter((JPanel) falsePanel);
		falseLayout.put(falseLayout.instanceOf(inputField, 0, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		falseLayout.put(falseLayout.instanceOf(getBackButton(), 0, 1).setAnchor(Anchor.CENTER).setFill(Fill.HORIZONTAL).setWeight(100, 0));
		addRestoreDefaultChild(inputField);
		addRestoreDefaultChild(displayLabel);
		addEnableChild(inputField, true);
		addEnableChild(displayLabel, true);
		addAction();
		flipTo(true);
	}
	
	private void addAction(){
		this.displayLabel.addMouseListener(new MouseAdapter(){
			/**
			 * @param e
			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
			 */
			@Override
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 2 && isEnabled()){
					flip();
				}
			}
		});
		this.inputField.getTextComponent().setDocument(new PlainDocument(){
			private static final long serialVersionUID = 1L;
			
			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException{
				if(str.endsWith("\n")){
					flip();
					return;
				}
				super.insertString(offs, str, a);
			}
		});
	}
	
	@Override
	protected void exitStateAction(){
		this.displayLabel.setHighLight(!isReady());
		if(inputField.isChanged())
			this.displayLabel.setText(inputField.getContent());
	}
	
	@Override
	public boolean isReady(){
		//	flipTo(true);
		return this.inputField.isChanged();
	}
	
	@Override
	public String getContent(){
		return inputField.getContent();
	}
}
