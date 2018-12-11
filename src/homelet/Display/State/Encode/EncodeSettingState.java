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
package homelet.Display.State.Encode;

import homelet.Display.Display;
import homelet.Display.State.States;
import homelet.QRCode.encode.EncodePreset;
import homelet.Swing.JBasic.JDividerLine;
import homelet.Swing.JComplex.JComplexCheckBox;
import homelet.Swing.JComplex.JComplexComboBox;
import homelet.Swing.RestoreDefault;
import homelet.Swing.RestoreDefaultParent;
import homelet.Utile.Constants.*;
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
public class EncodeSettingState extends States implements RestoreDefaultParent{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	//    final String	      useCustomizeTextFieldNotice  =
	//                                                          "----------Notice before using a customized size canvas---------\n"
	//                                                             + "->Use customized size canvas will not effect the size of the QRCode\n"
	//                                                             + "->Since the display consists a Dimension of 500x500 px, any other Dimension applies may be resized when displaying (no effect on output result)\n"
	//                                                             + "->The minimum size may be effect by the Generated QRCode size, when effected, the customized Dimention will be resized to the nearest bounds";
	final                int  maxSizeDI        = 1000;
	JComplexCheckBox          useAdvanceEncoding;
	JComplexComboBox<String>  encodeCharacterSet;
	JComplexComboBox<String>  encodeCorrectionLevel;
	JComplexComboBox<String>  encodeMode;
	JComplexComboBox<String>  encodeVersion;
	//	JComplexCheckBox useCustomSize;
	//	JComplexSlider width;
	//	JComplexSlider height;
	ArrayList<RestoreDefault> restoreDefaultChildList = new ArrayList<>();
	
	/**
	 * constructor for EncodeSettingState
	 * TODO
	 *
	 * @param name
	 * @author HomeletWei
	 */
	public EncodeSettingState(String name){
		super(name);
	}
	
	/**
	 * @see homelet.Display.State.States#createDisplay()
	 */
	@Override
	public void createDisplay(){
		this.addBackAction(Display.encodeState);
		this.addRestoreDefault(this);
		this.useAdvanceEncoding = new JComplexCheckBox(null, "Advance Encoding", false);
		this.useAdvanceEncoding.setMnemonic(KeyEvent.VK_A);
		this.encodeCharacterSet = new JComplexComboBox<>("Encode Character Set:", CharacterSet.characterSets, CharacterSet.defaultValue);
		this.encodeCorrectionLevel = new JComplexComboBox<>("Encode Correction Level:", EncodeCorrectionLevel.correctionLevel, EncodeCorrectionLevel.defaultValue);
		this.encodeMode = new JComplexComboBox<>("Encode Mode:", EncodeMode.encodeMode, EncodeMode.defaultValue);
		this.encodeVersion = new JComplexComboBox<>("Encode Version:", EncodeVersion.versionList, EncodeVersion.defaultValue);
		this.useAdvanceEncoding.addEnableChild(encodeCharacterSet, false);
		this.useAdvanceEncoding.addEnableChild(encodeCorrectionLevel, false);
		this.useAdvanceEncoding.addEnableChild(encodeMode, false);
		this.useAdvanceEncoding.addEnableChild(encodeVersion, false);
		//		this.useCustomSize = new JComplexCheckBox(null, "Use customize size canvas", false);
		//		this.useCustomSize.setMnemonic(KeyEvent.VK_C);
		//		this.useCustomSize.addCheckBoxAction((e) -> {
		//			if (useCustomSize.isSelected() == true) {
		//				int result = JOptionPane.showConfirmDialog(useCustomSize, "Are you sure to Use Use Customized Size Canvas?\nFor More Information please refer to", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
		//				if (result == JOptionPane.NO_OPTION) {
		//					useCustomSize.setSelected(false);
		//				}
		//			}
		//		});
		//		this.width = new JComplexSlider("width", maxSizeDI, 0, 500, JComplexSlider.VALUE_UNIT);
		//		this.height = new JComplexSlider("height", maxSizeDI, 0, 500, JComplexSlider.VALUE_UNIT);
		//		this.useCustomSize.addEnableChild(width, false);
		//		this.useCustomSize.addEnableChild(height, false);
		this.addRestoreDefaultChild(useAdvanceEncoding);
		this.addRestoreDefaultChild(encodeCharacterSet);
		this.addRestoreDefaultChild(encodeCorrectionLevel);
		this.addRestoreDefaultChild(encodeMode);
		this.addRestoreDefaultChild(encodeVersion);
		//		this.addRestoreDefaultChild(useCustomSize);
		//		this.addRestoreDefaultChild(width);
		//		this.addRestoreDefaultChild(height);
		JDividerLine div_start = new JDividerLine();
		//		JDividerLine div_mid = new JDividerLine();
		JDividerLine            div_end = new JDividerLine();
		Layouter.SpringLayouter layout  = new SpringLayouter(displayPanel);
		layout.put(Position.CONSTRAIN_X, div_start, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, div_start, 5, Position.CONSTRAIN_Y, displayPanel);
		layout.put(Position.CONSTRAIN_X_WIDTH, div_start, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		layout.put(layout.instanceOf(div_start).put(Position.VALUE_HEIGHT, SpringLayouter.valueOf(Sizing.Menu.dividerLine.height)));
		// <div> //
		////////////////////////////////////////////////////////////////////////////////
		layout.put(Position.CONSTRAIN_X, useAdvanceEncoding, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, useAdvanceEncoding, 5, Position.CONSTRAIN_Y_HEIGHT, div_start);
		layout.put(Position.CONSTRAIN_X_WIDTH, useAdvanceEncoding, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		//<div>//
		layout.put(Position.CONSTRAIN_X, encodeCharacterSet, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, encodeCharacterSet, 5, Position.CONSTRAIN_Y_HEIGHT, useAdvanceEncoding);
		layout.put(Position.CONSTRAIN_X_WIDTH, encodeCharacterSet, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		//<div>//
		layout.put(Position.CONSTRAIN_X, encodeCorrectionLevel, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, encodeCorrectionLevel, 5, Position.CONSTRAIN_Y_HEIGHT, encodeCharacterSet);
		layout.put(Position.CONSTRAIN_X_WIDTH, encodeCorrectionLevel, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		//<div>//
		layout.put(Position.CONSTRAIN_X, encodeMode, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, encodeMode, 5, Position.CONSTRAIN_Y_HEIGHT, encodeCorrectionLevel);
		layout.put(Position.CONSTRAIN_X_WIDTH, encodeMode, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		//<div>//
		layout.put(Position.CONSTRAIN_X, encodeVersion, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, encodeVersion, 5, Position.CONSTRAIN_Y_HEIGHT, encodeMode);
		layout.put(Position.CONSTRAIN_X_WIDTH, encodeVersion, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		//<div>//
		////////////////////////////////////////////////////////////////////////////////
		//		layout.put(Position.CONSTRAIN_X, div_mid, 5, Position.CONSTRAIN_X, displayPanel);
		//		layout.put(Position.CONSTRAIN_Y, div_mid, 5, Position.CONSTRAIN_Y_HEIGHT, encodeVersion);
		//		layout.put(Position.CONSTRAIN_X_WIDTH, div_mid, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		//		layout.put(layout.instanceOf(div_mid).put(Position.VALUE_HEIGHT, SpringLayouter.valueOf(Sizing.Menu.dividerLine.height)));
		// <div> //
		////////////////////////////////////////////////////////////////////////////////
		//		layout.put(Position.CONSTRAIN_X, useCustomSize, 5, Position.CONSTRAIN_X, displayPanel);
		//		layout.put(Position.CONSTRAIN_Y, useCustomSize, 5, Position.CONSTRAIN_Y_HEIGHT, div_mid);
		//		layout.put(Position.CONSTRAIN_X_WIDTH, useCustomSize, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		//		//<div>//
		//		layout.put(Position.CONSTRAIN_X, width, 5, Position.CONSTRAIN_X, displayPanel);
		//		layout.put(Position.CONSTRAIN_Y, width, 5, Position.CONSTRAIN_Y_HEIGHT, useCustomSize);
		//		layout.put(Position.CONSTRAIN_X_WIDTH, width, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		//		//<div>//
		//		layout.put(Position.CONSTRAIN_X, height, 5, Position.CONSTRAIN_X, displayPanel);
		//		layout.put(Position.CONSTRAIN_Y, height, 5, Position.CONSTRAIN_Y_HEIGHT, width);
		//		layout.put(Position.CONSTRAIN_X_WIDTH, height, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		//		// <div> //
		////////////////////////////////////////////////////////////////////////////////
		layout.put(Position.CONSTRAIN_X, div_end, 5, Position.CONSTRAIN_X, displayPanel);
		layout.put(Position.CONSTRAIN_Y, div_end, 5, Position.CONSTRAIN_Y_HEIGHT, encodeVersion);
		layout.put(Position.CONSTRAIN_X_WIDTH, div_end, -5, Position.CONSTRAIN_X_WIDTH, displayPanel);
		layout.put(layout.instanceOf(div_end).put(Position.VALUE_HEIGHT, SpringLayouter.valueOf(Sizing.Menu.dividerLine.height)));
	}
	
	EncodePreset getEncodePreset(){
		return EncodePreset.getInstance(useAdvanceEncoding.isSelected(), encodeCorrectionLevel.getSelectedIndex(), encodeMode.getSelectedIndex(), encodeVersion.getSelectedIndex(), encodeCharacterSet.getSelectedIndex());
	}
	
	/**
	 * @return is used advanceEncodings
	 * @author HomeletWei
	 */
	public boolean isUseAdvanceEncoding(){
		return useAdvanceEncoding.isSelected();
	}
	
	/**
	 * @param selected
	 * @author HomeletWei
	 */
	public void setUseAdvanceEncoding(boolean selected){
		useAdvanceEncoding.setSelected(selected);
	}
	//	/**
	//	 * @return the dimension which specific
	//	 * @author HomeletWei
	//	 */
	//	public Dimension getCustomisedDimention() {
	//		return new Dimension(width.getValue(), height.getValue());
	//	}
	
	@Override
	public ArrayList<RestoreDefault> getRestoreDefaultChildList(){
		return restoreDefaultChildList;
	}
}
