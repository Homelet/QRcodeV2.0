/**
 * @author HomeletWei
 * @date Apr 14, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Swing.JBasic;

import homelet.Swing.ChangeActionListener;
import homelet.Swing.EnableListener;
import homelet.Swing.RestoreDefault;
import homelet.Utile.Utility;

import javax.swing.*;
import java.awt.*;

/**
 * TODO
 *
 * @param <E>
 * @author HomeletWei
 * @date Apr 14, 2018
 */
public class JCustomizeComboBox<E> extends javax.swing.JComboBox<E> implements EnableListener, RestoreDefault{
	
	/** @Fields <b>serialVersionUID</b> */
	private static final long serialVersionUID = 1L;
	int defaultIndex;
	
	/**
	 * constructor for JCustomizeComboBox
	 * TODO
	 *
	 * @param items
	 * @param defaultIndex
	 * @param DI
	 * @author HomeletWei
	 */
	public JCustomizeComboBox(E[] items, int defaultIndex, Dimension DI){
		this(items, defaultIndex);
		Utility.getUtility().setPreferredSize(this, DI);
	}
	
	/**
	 * constructor for JCustomizeComboBox
	 * TODO
	 *
	 * @param items
	 * @param defaultIndex
	 * @author HomeletWei
	 */
	public JCustomizeComboBox(E[] items, int defaultIndex){
		super(items);
		initalize(defaultIndex);
	}
	
	/**
	 * TODO
	 *
	 * @param defaultIndex
	 * @author HomeletWei
	 */
	public void initalize(int defaultIndex){
		this.defaultIndex = defaultIndex;
		restoreDefault();
	}
	
	@Override
	public void restoreDefault(){
		this.setSelectedIndex(defaultIndex);
	}
	
	/**
	 * @param newEnable
	 * @see homelet.Swing.EnableListener#onEnableChange(boolean)
	 */
	@Override
	public void onEnableChange(boolean newEnable){
		this.setEnabled(newEnable);
	}
	
	/**
	 * TODO
	 *
	 * @param items
	 * @author HomeletWei
	 */
	public void setModule(E[] items){
		this.setModel(new DefaultComboBoxModel<>(items));
	}
	
	/**
	 * TODO
	 *
	 * @return
	 * @author HomeletWei
	 */
	@SuppressWarnings("unchecked")
	public E getContent(){
		return (E) this.getSelectedItem();
	}
	
	/**
	 * TODO
	 *
	 * @return
	 * @author HomeletWei
	 */
	public <T> T getContent(T[] contents){
		return contents[this.getSelectedIndex()];
	}
	
	public void addChangeActionListener(ChangeActionListener changeActionListener){
		this.addActionListener((ActionEvent)->{
			changeActionListener.onChange();
		});
	}
}
