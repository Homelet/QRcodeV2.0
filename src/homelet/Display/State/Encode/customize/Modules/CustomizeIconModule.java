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
package homelet.Display.State.Encode.customize.Modules;

import homelet.Display.State.Encode.customize.CustomizeModule;
import homelet.Swing.JBasic.*;
import homelet.Swing.JBasic.JDividerLine.DividerMode;
import homelet.Swing.JComplex.JComplexBoarderModule;
import homelet.Swing.JComplex.JComplexCheckBox;
import homelet.Swing.JComplex.JComplexColorChooser;
import homelet.Utile.Constants.Alignment;
import homelet.Utile.Constants.ImageResizeMode;
import homelet.Utile.FileHandler;
import homelet.Utile.GraphicsHandler;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;
import homelet.Utile.Layouter.SpringLayouter;
import homelet.Utile.Layouter.SpringLayouter.Position;
import homelet.Utile.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CustomizeIconModule extends CustomizeModule{
	
	CustomizeQRCodeModule qrCodeModule;
	
	public CustomizeIconModule(CustomizeQRCodeModule qrCodeModule){
		super("Icon");
		this.qrCodeModule = qrCodeModule;
		createDisplay();
	}
	
	JComplexCheckBox           isDrawIcon;
	JImagePreview              icon_preview;
	BufferedImage              icon_image;
	JCustomizeComboBox<String> icon_resize_mode_picker;
	JComplexColorChooser       icon_fill_chooser;
	JComplexCheckBox           isDrawBoarder;
	JComplexBoarderModule      icon_boarder_module;
	
	@Override
	protected void createDisplay(){
		isDrawIcon = new JComplexCheckBox(null, "Draw Icon", true);
		// preview
		icon_preview = new JImagePreview(new Dimension(100, 100), ImageResizeMode.resizeMode[ImageResizeMode.defaultValue]);
		// choose icon
		JTextLabel  icon_file         = new JTextLabel("Icon", Alignment.CENTER, Alignment.TOP, 0, null);
		JTextButton icon_file_chooser = new JTextButton("Choose Icon...");
		icon_file_chooser.addActionListener((a)->{
			File file = JFileHandler.FC.showChooseImageDialog_file(icon_file_chooser);
			if(file == null)
				return;
			try{
				icon_image = FileHandler.getFileHander().loadImage(file);
			}catch(IOException e1){
				Utility.getUtility().handleException(icon_file_chooser, e1, "IOException", "Fail to load file\nFile formate not supported or file is damaged", "3.1 Unsupported Image/File Formate");
				return;
			}
			icon_file_chooser.setText(file.getAbsolutePath());
			icon_preview.setImageSoruce(icon_image);
		});
		Utility.getUtility().setPreferredSize(icon_file_chooser, new Dimension(100, 30));
		// icon resize mode
		JTextLabel icon_resize_mode = new JTextLabel("Resize Mode", Alignment.CENTER, Alignment.TOP, 0, null);
		icon_resize_mode_picker = new JCustomizeComboBox<>(ImageResizeMode.imageResizeMode, ImageResizeMode.defaultValue);
		icon_resize_mode_picker.addChangeActionListener(()->{
			icon_preview.setResizeMode(icon_resize_mode_picker.getContent(ImageResizeMode.resizeMode));
		});
		// icon fill
		JTextLabel icon_fill = new JTextLabel("Icon Fill", Alignment.CENTER, Alignment.TOP, 0, null);
		icon_fill_chooser = new JComplexColorChooser(Color.WHITE, true, true, "Background Fill");
		icon_fill_chooser.addChangeActionListener(()->{
			if(qrCodeModule.getQrCode() == null)
				return;
			setFill();
		});
		// icon boarder
		isDrawBoarder = new JComplexCheckBox(null, "Draw Boarder", true);
		JTextLabel icon_boarder = new JTextLabel("Icon Boarder", Alignment.CENTER, Alignment.TOP, 0, null);
		icon_boarder_module = new JComplexBoarderModule();
		isDrawBoarder.addEnableChild(icon_boarder, true);
		isDrawBoarder.addEnableChild(icon_boarder_module, true);
		// add enable
		isDrawIcon.addEnableChild(icon_preview, true);
		isDrawIcon.addEnableChild(icon_file, true);
		isDrawIcon.addEnableChild(icon_file_chooser, true);
		isDrawIcon.addEnableChild(icon_resize_mode, true);
		isDrawIcon.addEnableChild(icon_resize_mode_picker, true);
		isDrawIcon.addEnableChild(icon_fill, true);
		isDrawIcon.addEnableChild(icon_fill_chooser, true);
		isDrawIcon.addEnableChild(isDrawBoarder, true);
//		isDrawIcon.addEnableChild(icon_boarder, true);
//		isDrawIcon.addEnableChild(icon_boarder_module, true);
		// size
		Utility.getUtility().setPreferredSize(icon_file, LABLE_DI);
		Utility.getUtility().setPreferredSize(icon_resize_mode, LABLE_DI);
		Utility.getUtility().setPreferredSize(icon_fill, LABLE_DI);
		Utility.getUtility().setPreferredSize(icon_boarder, LABLE_DI);
		// layout
		Layouter.SpringLayouter layouter = new SpringLayouter(this.nestedPanel);
		layouter.put(Position.CONSTRAIN_X, isDrawIcon, 0, Position.CONSTRAIN_X, nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, isDrawIcon, 5, Position.CONSTRAIN_Y, nestedPanel);
		layouter.put(Position.CONSTRAIN_X_WIDTH, isDrawIcon, 0, Position.CONSTRAIN_X_WIDTH, nestedPanel);
		///////////////////////// div /////////////////////////
		JDividerLine div_start = new JDividerLine(DividerMode.DOT, new Dimension(100, 10));
		layouter.put(Position.CONSTRAIN_X, div_start, 0, Position.CONSTRAIN_X, nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, div_start, 5, Position.CONSTRAIN_Y_HEIGHT, isDrawIcon);
		layouter.put(Position.CONSTRAIN_X_WIDTH, div_start, 0, Position.CONSTRAIN_X_WIDTH, nestedPanel);
		// div //
		JPanel icon_preview_panel = new JPanel();
		{
			Layouter.GridBagLayouter innerLayouter = new GridBagLayouter(icon_preview_panel);
			innerLayouter.put(innerLayouter.instanceOf(icon_preview, 0, 0).setAnchor(Anchor.CENTER).setFill(Fill.NONE).setWeight(0, 0));
		}
		layouter.put(Position.CONSTRAIN_X, icon_preview_panel, 0, Position.CONSTRAIN_X, nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, icon_preview_panel, 5, Position.CONSTRAIN_Y_HEIGHT, div_start);
		layouter.put(Position.CONSTRAIN_X_WIDTH, icon_preview_panel, 0, Position.CONSTRAIN_X_WIDTH, nestedPanel);
		///////////////////////// div /////////////////////////
		JDividerLine div_one = new JDividerLine(DividerMode.DOT, new Dimension(100, 10));
		layouter.put(Position.CONSTRAIN_X, div_one, 0, Position.CONSTRAIN_X, nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, div_one, 5, Position.CONSTRAIN_Y_HEIGHT, icon_preview_panel);
		layouter.put(Position.CONSTRAIN_X_WIDTH, div_one, 0, Position.CONSTRAIN_X_WIDTH, nestedPanel);
		// div //
		layouter.put(Position.CONSTRAIN_X, icon_file, 0, Position.CONSTRAIN_X, nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, icon_file, 5, Position.CONSTRAIN_Y_HEIGHT, div_one);
		// div //
		layouter.put(Position.CONSTRAIN_X, icon_file_chooser, 0, Position.CONSTRAIN_X_WIDTH, icon_file);
		layouter.put(Position.CONSTRAIN_Y, icon_file_chooser, 0, Position.CONSTRAIN_Y, icon_file);
		layouter.put(Position.CONSTRAIN_X_WIDTH, icon_file_chooser, 0, Position.CONSTRAIN_X_WIDTH, nestedPanel);
		// div //
		layouter.put(Position.CONSTRAIN_Y_HEIGHT, icon_file, 0, Position.CONSTRAIN_Y_HEIGHT, icon_file_chooser);
		///////////////////////// div /////////////////////////
		JDividerLine div_two = new JDividerLine(DividerMode.DOT, new Dimension(100, 10));
		layouter.put(Position.CONSTRAIN_X, div_two, 0, Position.CONSTRAIN_X, nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, div_two, 5, Position.CONSTRAIN_Y_HEIGHT, icon_file);
		layouter.put(Position.CONSTRAIN_X_WIDTH, div_two, 0, Position.CONSTRAIN_X_WIDTH, nestedPanel);
		// div //
		layouter.put(Position.CONSTRAIN_X, icon_resize_mode, 0, Position.CONSTRAIN_X, nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, icon_resize_mode, 5, Position.CONSTRAIN_Y_HEIGHT, div_two);
		// div //
		layouter.put(Position.CONSTRAIN_X, icon_resize_mode_picker, 0, Position.CONSTRAIN_X_WIDTH, icon_resize_mode);
		layouter.put(Position.CONSTRAIN_Y, icon_resize_mode_picker, 0, Position.CONSTRAIN_Y, icon_resize_mode);
		layouter.put(Position.CONSTRAIN_X_WIDTH, icon_resize_mode_picker, 0, Position.CONSTRAIN_X_WIDTH, nestedPanel);
		// div //
		layouter.put(Position.CONSTRAIN_Y_HEIGHT, icon_resize_mode, 0, Position.CONSTRAIN_Y_HEIGHT, icon_resize_mode_picker);
		///////////////////////// div /////////////////////////
		JDividerLine div_three = new JDividerLine(DividerMode.DOT, new Dimension(100, 10));
		layouter.put(Position.CONSTRAIN_X, div_three, 0, Position.CONSTRAIN_X, nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, div_three, 5, Position.CONSTRAIN_Y_HEIGHT, icon_resize_mode);
		layouter.put(Position.CONSTRAIN_X_WIDTH, div_three, 0, Position.CONSTRAIN_X_WIDTH, nestedPanel);
		// div //
		layouter.put(Position.CONSTRAIN_X, icon_fill, 0, Position.CONSTRAIN_X, nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, icon_fill, 5, Position.CONSTRAIN_Y_HEIGHT, div_three);
		// div //
		layouter.put(Position.CONSTRAIN_X, icon_fill_chooser, 0, Position.CONSTRAIN_X_WIDTH, icon_fill);
		layouter.put(Position.CONSTRAIN_Y, icon_fill_chooser, 0, Position.CONSTRAIN_Y, icon_fill);
		layouter.put(Position.CONSTRAIN_X_WIDTH, icon_fill_chooser, 0, Position.CONSTRAIN_X_WIDTH, nestedPanel);
		// div //
		layouter.put(Position.CONSTRAIN_Y_HEIGHT, icon_fill, 0, Position.CONSTRAIN_Y_HEIGHT, icon_fill_chooser);
		///////////////////////// div /////////////////////////
		JDividerLine div_four = new JDividerLine(DividerMode.DOT, new Dimension(100, 10));
		layouter.put(Position.CONSTRAIN_X, div_four, 0, Position.CONSTRAIN_X, nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, div_four, 5, Position.CONSTRAIN_Y_HEIGHT, icon_fill);
		layouter.put(Position.CONSTRAIN_X_WIDTH, div_four, 0, Position.CONSTRAIN_X_WIDTH, nestedPanel);
		// div //
		layouter.put(Position.CONSTRAIN_X, isDrawBoarder, 0, Position.CONSTRAIN_X, nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, isDrawBoarder, 0, Position.CONSTRAIN_Y_HEIGHT, div_four);
		layouter.put(Position.CONSTRAIN_X_WIDTH, isDrawBoarder, 0, Position.CONSTRAIN_X_WIDTH, nestedPanel);
		// div //
		layouter.put(Position.CONSTRAIN_X, icon_boarder, 0, Position.CONSTRAIN_X, nestedPanel);
		layouter.put(Position.CONSTRAIN_Y, icon_boarder, 5, Position.CONSTRAIN_Y_HEIGHT, isDrawBoarder);
		// div //
		layouter.put(Position.CONSTRAIN_X, icon_boarder_module, 0, Position.CONSTRAIN_X_WIDTH, icon_boarder);
		layouter.put(Position.CONSTRAIN_Y, icon_boarder_module, 0, Position.CONSTRAIN_Y, icon_boarder);
		layouter.put(Position.CONSTRAIN_X_WIDTH, icon_boarder_module, 0, Position.CONSTRAIN_X_WIDTH, nestedPanel);
		// div //
		layouter.put(Position.CONSTRAIN_Y_HEIGHT, icon_boarder, 0, Position.CONSTRAIN_Y_HEIGHT, icon_boarder_module);
		// div //
		layouter.put(Position.CONSTRAIN_Y_HEIGHT, nestedPanel, 0, Position.CONSTRAIN_Y_HEIGHT, icon_boarder_module);
		Utility.getUtility().setPreferredSize(this.nestedPanel, new Dimension(100, 500));
	}
	
	private void setFill(){
		iconBackGroundFill = GraphicsHandler.getGraphicsHandler().createFillImage(icon_fill_chooser.getColoring(), maxSize);
	}
	
	public void onQRCodeChange(){
		maxSize = qrCodeModule.getMaximumSpaceForIcon();
		icon_boarder_module.setBorderThinknessMax((int) (Integer.valueOf(maxSize.width - 1).floatValue() / 2));
		setFill();
	}
	
	Dimension     maxSize;
	Rectangle     avaiableForIcon;
	BufferedImage iconBackGroundFill;
	
	@Override
	public Dimension getDimension(){
		if(!isDrawIcon.isSelected() || qrCodeModule.qrCode == null || icon_image == null)
			return null;
		if(isDrawBoarder.isSelected())
			avaiableForIcon = Utility.getUtility().minusBounds(new Rectangle(maxSize), icon_boarder_module.getBorderThinkness());
		else
			avaiableForIcon = new Rectangle(maxSize);
		return maxSize;
	}
	
	@Override
	public Point getVertex(Dimension frameDimension, Dimension objectDimension){
		if(!isDrawIcon.isSelected() || qrCodeModule.qrCode == null || icon_image == null)
			return null;
		return Alignment.getDesiredVertex(Alignment.CENTER, qrCodeModule.getVertex(), qrCodeModule.getDi(), null, objectDimension);
	}
	
	@Override
	public void render(Graphics2D g){
		if(!isDrawIcon.isSelected() || qrCodeModule.qrCode == null || icon_image == null)
			return;
		// draw BackGround Fill
		if(iconBackGroundFill != null)
			g.drawImage(iconBackGroundFill, 0, 0, null);
		// draw Boarder
		if(isDrawBoarder.isSelected())
			icon_boarder_module.render(g);
		// draw Icon
		g.drawImage(icon_resize_mode_picker.getContent(ImageResizeMode.resizeMode).getResizedImage(icon_image, avaiableForIcon.getSize()), avaiableForIcon.x, avaiableForIcon.y, null);
	}
}
