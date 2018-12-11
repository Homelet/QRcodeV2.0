/*
 * Copyright (c) 2018. Homelet Wei
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package homelet.Swing.JComplex;

import homelet.Swing.JBasic.JTextButton;
import homelet.Swing.JBasic.JTextLabel;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;
import homelet.Utile.StringDrawer.StringDrawer;
import homelet.Utile.Utility;
import homelet.Utile.Utility.Orientation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * <pre>
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 * </pre>
 *
 * @author HomeletWei
 * @date 2018-07-16
 */
public class JComplexNumberRegulator extends JComplex{
	
	JTextLabel  label;
	JTextButton plus;
	JTextButton minus;
	int         step, max, min, init;
	
	public JComplexNumberRegulator(int init, Orientation orientation){
		this(1, init, Integer.MAX_VALUE, Integer.MIN_VALUE, orientation);
	}
	
	public JComplexNumberRegulator(int step, int init, int max, int min, Orientation orientation){
		super();
		this.step = step;
		this.max = max;
		this.min = min;
		this.init = init;
		this.plus = new JTextButton("+".concat(step != 1 ? String.valueOf(step) : ""), (actionEvent)->{
			processShift(+this.step);
			onChangeActionInvoke();
		});
		this.minus = new JTextButton("-".concat(step != 1 ? String.valueOf(step) : ""), (actionEvent)->{
			processShift(-this.step);
			onChangeActionInvoke();
		});
		this.label = new JTextLabel(String.valueOf(init), Alignment.CENTER, Alignment.TOP, 0, StringDrawer.defaultFont.deriveFont(Font.PLAIN, 30));
		addAction();
		addEnableChild(plus, true);
		addEnableChild(minus, true);
		addEnableChild(label, true);
		addRestoreDefaultChild(plus);
		addRestoreDefaultChild(minus);
		addRestoreDefaultChild(label);
		Layouter.GridBagLayouter layouter = new GridBagLayouter(this);
		switch(orientation){
			case EQUILATERAL:
			case HORIZONTAL:
				layouter.put(layouter.instanceOf(minus, 0, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(0, 100));
				layouter.put(layouter.instanceOf(label, 1, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
				layouter.put(layouter.instanceOf(plus, 2, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(0, 100));
				break;
			case VERTICAL:
				layouter.put(layouter.instanceOf(minus, 0, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(0, 100));
				layouter.put(layouter.instanceOf(label, 0, 1).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
				layouter.put(layouter.instanceOf(plus, 0, 2).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(0, 100));
				break;
		}
	}
	
	private void processShift(int shift){
		processValue(getValue() + shift);
	}
	
	private void addAction(){
		label.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e){
				if(e.getClickCount() == 2 && label.isEnabled()){
					String filen = (String) (JOptionPane.showInputDialog(JComplexNumberRegulator.this, "Enter your new Value", "Enter new value", JOptionPane.PLAIN_MESSAGE, null, null, String.valueOf(getValue())));
					if(filen == null){
						return;
					}
					int value = 0;
					try{
						value = Integer.parseInt(filen);
					}catch(NumberFormatException es){
						JOptionPane.showMessageDialog(JComplexNumberRegulator.this, "Unparsable Input For \"".concat(filen).concat("\""), "Unparsable Input", JOptionPane.ERROR_MESSAGE, null);
						return;
					}
					switch(Utility.getUtility().betweenPeaks(value, getMaximum(), getMinimum())){
						case -2:
							switch(JOptionPane.showConfirmDialog(JComplexNumberRegulator.this, "The value \"" + filen + "\" is smaller than it's minimum\nDo you want to continue as Minimum?", "illegal Argument", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null)){
								case JOptionPane.NO_OPTION:
									return;
								case JOptionPane.YES_OPTION:
									value = getMinimum();
							}
							break;
						case 2:
							switch(JOptionPane.showConfirmDialog(JComplexNumberRegulator.this, "The value \"" + filen + "\" is bigger than it's maximum\nDo you want to continue as Maximum?", "illegal Argument", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null)){
								case JOptionPane.NO_OPTION:
									return;
								case JOptionPane.YES_OPTION:
									value = getMaximum();
							}
							break;
						case -1:
						case 1:
						case 0:
							break;
					}
					setValue(value);
					onChangeActionInvoke();
				}
			}
		});
	}
	
	private void processValue(int newValue){
		switch(Utility.getUtility().betweenPeaks(newValue, max, min)){
			case -2:
			case -1:
				minus.onEnableChange(false);
				setValue(min);
				break;
			case 2:
			case 1:
				plus.onEnableChange(false);
				setValue(max);
				break;
			case 0:
				minus.onEnableChange(true);
				plus.onEnableChange(true);
				setValue(newValue);
				break;
		}
	}
	
	public int getValue(){
		return Integer.parseInt(label.getText());
	}
	
	public void setValue(int value){
		label.setText(String.valueOf(value));
	}
	
	public int getMaximum(){
		return max;
	}
	
	public int getMinimum(){
		return min;
	}
	
	public void setMaximum(int max){
		if(max < min)
			max = min;
		this.max = max;
		processValue(getValue());
	}
	
	public void setMinimum(int min){
		if(min > max)
			min = max;
		this.min = min;
		processValue(getValue());
	}
	
	public int getStep(){
		return step;
	}
	
	public void setStep(int step){
		this.step = step;
	}
	
	@Override
	public void restoreDefault(){
		processValue(init);
	}
}
