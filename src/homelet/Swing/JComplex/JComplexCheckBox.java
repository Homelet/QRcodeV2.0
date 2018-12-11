/**
 * @author HomeletWei
 * @date Apr 5, 2018
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

import homelet.Swing.JBasic.JTextLabel;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * this is a enable parent
 *
 * @author HomeletWei
 * @date Apr 5, 2018
 */
public class JComplexCheckBox extends JComplex{
	
	/** @Fields <b>serialVersionUID</b> */
	private static final long serialVersionUID = 1L;
	JCheckBox checkBox;
	boolean   defaultChecked;
	
	/**
	 * constructor for JComplexCheckBox
	 *
	 * @param discription
	 * @param checkBoxTitle
	 * @param defaultChecked
	 * @author HomeletWei
	 */
	public JComplexCheckBox(String discription, String checkBoxTitle, boolean defaultChecked){
		this.checkBox = new JCheckBox(checkBoxTitle){
			private static final long serialVersionUID = 1L;
			
			@Override
			public void setSelected(boolean b){
				super.setSelected(b);
				onChildEnableChange(b);
			}
		};
		JTextLabel label = new JTextLabel(discription, Alignment.LEFT, Alignment.LEFT, 0, null, false);
		addEnableChild(label, defaultChecked);
		addRestoreDefaultChild(label);
		Layouter.GridBagLayouter layout = new GridBagLayouter(this);
		layout.put(layout.instanceOf(checkBox, 0, 0).setAnchor(Anchor.LEFT).setWeight(100, 0).setFill(Fill.HORIZONTAL));
		layout.put(layout.instanceOf(label, 0, 1).setAnchor(Anchor.LEFT).setWeight(100, 0).setFill(Fill.HORIZONTAL));
		initalize(defaultChecked);
		this.checkBox.addActionListener(e->onChildEnableChange(checkBox.isSelected()));
	}
	
	@Override
	public void onEnableChange(boolean newEnable){
		super.onEnableChange(newEnable);
		checkBox.setEnabled(newEnable);
	}
	
	/**
	 * @param defaultChecked
	 * @author HomeletWei
	 */
	public void initalize(boolean defaultChecked){
		this.defaultChecked = defaultChecked;
		restoreDefault();
	}
	
	@Override
	public void restoreDefault(){
		this.checkBox.setSelected(defaultChecked);
		this.onChildEnableChange(defaultChecked);
		super.restoreDefault();
	}
	
	public void setCheckChangeAble(boolean checkAble){
		checkBox.setEnabled(checkAble);
	}
	
	/**
	 * @return is checked
	 * @author HomeletWei
	 */
	public boolean isSelected(){
		return checkBox.isSelected();
	}
	
	/**
	 * @param selected
	 * @author HomeletWei
	 */
	public void setSelected(boolean selected){
		this.checkBox.setSelected(selected);
	}
	
	/**
	 * @param k
	 * @author HomeletWei
	 */
	public void setMnemonic(int k){
		this.checkBox.setMnemonic(k);
	}
	
	/**
	 * @param a
	 * @author HomeletWei
	 */
	public void addCheckBoxAction(ActionListener a){
		this.checkBox.addActionListener(a);
	}
}
