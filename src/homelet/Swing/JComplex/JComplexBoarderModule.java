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

import homelet.Display.State.Encode.customize.CustomizeModule;
import homelet.Swing.ChangeActionListener;
import homelet.Swing.JBasic.JCustomizeComboBox;
import homelet.Swing.JBasic.JDividerLine;
import homelet.Swing.JBasic.JDividerLine.DividerMode;
import homelet.Swing.JBasic.JTextLabel;
import homelet.Swing.JDrawer.Render;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.Constants.BorderStyle;
import homelet.Utile.GraphicsHandler;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;
import homelet.Utile.Utility;

import java.awt.*;

/**
 * choose border mode
 * choose border thickness
 * choose border color
 */
public class JComplexBoarderModule extends JComplex implements Render{
	
	JComplexSlider             borderThinkness;
	JComplexColorChooser       borderColor;
	JCustomizeComboBox<String> borderMode;
	
	public JComplexBoarderModule(){
		borderThinkness = new JComplexSlider("Boarder Thinkness", 1, 1, 1, "&v &u", "px");
		borderColor = new JComplexColorChooser(Color.BLACK, false, false, "Boarder Color");
		borderMode = new JCustomizeComboBox<>(BorderStyle.borderMode, BorderStyle.defaultValue);
		Layouter.GridBagLayouter layouter         = new GridBagLayouter(this);
		JTextLabel               boarderModeLable = new JTextLabel("Mode", Alignment.CENTER, Alignment.TOP, 0, null);
		Utility.getUtility().setPreferredSize(boarderModeLable, CustomizeModule.LABLE_DI);
		addEnableChild(borderThinkness, true);
		addEnableChild(borderColor, true);
		addEnableChild(borderMode, true);
		addEnableChild(boarderModeLable, true);
		addRestoreDefaultChild(borderThinkness);
		addRestoreDefaultChild(borderColor);
		addRestoreDefaultChild(borderMode);
		addRestoreDefaultChild(boarderModeLable);
		layouter.put(layouter.instanceOf(borderThinkness, 0, 0, 2, 1).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		layouter.put(layouter.instanceOf(new JDividerLine(DividerMode.DOT, new Dimension(100, 10)), 0, 1, 2, 1).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 0));
		layouter.put(layouter.instanceOf(boarderModeLable, 0, 2, 1, 1).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(0, 100));
		layouter.put(layouter.instanceOf(borderMode, 1, 2, 1, 1).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		layouter.put(layouter.instanceOf(new JDividerLine(DividerMode.DOT, new Dimension(100, 10)), 0, 3, 2, 1).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 0));
		layouter.put(layouter.instanceOf(borderColor, 0, 4, 2, 1).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
	}
	
	@Override
	public void render(Graphics2D g){
		GraphicsHandler.getGraphicsHandler().drawBorder(g, g.getClipBounds(), getBorderThinkness(), getBorderStyle(), getColor());
	}
	
	public void setBorderThinknessMax(int max){
		borderThinkness.setMax(max);
	}
	
	public void setBorderThinknessMin(int min){
		borderThinkness.setMin(min);
	}
	
	public int getBorderThinkness(){
		return borderThinkness.getValue();
	}
	
	public BorderStyle getBorderStyle(){
		return borderMode.getContent(BorderStyle.border);
	}
	
	public Color getColor(){
		return borderColor.getColoring().getColor();
	}
	
	@Override
	public void addChangeActionListener(ChangeActionListener changeActionListener){
		super.addChangeActionListener(changeActionListener);
		borderThinkness.addChangeActionListener(changeActionListener);
		borderColor.addChangeActionListener(changeActionListener);
		borderMode.addChangeActionListener(changeActionListener);
	}
}
