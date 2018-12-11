package homelet.Display.State.Decode;
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

import homelet.Display.Display;
import homelet.Display.State.States;
import homelet.Swing.JBasic.JFileHandler;
import homelet.Swing.JBasic.JImagePreview;
import homelet.Swing.JBasic.JTextButton;
import homelet.Swing.JInput.JInputArea;
import homelet.Utile.Constants.ImageResizeMode;
import homelet.Utile.Constants.Sizing;
import homelet.Utile.FileHandler;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;
import homelet.Utile.Utility;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Mar 26, 2018
 */
public class DecodeState extends States{
	
	/** @Fields <b>serialVersionUID</b> TODO */
	private static final long serialVersionUID = 1L;
	Display       display;
	JImagePreview imagePreviews;
	JInputArea    result;
	
	/**
	 * constructor for DecodeState
	 * TODO
	 *
	 * @param name
	 * @author HomeletWei
	 */
	public DecodeState(String name){
		super(name);
	}
	
	final String defaultText = "Choose File...";
	
	@Override
	public void createDisplay(){
		this.addBackAction(Display.menuState);
		this.addTargetStateAction(Display.decodeSettingState, "Decode Setting");
		this.imagePreviews = new JImagePreview(new Dimension(500, 500), ImageResizeMode.SCALE_TO_FIT);
		JTextButton chooseFile = new JTextButton(defaultText);
		chooseFile.addActionListener((ActionEvent a)->{
			File file = JFileHandler.FC.showChooseImageDialog_file(DecodeState.this);
			if(file == null)
				return;
			try{
				imagePreviews.setImageSoruce(FileHandler.getFileHander().loadImage(file));
			}catch(IOException e1){
				Utility.getUtility().handleException(DecodeState.this, e1, "IOException", "Fail to load file\nFile formate not supported or file is damaged", "3.1 Unsupported Image/File Formate");
			}
			chooseFile.setText(file.getAbsolutePath());
		});
		Utility.getUtility().setPreferredSize(chooseFile, new Dimension(100, 20));
		this.result = new JInputArea(null, Sizing.Display.displayPanelDI, false);
		Utility.getUtility().setPreferredSize(result, new Dimension(510, 100));
		Layouter.GridBagLayouter layout = new GridBagLayouter(displayPanel);
		layout.put(layout.instanceOf(imagePreviews, 0, 0).setAnchor(Anchor.CENTER).setWeight(0, 0));
		layout.put(layout.instanceOf(chooseFile, 0, 1).setFill(Fill.BOTH).setAnchor(Anchor.CENTER).setWeight(1, 1));
		layout.put(layout.instanceOf(result, 0, 2).setFill(Fill.BOTH).setAnchor(Anchor.CENTER).setWeight(100, 100));
		JTextButton decode = new JTextButton("Decode", (ActionEvent a)->{
		});
		addControlBarButton(decode);
	}
}
