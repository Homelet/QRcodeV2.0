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
package homelet.Swing.JBasic;

import homelet.Swing.EnableListener;
import homelet.Swing.RestoreDefault;
import homelet.Utile.Utility.Orientation;

import javax.swing.*;

/**
 * The type Jscrollablepanel.
 */
public class JScrollablePanel extends JScrollPane implements EnableListener, RestoreDefault{
	
	protected JPanel     nestedPanel;
	private   JScrollBar scrollBar;
	
	public JScrollablePanel(Orientation orientation){
		this.nestedPanel = new JPanel();
		this.setViewportView(nestedPanel);
		if(orientation == null)
			orientation = Orientation.VERTICAL;
		switch(orientation){
			case VERTICAL:
				this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				this.scrollBar = this.getVerticalScrollBar();
				break;
			case HORIZONTAL:
				this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
				this.scrollBar = this.getHorizontalScrollBar();
				break;
			case EQUILATERAL:
				this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				break;
		}
	}
	
	public void setNestedPanel(JPanel panel){
		this.nestedPanel = panel;
		this.setViewportView(nestedPanel);
	}
	
	public JPanel getNestedPanel(){
		return nestedPanel;
	}
	
	public void scrollToMax(){
		if(scrollBar != null)
			scrollBar.setValue(scrollBar.getMaximum());
	}
	
	public void scrollToMin(){
		if(scrollBar != null)
			scrollBar.setValue(scrollBar.getMinimum());
	}
	
	@Override
	public void onEnableChange(boolean newEnable){
		this.setEnabled(newEnable);
		this.nestedPanel.setEnabled(newEnable);
	}
	
	@Override
	public void restoreDefault(){}
}
