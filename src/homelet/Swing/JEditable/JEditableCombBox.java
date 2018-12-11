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

import homelet.Swing.JBasic.JCustomizeComboBox;
import homelet.Swing.JBasic.JTextLabel;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.Constants.Sizing;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * TODO
 *
 * @param <E>
 * @author HomeletWei
 * @date Apr 4, 2018
 */
public class JEditableCombBox<E> extends JEdit{
	
	/** @Fields <b>templateIdentifyer</b> the template indetifyer */
	public static final  String templateIdentifyer = "#CB#";
	/** @Fields <b>serialVersionUID</b> */
	private static final long   serialVersionUID   = 1L;
	JTextLabel            textLabel;
	JCustomizeComboBox<E> chooser;
	
	/**
	 * constructor for JTextLabel
	 * TODO
	 *
	 * @param textHints
	 * @param defaultSelected
	 * @author HomeletWei
	 */
	public JEditableCombBox(E[] textHints, int defaultSelected){
		super();
		this.textLabel = new JTextLabel((String) textHints[defaultSelected], Alignment.CENTER, Alignment.CENTER, 0, null, false);
		this.chooser = new JCustomizeComboBox<>(textHints, defaultSelected, Sizing.EncodeTemplate.singleLine);
		addRestoreDefaultChild(chooser);
		addEnableChild(chooser, true);
		addRestoreDefaultChild(textLabel);
		addEnableChild(textLabel, true);
		this.textLabel.addMouseListener(new MouseAdapter(){
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
		this.chooser.addActionListener((ActionEvent e)->{
			flip();
		});
		Layouter.GridBagLayouter layoutTrue = new GridBagLayouter((JPanel) truePanel);
		layoutTrue.put(layoutTrue.instanceOf(textLabel, 0, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		Layouter.GridBagLayouter layoutFalse = new GridBagLayouter((JPanel) falsePanel);
		layoutFalse.put(layoutFalse.instanceOf(chooser, 0, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		layoutFalse.put(layoutFalse.instanceOf(getBackButton(), 0, 1).setAnchor(Anchor.CENTER).setWeight(100, 0).setFill(Fill.HORIZONTAL));
		this.setMinimumSize(Sizing.EncodeTemplate.templatePieceTwo);
	}
	
	@Override
	public boolean isReady(){
		//	flipTo(true);
		return true;
	}
	
	@Override
	public String getContent(){
		return (String) chooser.getSelectedItem();
	}
	
	/**
	 * @return the selected index
	 * @author HomeletWei
	 */
	public int getSelectedIndex(){
		return chooser.getSelectedIndex();
	}
	
	@Override
	protected void exitStateAction(){
		textLabel.setText((String) chooser.getSelectedItem());
	}
}
