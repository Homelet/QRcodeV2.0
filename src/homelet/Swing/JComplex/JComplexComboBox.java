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
package homelet.Swing.JComplex;

import homelet.Swing.ChangeActionListener;
import homelet.Swing.JBasic.JCustomizeComboBox;
import homelet.Swing.JBasic.JDividerLine;
import homelet.Swing.JBasic.JDividerLine.DividerMode;
import homelet.Swing.JBasic.JTextLabel;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.Constants.Sizing;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.SpringLayouter;
import homelet.Utile.Layouter.SpringLayouter.Position;
import homelet.Utile.Utility;

/**
 * TODO
 *
 * @param <E>
 * @author HomeletWei
 * @date Apr 13, 2018
 */
public class JComplexComboBox<E> extends JComplex{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	JCustomizeComboBox<E> comboBox;
	
	/**
	 * constructor for JComplexComboBox
	 * TODO
	 *
	 * @param discription
	 * @param items
	 * @param defaultSelected
	 * @author HomeletWei
	 */
	public JComplexComboBox(String discription, E[] items, int defaultSelected){
		this.comboBox = new JCustomizeComboBox<>(items, defaultSelected);
		JTextLabel label = new JTextLabel(discription, Alignment.RIGHT, Alignment.RIGHT, 0, null, false);
		Utility.getUtility().setPreferredSize(label, Sizing.Menu.doubleMediumOne, Sizing.Menu.smallItemHeight);
		Utility.getUtility().setPreferredSize(comboBox, Sizing.Menu.doubleMediumTwo, Sizing.Menu.smallItemHeight);
		addRestoreDefaultChild(comboBox);
		addRestoreDefaultChild(label);
		addEnableChild(comboBox, false);
		addEnableChild(label, false);
		JDividerLine            div    = new JDividerLine(DividerMode.DOT);
		Layouter.SpringLayouter layout = new SpringLayouter(this);
		layout.put(layout.instanceOf(this).put(Position.CONSTRAIN_Y_HEIGHT, Sizing.Menu.smallItemHeight));
		layout.put(Position.CONSTRAIN_X, label, 0, Position.CONSTRAIN_X, this);
		layout.put(Position.CONSTRAIN_Y, label, 0, Position.CONSTRAIN_Y, this);
		layout.put(Position.CONSTRAIN_Y_HEIGHT, label, 0, Position.CONSTRAIN_Y_HEIGHT, this);
		// <DIV> //
		layout.put(Position.CONSTRAIN_X, div, 0, Position.CONSTRAIN_X_WIDTH, label);
		layout.put(Position.CONSTRAIN_Y, div, 0, Position.CONSTRAIN_Y, this);
		layout.put(Position.CONSTRAIN_Y_HEIGHT, div, 0, Position.CONSTRAIN_Y_HEIGHT, this);
		// <DIV> //
		layout.put(Position.CONSTRAIN_X, comboBox, 0, Position.CONSTRAIN_X_WIDTH, div);
		layout.put(Position.CONSTRAIN_Y, comboBox, 0, Position.CONSTRAIN_Y, this);
		layout.put(Position.CONSTRAIN_Y_HEIGHT, comboBox, 0, Position.CONSTRAIN_Y_HEIGHT, this);
		// <DIV> //
		layout.put(Position.CONSTRAIN_X_WIDTH, this, 0, Position.CONSTRAIN_X_WIDTH, comboBox);
	}
	
	public int getSelectedIndex(){
		return comboBox.getSelectedIndex();
	}
	
	public void setSelectedIndex(int index){
		comboBox.setSelectedIndex(index);
	}
	
	public JCustomizeComboBox<E> getComboBox(){
		return comboBox;
	}
	
	@Override
	public void addChangeActionListener(ChangeActionListener changeActionListener){
		comboBox.addChangeActionListener(changeActionListener);
	}
}
