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

import homelet.Swing.ChangeActionListener;
import homelet.Swing.JBasic.JTextLabel;
import homelet.Swing.JDrawer.Render;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;
import homelet.Utile.Utility.Orientation;

import java.awt.*;

public class JComplexInsetModule extends JComplex implements ChangeActionListener, Render{
	
	JComplexNumberRegulator top;
	JComplexNumberRegulator bottom;
	JComplexNumberRegulator right;
	JComplexNumberRegulator left;
	JComplexCheckBox        linked;
	// util
	Dimension               dimension;
	int[]                   insets = new int[4];
	
	@Override
	public void render(Graphics2D g){
	}
	
	public JComplexInsetModule(){
		JTextLabel top_label    = new JTextLabel("Top", Alignment.CENTER, Alignment.CENTER, 0, null);
		JTextLabel bottom_label = new JTextLabel("Bottom", Alignment.CENTER, Alignment.CENTER, 0, null);
		JTextLabel right_label  = new JTextLabel("Right", Alignment.CENTER, Alignment.CENTER, 0, null);
		JTextLabel left_label   = new JTextLabel("Left", Alignment.CENTER, Alignment.CENTER, 0, null);
		this.top = new JComplexNumberRegulator(1, 5, 5, 0, Orientation.HORIZONTAL);
		top.addChangeActionListener(()->{
			insets[0] = top.getValue();
		});
		top.addChangeActionListener(this);
		this.right = new JComplexNumberRegulator(1, 5, 5, 0, Orientation.HORIZONTAL);
		right.addChangeActionListener(()->{
			insets[1] = right.getValue();
		});
		right.addChangeActionListener(this);
		this.bottom = new JComplexNumberRegulator(1, 5, 5, 0, Orientation.HORIZONTAL);
		bottom.addChangeActionListener(()->{
			insets[2] = bottom.getValue();
		});
		bottom.addChangeActionListener(this);
		this.left = new JComplexNumberRegulator(1, 5, 5, 0, Orientation.HORIZONTAL);
		left.addChangeActionListener(()->{
			insets[3] = left.getValue();
		});
		left.addChangeActionListener(this);
		this.linked = new JComplexCheckBox(null, "Link", true);
		addRestoreDefaultChild(top);
		addRestoreDefaultChild(bottom);
		addRestoreDefaultChild(right);
		addRestoreDefaultChild(left);
		addRestoreDefaultChild(top_label);
		addRestoreDefaultChild(bottom_label);
		addRestoreDefaultChild(right_label);
		addRestoreDefaultChild(left_label);
		addRestoreDefaultChild(linked);
		addEnableChild(top, true);
		addEnableChild(bottom, true);
		addEnableChild(right, true);
		addEnableChild(left, true);
		addEnableChild(top_label, true);
		addEnableChild(bottom_label, true);
		addEnableChild(right_label, true);
		addEnableChild(left_label, true);
		addEnableChild(linked, true);
		Layouter.GridBagLayouter layouter = new GridBagLayouter(this);
		layouter.put(layouter.instanceOf(top_label, 0, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(0, 100));
		layouter.put(layouter.instanceOf(top, 1, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(0, 100));
	}
	
	public Dimension getDimension(){
		return dimension;
	}
	
	public void setDimension(Dimension dimension){
		this.dimension = dimension;
		updateBounds();
	}
	
	@Override
	public void onChange(){
		updateBounds();
	}
	
	private void updateBounds(){
		update_left_right_pair();
		update_top_bottom_pair();
	}
	
	private void update_left_right_pair(){
	}
	
	private void update_top_bottom_pair(){
	}
}
