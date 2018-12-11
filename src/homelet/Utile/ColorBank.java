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
package homelet.Utile;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * The type Color bank.
 */
/*
 * <pre>
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 * </pre>
 *
 * @author HomeletWei
 * @date  2018-07-04
 */
public class ColorBank{
	
	private static Color[] REGULAR_COLOR_BANK = new Color[]{
			new Color(0x000000),//black
			new Color(0xFFFFFF),//white
			new Color(0xFF0000),//red
			new Color(0x00FF00),//green
			new Color(0x0000FF),//blue
			new Color(0xFFFF00),//yellow
			new Color(0xFF00FF),//pink
			new Color(0x00FFFF),//cayn
			//<DIV>
			new Color(0x7f0000),//dark red
			new Color(0x007F00),//dark green
			new Color(0x00007F),//dark blue
			new Color(0x7F7F00),//dark yellow
			new Color(0x7F007F),//dark purple
			new Color(0x007F7F),//dark cayn
			new Color(0x7F7F7F),//gray
			//<DIV>
			new Color(0xFF7F00),//orange
			new Color(0xFF007F),//light red
			new Color(0x00FF7F),//light cayn
			new Color(0x7FFF00),//light green
			new Color(0x7F00FF),//purple
			new Color(0x007FFF),//light blue
	};
	/**
	 * The Pull list.
	 */
	/*
	 * first at inital, all elements are stored in pull list, as pull list
	 * pulls element, elements are moved to pendingList, as soon as the elements used up
	 * all pending list element are moved into pull lists
	 * to ensure that no color out of this bank are the same during the cycles
	 */
	LinkedList<Color>   pullList;
	/**
	 * The Pending list.
	 */
	LinkedList<Color>   pendingList;
	/**
	 * The Color map.
	 */
	Map<Integer, Color> colorMap;
	
	/**
	 * Instantiates a new Color bank.
	 *
	 * @param colors the colors
	 */
	public ColorBank(Color[] colors){
		pullList = new LinkedList<>();
		pendingList = new LinkedList<>();
		colorMap = new HashMap<>();
		initalize(colors);
	}
	
	/**
	 * Instantiates a new Color bank.
	 */
	public ColorBank(){
		this(REGULAR_COLOR_BANK);
	}
	
	private void initalize(Color[] colors){
		for(Color c : colors)
			pullList.add(c);
	}
	
	/**
	 * Poll a color from the list.
	 *
	 * @return the color
	 */
	public Color pollColor(){
		Color color = pullList.poll();
		if(color == null){
			color = transferColors();
		}
		return pushColor(color);
	}
	
	/**
	 * Poll color from the list and map the color to the index<br>
	 * so the same index will result in the same color
	 *
	 * @param index the index
	 * @return the color
	 */
	public Color pollColor(int index){
		Color color = colorMap.get(index);
		if(color == null){
			color = pollColor();
			colorMap.put(index, color);
		}
		return color;
	}
	
	// push a color to pending list
	private Color pushColor(Color color){
		pendingList.push(color);
		return color;
	}
	
	// move all pendingList colors and transfer to pullList,
	// this is invoked if the pull list is empty
	private Color transferColors(){
		pullList.addAll(pendingList);
		pendingList.clear();
		return pullList.poll();
	}
	
	public Map<Integer, Color> getColorMap(){
		return colorMap;
	}
}
