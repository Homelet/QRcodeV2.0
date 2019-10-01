/*
 * Copyright (c) 2018 Homelet Wei
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

import homelet.Swing.JBasic.JCustomizeComboBox;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;

import java.awt.*;

public class JComplexAlignmentModule extends JComplex{
	
	JCustomizeComboBox<String> alignments;
	JComplexSlider             x;
	JComplexSlider             y;
	
	public JComplexAlignmentModule(){
		alignments = new JCustomizeComboBox<>(Alignment.alignment, Alignment.defaultValue);
		alignments.addActionListener((actionEvent)->{
			updateSlider();
			onChangeActionInvoke();
		});
		x = new JComplexSlider("X", 100, 0, 0, "&v &u", "%");
		y = new JComplexSlider("Y", 100, 0, 0, "&v &u", "%");
		addRestoreDefaultChild(alignments);
		addRestoreDefaultChild(x);
		addRestoreDefaultChild(y);
		addEnableChild(alignments, true);
		addEnableChild(x, true);
		addEnableChild(y, true);
		Layouter.GridBagLayouter layouter = new GridBagLayouter(this);
		layouter.put(layouter.instanceOf(alignments, 0, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		layouter.put(layouter.instanceOf(x, 0, 1).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		layouter.put(layouter.instanceOf(y, 0, 2).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		updateSlider();
	}
	
	private void updateSlider(){
		switch(alignments.getContent(Alignment.alignments)){
			////////////////////full alignment//////////////////////////
			case NORTH_WEST:
			case TOP_LEFT:
				updateEnable(false, false);
				x.setValue(0);
				y.setValue(0);
				break;
			case NORTH:
			case TOP:
				updateEnable(false, false);
				x.setValue(50);
				y.setValue(0);
				break;
			case NORTH_EAST:
			case TOP_RIGHT:
				updateEnable(false, false);
				x.setValue(100);
				y.setValue(0);
				break;
			case WEST:
			case LEFT:
				updateEnable(false, false);
				x.setValue(0);
				y.setValue(50);
				break;
			case ORIGIN:
			case CENTER:
				updateEnable(false, false);
				x.setValue(50);
				y.setValue(50);
				break;
			case EAST:
			case RIGHT:
				updateEnable(false, false);
				x.setValue(100);
				y.setValue(50);
				break;
			case SOUTH_WEST:
			case BOTTOM_LEFT:
				updateEnable(false, false);
				x.setValue(0);
				y.setValue(100);
				break;
			case SOUTH:
			case BOTTOM:
				updateEnable(false, false);
				x.setValue(50);
				y.setValue(100);
				break;
			case SOUTH_EAST:
			case BOTTOM_RIGHT:
				updateEnable(false, false);
				x.setValue(100);
				y.setValue(100);
				break;
			////////////////////half alignment//////////////////////////
			case KEEP_X_ON_WEST:
			case KEEP_X_ON_LEFT:
				updateEnable(false, true);
				x.setValue(0);
				break;
			case KEEP_X_ON_ORIGIN:
			case KEEP_X_ON_CENTER:
				updateEnable(false, true);
				x.setValue(50);
				break;
			case KEEP_X_ON_EAST:
			case KEEP_X_ON_RIGHT:
				updateEnable(false, true);
				x.setValue(100);
				break;
			case KEEP_Y_ON_NORTH:
			case KEEP_Y_ON_TOP:
				updateEnable(true, false);
				y.setValue(0);
				break;
			case KEEP_Y_ON_ORIGIN:
			case KEEP_Y_ON_CENTER:
				updateEnable(true, false);
				y.setValue(50);
				break;
			case KEEP_Y_ON_SOUTH:
			case KEEP_Y_ON_BOTTOM:
				updateEnable(true, false);
				y.setValue(100);
				break;
			////////////////////full customize alignment//////////////////////////
			case CUSTOMIZE:
				updateEnable(true, true);
				break;
		}
	}
	
	private void updateEnable(boolean x, boolean y){
		this.x.onEnableChange(x);
		this.y.onEnableChange(y);
	}
	
	public Point getPoint(Rectangle frame, Dimension objectDI){
		return Alignment.getDesiredVertex(alignments.getSelectedIndex(), frame, toPoint(frame, objectDI), objectDI);
	}
	
	private Point toPoint(Rectangle frame, Dimension objectDI){
		int realX = (int) (Integer.valueOf(frame.width - objectDI.width).floatValue() / 100 * x.getValue());
		int realY = (int) (Integer.valueOf(frame.height - objectDI.height).floatValue() / 100 * y.getValue());
		return new Point(realX, realY);
	}
	
	@Override
	public void restoreDefault(){
	}
}
