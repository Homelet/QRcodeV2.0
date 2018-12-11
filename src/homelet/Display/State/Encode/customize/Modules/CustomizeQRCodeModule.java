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
package homelet.Display.State.Encode.customize.Modules;

import homelet.Display.State.Encode.customize.CustomizeModule;
import homelet.QRCode.QRCode;
import homelet.Swing.*;
import homelet.Swing.JBasic.JCustomizeComboBox;
import homelet.Swing.JBasic.JDividerLine;
import homelet.Swing.JBasic.JDividerLine.DividerMode;
import homelet.Swing.JBasic.JTextLabel;
import homelet.Swing.JComplex.JComplexAlignmentModule;
import homelet.Swing.JComplex.JComplexColorChooser;
import homelet.Swing.JComplex.JComplexNumberRegulator;
import homelet.Swing.JComplex.JComplexSlider;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.Constants.QRcodeApperance;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;
import homelet.Utile.Layouter.SpringLayouter;
import homelet.Utile.Layouter.SpringLayouter.Position;
import homelet.Utile.Utility;
import homelet.Utile.Utility.Orientation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
public class CustomizeQRCodeModule extends CustomizeModule implements ChangeActionListener{
	
	@Override
	public void onChange(){
		updateQRcodeImage();
	}
	
	Dimension getMaximumSpaceForIcon(){
		return getQrCode().getMaximunSpaceForIcon(pixelPerUnit_regulator.getValue());
	}
	
	QRCode        qrCode;
	BufferedImage qrCodeImage;
	
	public CustomizeQRCodeModule(){
		super("QRCode");
		createDisplay();
	}
	
	JComplexNumberRegulator pixelPerUnit_regulator;
	JComplexAlignmentModule qrcodePosition_alignmentModule;
	QrcodeStyleModule       style_module;
	JComplexColorChooser    color_Chooser;
	
	@Override
	protected void createDisplay(){
		// QRcode Pixal Per Unit
		JTextLabel pixelPerUnit = new JTextLabel("Pixel Per Unit", Alignment.CENTER, Alignment.TOP, 0, null);
		pixelPerUnit_regulator = new JComplexNumberRegulator(1, 15, Integer.MAX_VALUE, 1, Orientation.HORIZONTAL);
		Utility.getUtility().setPreferredSize(pixelPerUnit_regulator, 100, 30);
		pixelPerUnit_regulator.addChangeActionListener(this);
		// QRcode Position
		JTextLabel qrcodePosition = new JTextLabel("Position", Alignment.CENTER, Alignment.TOP, 0, null);
		qrcodePosition_alignmentModule = new JComplexAlignmentModule();
		qrcodePosition_alignmentModule.addChangeActionListener(this);
//		Utility.getUtility().setPreferredSize(qrcodePosition_alignmentModule, 100, 150);
		// QRcode Style
		JTextLabel style = new JTextLabel("Style", Alignment.CENTER, Alignment.TOP, 0, null);
		style_module = new QrcodeStyleModule();
		style_module.addChangeActionListener(this);
//		Utility.getUtility().setPreferredSize(style_module, 100, 100);
		// QRcode Color
		JTextLabel color = new JTextLabel("Color", Alignment.CENTER, Alignment.TOP, 0, null);
		color_Chooser = new JComplexColorChooser(Color.BLACK, false, true, "QRCode Color");
		color_Chooser.addChangeActionListener(this);
//		Utility.getUtility().setPreferredSize(color_Chooser, 100, 50);
		// Apply Label Size
		Utility.getUtility().setPreferredSize(pixelPerUnit, LABLE_DI);
		Utility.getUtility().setPreferredSize(qrcodePosition, LABLE_DI);
		Utility.getUtility().setPreferredSize(style, LABLE_DI);
		Utility.getUtility().setPreferredSize(color, LABLE_DI);
		// layout
		Layouter.SpringLayouter layouter = new SpringLayouter(this.nestedPanel);
		//////////////// DIV ///////////////
		JDividerLine div_start = new JDividerLine(DividerMode.DOT, new Dimension(100, 10));
		layouter.put(Position.CONSTRAIN_X, div_start, 0, Position.CONSTRAIN_X, this.nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, div_start, 5, Position.CONSTRAIN_Y, this.nestedPanel);
		layouter.put(Position.CONSTRAIN_X_WIDTH, div_start, 0, Position.CONSTRAIN_X_WIDTH, this.nestedPanel);
		// DIV //
		layouter.put(Position.CONSTRAIN_X, pixelPerUnit, 0, Position.CONSTRAIN_X, this.nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, pixelPerUnit, 5, Position.CONSTRAIN_Y_HEIGHT, div_start);
		// DIV //
		layouter.put(Position.CONSTRAIN_X, pixelPerUnit_regulator, 0, Position.CONSTRAIN_X_WIDTH, pixelPerUnit);
		layouter.put(Position.CONSTRAIN_Y, pixelPerUnit_regulator, 0, Position.CONSTRAIN_Y, pixelPerUnit);
		layouter.put(Position.CONSTRAIN_X_WIDTH, pixelPerUnit_regulator, 0, Position.CONSTRAIN_X_WIDTH, this.nestedPanel);
		// DIV //
		layouter.put(Position.CONSTRAIN_Y_HEIGHT, pixelPerUnit, 0, Position.CONSTRAIN_Y_HEIGHT, pixelPerUnit_regulator);
		//////////////// DIV ///////////////
		JDividerLine div_one = new JDividerLine(DividerMode.DOT, new Dimension(100, 10));
		layouter.put(Position.CONSTRAIN_X, div_one, 0, Position.CONSTRAIN_X, this.nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, div_one, 5, Position.CONSTRAIN_Y_HEIGHT, pixelPerUnit_regulator);
		layouter.put(Position.CONSTRAIN_X_WIDTH, div_one, 0, Position.CONSTRAIN_X_WIDTH, this.nestedPanel);
		// DIV //
		layouter.put(Position.CONSTRAIN_X, qrcodePosition, 0, Position.CONSTRAIN_X, this.nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, qrcodePosition, 5, Position.CONSTRAIN_Y_HEIGHT, div_one);
		// DIV //
		layouter.put(Position.CONSTRAIN_X, qrcodePosition_alignmentModule, 0, Position.CONSTRAIN_X_WIDTH, qrcodePosition);
		layouter.put(Position.CONSTRAIN_Y, qrcodePosition_alignmentModule, 0, Position.CONSTRAIN_Y, qrcodePosition);
		layouter.put(Position.CONSTRAIN_X_WIDTH, qrcodePosition_alignmentModule, 0, Position.CONSTRAIN_X_WIDTH, this.nestedPanel);
		// DIV //
		layouter.put(Position.CONSTRAIN_Y_HEIGHT, qrcodePosition, 0, Position.CONSTRAIN_Y_HEIGHT, qrcodePosition_alignmentModule);
		//////////////// DIV ///////////////
		JDividerLine div_two = new JDividerLine(DividerMode.DOT, new Dimension(100, 10));
		layouter.put(Position.CONSTRAIN_X, div_two, 0, Position.CONSTRAIN_X, this.nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, div_two, 5, Position.CONSTRAIN_Y_HEIGHT, qrcodePosition_alignmentModule);
		layouter.put(Position.CONSTRAIN_X_WIDTH, div_two, 0, Position.CONSTRAIN_X_WIDTH, this.nestedPanel);
		// DIV //
		layouter.put(Position.CONSTRAIN_X, style, 0, Position.CONSTRAIN_X, this.nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, style, 5, Position.CONSTRAIN_Y_HEIGHT, div_two);
		// DIV //
		layouter.put(Position.CONSTRAIN_X, style_module, 0, Position.CONSTRAIN_X_WIDTH, style);
		layouter.put(Position.CONSTRAIN_Y, style_module, 0, Position.CONSTRAIN_Y, style);
		layouter.put(Position.CONSTRAIN_X_WIDTH, style_module, 0, Position.CONSTRAIN_X_WIDTH, this.nestedPanel);
		// DIV //
		layouter.put(Position.CONSTRAIN_Y_HEIGHT, style, 0, Position.CONSTRAIN_Y_HEIGHT, style_module);
		//////////////// DIV ///////////////
		JDividerLine div_three = new JDividerLine(DividerMode.DOT, new Dimension(100, 10));
		layouter.put(Position.CONSTRAIN_X, div_three, 0, Position.CONSTRAIN_X, this.nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, div_three, 5, Position.CONSTRAIN_Y_HEIGHT, style_module);
		layouter.put(Position.CONSTRAIN_X_WIDTH, div_three, 0, Position.CONSTRAIN_X_WIDTH, this.nestedPanel);
		// DIV //
		layouter.put(Position.CONSTRAIN_X, color, 0, Position.CONSTRAIN_X, this.nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, color, 5, Position.CONSTRAIN_Y_HEIGHT, div_three);
		// DIV //
		layouter.put(Position.CONSTRAIN_X, color_Chooser, 0, Position.CONSTRAIN_X_WIDTH, color);
		layouter.put(Position.CONSTRAIN_Y, color_Chooser, 0, Position.CONSTRAIN_Y, color);
		layouter.put(Position.CONSTRAIN_X_WIDTH, color_Chooser, 0, Position.CONSTRAIN_X_WIDTH, this.nestedPanel);
		// DIV //
		layouter.put(Position.CONSTRAIN_Y_HEIGHT, color, 0, Position.CONSTRAIN_Y_HEIGHT, color_Chooser);
		//////////////////////////////////
		JDividerLine div_end = new JDividerLine(DividerMode.DOT, new Dimension(100, 10));
		layouter.put(Position.CONSTRAIN_X, div_end, 0, Position.CONSTRAIN_X, this.nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, div_end, 5, Position.CONSTRAIN_Y_HEIGHT, color_Chooser);
		layouter.put(Position.CONSTRAIN_X_WIDTH, div_end, 0, Position.CONSTRAIN_X_WIDTH, this.nestedPanel);
		layouter.put(Position.CONSTRAIN_Y_HEIGHT, div_end, -5, Position.CONSTRAIN_Y_HEIGHT, this.nestedPanel);
		Utility.getUtility().setPreferredSize(this.nestedPanel, new Dimension(100, 350));
	}
	
	Point     vertex;
	Dimension di;
	
	@Override
	public Dimension getDimension(){
		if(qrCode != null){
			di = qrCode.getQrcodeDI(pixelPerUnit_regulator.getValue());
			return di;
		}else
			return null;
	}
	
	Dimension getDi(){
		return di;
	}
	
	Point getVertex(){
		return vertex;
	}
	
	@Override
	public Point getVertex(Dimension frameDimension, Dimension objectDI){
		if(qrCode != null){
			vertex = qrcodePosition_alignmentModule.getPoint(new Rectangle(frameDimension), objectDI);
			return vertex;
		}else
			return null;
	}
	
	@Override
	public void render(Graphics2D g){
		if(qrCodeImage != null){
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.drawImage(qrCodeImage, 0, 0, null);
		}
	}
	
	public QRCode getQrCode(){
		return qrCode;
	}
	
	public void setQrCode(QRCode qrCode){
		this.qrCode = qrCode;
		updateQRcodeImage();
	}
	
	private void updateQRcodeImage(){
		if(qrCode == null){
			qrCodeImage = null;
			return;
		}
		qrCodeImage = qrCode.draw(color_Chooser.getColoring(), style_module.getStyle(), pixelPerUnit_regulator.getValue(), style_module.getPersentageOfCurvature());
	}
}

class QrcodeStyleModule extends JComponent implements EnableListenerParent, RestoreDefaultParent{
	
	JCustomizeComboBox<String> style_picker;
	JComplexSlider             style_percentageOfCurvature;
	
	public QrcodeStyleModule(){
		style_picker = new JCustomizeComboBox<>(QRcodeApperance.qrcodeApperance, QRcodeApperance.defaultIndex);
		style_picker.addActionListener((actionEvent)->{
			update();
		});
		style_percentageOfCurvature = new JComplexSlider("Curvature", 100, 0, 100, "&v &u", "%");
		addRestoreDefaultChild(style_picker);
		addRestoreDefaultChild(style_percentageOfCurvature);
		addEnableChild(style_picker, true);
		addEnableChild(style_percentageOfCurvature, true);
		Layouter.GridBagLayouter layouter = new GridBagLayouter(this);
		layouter.put(layouter.instanceOf(style_picker, 0, 0).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		layouter.put(layouter.instanceOf(style_percentageOfCurvature, 0, 1).setAnchor(Anchor.CENTER).setFill(Fill.BOTH).setWeight(100, 100));
		update();
	}
	
	private void update(){
		switch(style_picker.getContent(QRcodeApperance.qrcodeApperances)){
			case LIQUEFIED:
				style_percentageOfCurvature.onEnableChange(true);
				break;
			case NORMAL:
				style_percentageOfCurvature.onEnableChange(false);
				break;
		}
	}
	
	public QRcodeApperance getStyle(){
		return style_picker.getContent(QRcodeApperance.qrcodeApperances);
	}
	
	public int getPersentageOfCurvature(){
		return style_percentageOfCurvature.getValue();
	}
	
	// restore default & enable
	ArrayList<EnableListener> enableListeners = new ArrayList<>();
	
	@Override
	public ArrayList<EnableListener> getEnableChildList(){
		return enableListeners;
	}
	
	ArrayList<RestoreDefault> restoreDefaults = new ArrayList<>();
	
	@Override
	public ArrayList<RestoreDefault> getRestoreDefaultChildList(){
		return restoreDefaults;
	}
	
	public void addChangeActionListener(ChangeActionListener changeActionListener){
		style_percentageOfCurvature.addChangeActionListener(changeActionListener);
		style_picker.addChangeActionListener(changeActionListener);
	}
}