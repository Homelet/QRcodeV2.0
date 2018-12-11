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
package homelet.Display.State.Encode.customize;

import homelet.Swing.JBasic.JTextButton;
import homelet.Swing.JDrawer.RenderTarget;
import homelet.Utile.Layouter;
import homelet.Utile.Layouter.GridBagLayouter;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Anchor;
import homelet.Utile.Layouter.GridBagLayouter.GridConstrain.Fill;
import homelet.Utile.Utility;

import javax.swing.*;
import java.awt.*;

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
public abstract class CustomizeModule extends JPanel implements RenderTarget{
	
	protected static int       HEIGHT      = 200;
	protected static int       LABLE_WIDTH = 120;
	public static    Dimension LABLE_DI    = new Dimension(LABLE_WIDTH, HEIGHT);
	protected        JPanel    nestedPanel;
	
	public CustomizeModule(String moduleName){
		CustomizeModuleHeader header = new CustomizeModuleHeader(this, moduleName);
		this.nestedPanel = new JPanel();
		Layouter.GridBagLayouter gridBagLayouter = new GridBagLayouter(this);
		gridBagLayouter.put(gridBagLayouter.instanceOf(header, 0, 0).setAnchor(Anchor.TOP).setFill(Fill.BOTH).setWeight(100, 0));
		gridBagLayouter.put(gridBagLayouter.instanceOf(nestedPanel, 0, 1).setAnchor(Anchor.TOP).setFill(Fill.BOTH).setWeight(100, 100));
//		Layouter.SpringLayouter layouter = new SpringLayouter(this);
//		layouter.put(Position.CONSTRAIN_X, header, 0, Position.CONSTRAIN_X, this);
//		layouter.put(Position.CONSTRAIN_Y, header, 0, Position.CONSTRAIN_Y, this);
//		layouter.put(Position.CONSTRAIN_X_WIDTH, header, 0, Position.CONSTRAIN_X_WIDTH, this);
//		// div
//		layouter.put(Position.CONSTRAIN_X, nestedPanel, 0, Position.CONSTRAIN_X, this);
//		layouter.put(Position.CONSTRAIN_Y, nestedPanel, 0, Position.CONSTRAIN_Y_HEIGHT, header);
//		layouter.put(Position.CONSTRAIN_X_WIDTH, nestedPanel, 0, Position.CONSTRAIN_X_WIDTH, this);
//		layouter.put(Position.CONSTRAIN_Y_HEIGHT, nestedPanel, 0, Position.CONSTRAIN_Y_HEIGHT, this);
		toggleVisable();
	}
	
	void toggleVisable(){
		nestedPanel.setVisible(!nestedPanel.isVisible());
	}
	
	protected abstract void createDisplay();
}

class CustomizeModuleHeader extends JTextButton{
	
	public CustomizeModuleHeader(CustomizeModule parent, String name){
		super(name, (actionEvent)->{
			parent.toggleVisable();
		});
		Utility.getUtility().setPreferredSize(this, new Dimension(100, 30));
	}
}
