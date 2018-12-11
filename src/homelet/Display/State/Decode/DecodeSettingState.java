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
package homelet.Display.State.Decode;

import homelet.Display.Display;
import homelet.Display.State.States;
import homelet.Swing.JBasic.JDividerLine;
import homelet.Swing.JComplex.JComplexCheckBox;
import homelet.Swing.JComplex.JComplexComboBox;
import homelet.Swing.RestoreDefault;
import homelet.Swing.RestoreDefaultParent;
import homelet.Utile.Constants.CharacterSet;
import homelet.Utile.Constants.Sizing;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.SpringLayouter;
import homelet.Utile.Layouter.SpringLayouter.Position;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Mar 26, 2018
 */
public class DecodeSettingState extends States implements RestoreDefaultParent{
	
	private static final long serialVersionUID = 1L;
	/** @Fields <b>serialVersionUID</b> TODO */
	JComplexCheckBox          useAdvanceDecodeing;
	JComplexComboBox<String>  decodeCharacterSet;
	ArrayList<RestoreDefault> restoreDefaultChildList = new ArrayList<>();
	
	/**
	 * constructor for DecodeSettingState
	 * TODO
	 *
	 * @param name
	 * @author HomeletWei
	 */
	public DecodeSettingState(String name){
		super(name);
	}
	
	/**
	 * @see homelet.Display.State.States#createDisplay()
	 */
	@Override
	public void createDisplay(){
		this.addBackAction(Display.decodeState);
		this.addRestoreDefault(this);
		this.useAdvanceDecodeing = new JComplexCheckBox(null, "Advance Decode", false);
		this.useAdvanceDecodeing.setMnemonic(KeyEvent.VK_A);
		this.decodeCharacterSet = new JComplexComboBox<>("Decode Character Set:", CharacterSet.characterSets, CharacterSet.defaultValue);
		this.useAdvanceDecodeing.addEnableChild(decodeCharacterSet, false);
		this.addRestoreDefaultChild(useAdvanceDecodeing);
		this.addRestoreDefaultChild(decodeCharacterSet);
		JDividerLine            div_start = new JDividerLine();
		JDividerLine            div_end   = new JDividerLine();
		Layouter.SpringLayouter layout    = new SpringLayouter(displayPanel);
		//<div>//
		layout.put(Position.CONSTRAIN_X, div_start, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, div_start, 5, Position.CONSTRAIN_Y, displayPanel);
		layout.put(Position.CONSTRAIN_X_WIDTH, div_start, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		layout.put(layout.instanceOf(div_start).put(Position.VALUE_HEIGHT, SpringLayouter.valueOf(Sizing.Menu.dividerLine.height)));
		//<div>//
		layout.put(Position.CONSTRAIN_X, useAdvanceDecodeing, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, useAdvanceDecodeing, 5, Position.CONSTRAIN_Y_HEIGHT, div_start);
		layout.put(Position.CONSTRAIN_X_WIDTH, useAdvanceDecodeing, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		//<div>//
		layout.put(Position.CONSTRAIN_X, decodeCharacterSet, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, decodeCharacterSet, 5, Position.CONSTRAIN_Y_HEIGHT, useAdvanceDecodeing);
		layout.put(Position.CONSTRAIN_X_WIDTH, decodeCharacterSet, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		//<div>//
		layout.put(Position.CONSTRAIN_X, div_end, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, div_end, 5, Position.CONSTRAIN_Y_HEIGHT, decodeCharacterSet);
		layout.put(Position.CONSTRAIN_X_WIDTH, div_end, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		layout.put(layout.instanceOf(div_end).put(Position.VALUE_HEIGHT, SpringLayouter.valueOf(Sizing.Menu.dividerLine.height)));
	}
	
	/**
	 * @return the selected char set
	 * @author HomeletWei
	 */
	public String getDecodeCharacterSet(){
		return CharacterSet.getCharactertSet(decodeCharacterSet.getSelectedIndex());
	}
	
	@Override
	public ArrayList<RestoreDefault> getRestoreDefaultChildList(){
		return restoreDefaultChildList;
	}
}
