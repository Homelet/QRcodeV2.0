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
package homelet.Display.State.Encode.customize;

import homelet.Display.State.Encode.customize.Modules.CustomizeIconModule;
import homelet.Display.State.Encode.customize.Modules.CustomizeQRCodeModule;
import homelet.Swing.EnableListener;
import homelet.Swing.EnableListenerParent;
import homelet.Swing.JBasic.JScrollablePanel;
import homelet.Swing.RestoreDefault;
import homelet.Swing.RestoreDefaultParent;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.FlowLayouter;
import homelet.Utile.Layouter.FlowLayouter.FlowAlignment;
import homelet.Utile.Utility.Orientation;

import java.awt.*;
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
public class CustomizePanel extends JScrollablePanel implements RestoreDefaultParent, EnableListenerParent{
	
	CustomizeQRCodeModule qrCodeModule;
	CustomizeIconModule   iconModule;
	
	public CustomizePanel(){
		super(Orientation.VERTICAL);
		this.qrCodeModule = new CustomizeQRCodeModule();
		this.iconModule = new CustomizeIconModule(qrCodeModule);
		this.nestedPanel.setBackground(Color.GREEN);
//		Layouter.SpringLayouter layouter = new SpringLayouter(this.nestedPanel);
//		layouter.put(Position.CONSTRAIN_X, qrCodeModule, 0, Position.CONSTRAIN_X, nestedPanel);
//		layouter.put(Position.CONSTRAIN_Y, qrCodeModule, 0, Position.CONSTRAIN_Y, nestedPanel);
//		layouter.put(Position.CONSTRAIN_X_WIDTH, qrCodeModule, 0, Position.CONSTRAIN_X_WIDTH, nestedPanel);
//		// div
//		layouter.put(Position.CONSTRAIN_X, iconModule, 0, Position.CONSTRAIN_X, nestedPanel);
//		layouter.put(Position.CONSTRAIN_Y, iconModule, 0, Position.CONSTRAIN_Y_HEIGHT, qrCodeModule);
//		layouter.put(Position.CONSTRAIN_X_WIDTH, iconModule, 0, Position.CONSTRAIN_X_WIDTH, nestedPanel);
//		layouter.put(Position.CONSTRAIN_Y_HEIGHT, iconModule, 0, Position.CONSTRAIN_Y_HEIGHT, nestedPanel);
		Layouter.FlowLayouter layouter = new FlowLayouter(this.nestedPanel, FlowAlignment.TOP, 0, 0, true, false);
		layouter.add(qrCodeModule);
		layouter.add(iconModule);
	}
	
	public CustomizeQRCodeModule getQrCodeModule(){
		return qrCodeModule;
	}
	
	public CustomizeIconModule getIconModule(){
		return iconModule;
	}
	
	// Restore default & enable parents
	ArrayList<EnableListener> ENlisteners = new ArrayList<>();
	
	@Override
	public ArrayList<EnableListener> getEnableChildList(){
		return ENlisteners;
	}
	
	ArrayList<RestoreDefault> RElisteners = new ArrayList<>();
	
	@Override
	public ArrayList<RestoreDefault> getRestoreDefaultChildList(){
		return RElisteners;
	}
}
