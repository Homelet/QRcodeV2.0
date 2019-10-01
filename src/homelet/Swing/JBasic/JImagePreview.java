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
package homelet.Swing.JBasic;

import homelet.Swing.ChangeActionListener;
import homelet.Swing.EnableListener;
import homelet.Swing.JDrawer.Render;
import homelet.Swing.RestoreDefault;
import homelet.Utile.Constants.ImageResizeMode;
import homelet.Utile.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class JImagePreview extends JComponent implements EnableListener, RestoreDefault, Render{
	
	final Dimension maxDimention;
	BufferedImage     imageSoruce;
	BufferedImage     imageAfter;
	ImageResizeMode   resizeMode;
	ArrayList<Render> preRenderList;
	ArrayList<Render> postRenderList;
	// top , bottom, right, left
	int[]             insets = new int[4];
	
	public JImagePreview(Dimension maxDimention, ImageResizeMode resizeMode){
		this.maxDimention = maxDimention;
		Utility.getUtility().setPreferredSize(this, maxDimention);
		setResizeMode(resizeMode);
		postRenderList = new ArrayList<>();
		preRenderList = new ArrayList<>();
	}
	
	public void setImageSoruce(BufferedImage imageSoruce){
		this.imageSoruce = imageSoruce;
		applyImage();
	}
	
	public BufferedImage getImageSource(){
		return imageSoruce;
	}
	
	public ImageResizeMode getResizeMode(){
		return resizeMode;
	}
	
	public void addPreRender(Render render){
		preRenderList.add(render);
	}
	
	public void addPostRender(Render render){
		postRenderList.add(render);
	}
	
	public void setInsets(int... insets){
		if(insets.length == 1){
			this.insets[0] = this.insets[1] = this.insets[2] = this.insets[3] = insets[0];
		}else if(insets.length == 4){
			this.insets[0] = insets[0];
			this.insets[1] = insets[1];
			this.insets[2] = insets[2];
			this.insets[3] = insets[3];
		}
	}
	
	@Override
	public void render(Graphics2D g){
		g.drawImage(resizeMode.getResizedImage(imageSoruce, Utility.getUtility().minusInsets(new Rectangle(maxDimention), insets[0], insets[1], insets[2], insets[3]).getSize()), insets[0], insets[1], null);
	}
	
	public void setResizeMode(ImageResizeMode resizeMode){
		this.resizeMode = resizeMode;
		applyImage();
	}
	
	private void applyImage(){
		if(imageSoruce == null)
			return;
		imageAfter = resizeMode.getResizedImage(imageSoruce, maxDimention);
		onChangeActionInvoke();
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(imageAfter, 0, 0, null);
	}
	
	@Override
	public void onEnableChange(boolean newEnable){
		this.setEnabled(newEnable);
	}
	
	@Override
	public void restoreDefault(){
	}
	
	private ArrayList<ChangeActionListener> changeActionListeners = new ArrayList<>();
	
	public void addChangeActionListener(ChangeActionListener changeActionListener){
		this.changeActionListeners.add(changeActionListener);
	}
	
	private void onChangeActionInvoke(){
		for(ChangeActionListener c : changeActionListeners){
			if(c != null)
				c.onChange();
		}
	}
}
