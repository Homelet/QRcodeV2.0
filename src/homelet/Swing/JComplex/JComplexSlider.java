/**
 * @author HomeletWei
 * @date Mar 30, 2018
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
import homelet.Swing.JBasic.JTextLabel;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.Constants.Sizing;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.SpringLayouter;
import homelet.Utile.Layouter.SpringLayouter.Position;
import homelet.Utile.Utility;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseAdapter;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Mar 30, 2018
 */
public class JComplexSlider extends JComplex{
	
	/** @Fields <b>VALUE_UNIT</b> EX: 100 px */
	public static final  String VALUE_UNIT         = "&v &u";
	/** @Fields <b>VALUE_X_VALUE_UNIT</b> EX: 100 X 100 px */
	public static final  String VALUE_X_VALUE_UNIT = "&v X &v &u";
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long   serialVersionUID   = 1L;
	int defaultMax, defaultMin, defaultIntial;
	ChangeListener cl;
	JSlider        slider;
	JTextLabel     resultPanel;
	private String unit;
	private String currentShowingMode;
	private float  resultRatio = 1.0F;
	
	/**
	 * constructor for JComplexSlider
	 * TODO
	 *
	 * @param discrtiption
	 * @param sliderMax
	 * @param sliderMin
	 * @param inital
	 * @param resultPanelShowingMode
	 * @author HomeletWei
	 */
	public JComplexSlider(String discrtiption, int sliderMax, int sliderMin, int inital, String resultPanelShowingMode){
		this(discrtiption, sliderMax, sliderMin, inital, resultPanelShowingMode, "(px)");
	}
	
	/**
	 * constructor for JComplexSlider
	 * TODO
	 *
	 * @param discrtiption
	 * @param sliderMax
	 * @param sliderMin
	 * @param inital
	 * @param resultPanelShowingMode
	 * @param unit
	 * @author HomeletWei
	 */
	public JComplexSlider(String discrtiption, int sliderMax, int sliderMin, int inital, String resultPanelShowingMode, String unit){
		this.unit = unit;
		this.slider = new JSlider();
		setDisplayingFormat(resultPanelShowingMode);
		initialize(sliderMax, sliderMin, inital);
		this.resultPanel = new JTextLabel("", Alignment.CENTER, Alignment.CENTER, 0, null, false);
		JTextLabel description = new JTextLabel(discrtiption, Alignment.CENTER, Alignment.CENTER, 0, null, false);
		addRestoreDefaultChild(description);
		//	addRestoreDefaultChild(resultPanel);
		addEnableChild(description, false);
		addEnableChild(resultPanel, false);
		Utility.getUtility().setPreferredSize(description, Sizing.Menu.tripleOne, Sizing.Menu.smallItemHeight);
		Utility.getUtility().setPreferredSize(resultPanel, Sizing.Menu.tripleThree, Sizing.Menu.smallItemHeight);
		Layouter.SpringLayouter layout = new SpringLayouter(this);
		layout.put(layout.instanceOf(this).put(Position.CONSTRAIN_Y_HEIGHT, Sizing.Menu.smallItemHeight));
		// <DIV> //
		layout.put(Position.CONSTRAIN_X, description, 0, Position.CONSTRAIN_X, this);
		layout.put(Position.CONSTRAIN_Y, description, 0, Position.CONSTRAIN_Y, this);
		layout.put(Position.CONSTRAIN_Y_HEIGHT, description, 0, Position.CONSTRAIN_Y_HEIGHT, this);
		// <DIV> //
		layout.put(Position.CONSTRAIN_X, slider, 0, Position.CONSTRAIN_X_WIDTH, description);
		layout.put(Position.CONSTRAIN_Y, slider, 0, Position.CONSTRAIN_Y, this);
		layout.put(Position.CONSTRAIN_Y_HEIGHT, slider, 0, Position.CONSTRAIN_Y_HEIGHT, this);
		// <DIV> //
		layout.put(Position.CONSTRAIN_X, resultPanel, 0, Position.CONSTRAIN_X_WIDTH, slider);
		layout.put(Position.CONSTRAIN_Y, resultPanel, 0, Position.CONSTRAIN_Y, this);
		layout.put(Position.CONSTRAIN_Y_HEIGHT, resultPanel, 0, Position.CONSTRAIN_Y_HEIGHT, this);
		// <DIV> //
		layout.put(Position.CONSTRAIN_X_WIDTH, this, 0, Position.CONSTRAIN_X_WIDTH, resultPanel);
		addAction();
		restoreDefault();
	}
	
	/**
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public void initialize(int sliderMax, int sliderMin, int inital){
		this.defaultMax = sliderMax;
		this.defaultMin = sliderMin;
		this.defaultIntial = inital;
	}
	
	private void addAction(){
		slider.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e){
				resultPanel.setText(convertToResultPanelText());
				if(cl != null)
					cl.stateChanged(e);
			}
		});
		resultPanel.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e){
				if(e.getClickCount() == 2 && slider.isEnabled()){
					String filen = (String) (JOptionPane.showInputDialog(JComplexSlider.this, "Enter your new Value", "Enter new value", JOptionPane.PLAIN_MESSAGE, null, null, String.valueOf((int) (getValue() * resultRatio))));
					if(filen == null){
						return;
					}
					int value = 0;
					try{
						value = (int) (Integer.parseInt(filen) / resultRatio);
					}catch(NumberFormatException es){
						JOptionPane.showMessageDialog(JComplexSlider.this, "Unparsable Input For \"".concat(filen).concat("\""), "Unparsable Input", JOptionPane.ERROR_MESSAGE, null);
						return;
					}
					switch(Utility.getUtility().betweenPeaks(value, slider.getMaximum(), slider.getMinimum())){
						case 2:
							switch(JOptionPane.showConfirmDialog(JComplexSlider.this, "The value \"" + filen + "\" is bigger than it's maximum\nDo you want to continue as Maximum?", "illegal Argument", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null)){
								case JOptionPane.YES_OPTION:
									value = slider.getMaximum();
									break;
								case JOptionPane.NO_OPTION:
									return;
							}
							break;
						case -2:
							switch(JOptionPane.showConfirmDialog(JComplexSlider.this, "The value \"" + filen + "\" is smaller than it's minimum\nDo you want to continue as Minimum?", "illegal Argument", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null)){
								case JOptionPane.YES_OPTION:
									value = slider.getMinimum();
									break;
								case JOptionPane.NO_OPTION:
									return;
							}
							break;
						case 1:
						case 0:
						case -1:
							break;
					}
					slider.setValue(value);
				}
			}
		});
	}
	
	@Override
	public void restoreDefault(){
		this.slider.setMaximum(defaultMax);
		this.slider.setMinimum(defaultMin);
		this.slider.setValue(defaultIntial);
		super.restoreDefault();
	}
	
	private String convertToResultPanelText(){
		StringBuilder builder = new StringBuilder();
		for(int index = 0; index < currentShowingMode.length(); index++){
			if(currentShowingMode.charAt(index) == '&'){
				if(currentShowingMode.charAt(index + 1) == 'v'){
					builder.append((int) (slider.getValue() * resultRatio));
					index++;
					continue;
				}else if(currentShowingMode.charAt(index + 1) == 'u'){
					builder.append(unit);
					index++;
					continue;
				}
			}
			builder.append(currentShowingMode.charAt(index));
		}
		return builder.toString();
	}
	
	/**
	 * @return the current value
	 * @author HomeletWei
	 */
	public int getValue(){
		return slider.getValue();
	}
	
	/**
	 * @param value
	 * @author HomeletWei
	 */
	public void setValue(int value){
		slider.setValue(value);
	}
	
	/**
	 * @param newMax
	 * @author HomeletWei
	 */
	public void setMax(int newMax){
		this.slider.setMaximum(newMax);
	}
	
	/**
	 * @param newMin
	 * @author HomeletWei
	 */
	public void setMin(int newMin){
		this.slider.setMinimum(newMin);
	}
	
	/**
	 * @param cl
	 * @author HomeletWei
	 */
	public void addChangeListener(ChangeListener cl){
		this.cl = cl;
	}
	
	/**
	 * @param bool
	 * @author HomeletWei
	 */
	public void setSliderEnable(boolean bool){
		this.slider.setEnabled(bool);
	}
	
	/**
	 * @return the ratio
	 * @author HomeletWei
	 */
	public float getResultRatio(){
		return resultRatio;
	}
	
	/**
	 * @param newRatio
	 * @author HomeletWei
	 */
	public void setResultRatio(float newRatio){
		this.resultRatio = newRatio;
		resultPanel.setText(convertToResultPanelText());
	}
	
	/**
	 * the result format must be set in a way which follows the syntax
	 * <br>
	 * <ul>
	 * <li>the unit is &u</li>
	 * <li>the value is &v</li>
	 * <li>other character can be as same as they looked</li>
	 * </ul>
	 * For Example:
	 * <br>
	 * &v X &v &u => 100 X 100 pix
	 * <br>
	 * &vExample&u => 100Examplepix
	 * <br>
	 * <br>
	 *
	 * @param format the new Format
	 * @author HomeletWei
	 */
	public void setDisplayingFormat(String format){
		this.currentShowingMode = format;
	}
	
	@Override
	public void onEnableChange(boolean newEnable){
		this.slider.setEnabled(newEnable);
		super.onEnableChange(newEnable);
	}
	
	@Override
	public void addChangeActionListener(ChangeActionListener changeActionListener){
		slider.addChangeListener((a)->{
			changeActionListener.onChange();
		});
	}
}
