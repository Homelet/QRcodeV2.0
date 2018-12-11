/**
 * <pre>
 * ****************************************************
 * Name: TODO
 * Date: TODO
 * Description: TODO
 * *****************************************************
 * </pre>
 *
 * @author HomeletWei
 * @date Jun 15, 2018
 */
package homelet.Utile;

import homelet.Utile.Constants.GradientColorMapping;

import java.awt.*;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Jun 15, 2018
 */
public class Coloring{
	
	private Color[]              colors = new Color[2];
	private GradientColorMapping colorMapping;
	private ColorMode            mode   = ColorMode.NULL;
	
	/**
	 * constructor for Coloring
	 *
	 * @param colors
	 * @author HomeletWei
	 */
	public Coloring(Color... colors){
		setColors(colors);
	}
	
	private void catatorize(Color... colors){
		if(colors.length == 0){
			setMode(ColorMode.NULL);
		}
		else if(colors.length == 1){
			setMode(ColorMode.PAIN_COLOR);
		}
		else{
			setMode(ColorMode.COLOR_MAP);
		}
	}
	
	/**
	 * constructor for Coloring
	 *
	 * @param mode
	 * @author HomeletWei
	 */
	public Coloring(ColorMode mode){
		setMode(mode);
	}
	
	/**
	 * constructor for Coloring
	 *
	 * @author HomeletWei
	 */
	public Coloring(){}
	
	/**
	 * @param index
	 * @return the color
	 * @author HomeletWei
	 */
	public Color getColor(int index){
		return getColors()[index];
	}
	
	/** @return colors */
	public Color[] getColors(){
		return colors;
	}
	
	/** @param colors : colors */
	public void setColors(Color... colors){
		catatorize(colors);
		for(int index = 0; index < colors.length; index++)
			this.colors[index] = colors[index];
	}
	
	/**
	 * @return the color
	 * @author HomeletWei
	 */
	public Color getColor(){
		return getColors()[0];
	}
	
	/** @param color : color */
	public void setColor(Color color){
		setMode(ColorMode.PAIN_COLOR);
		this.colors[0] = color;
	}
	
	/**
	 * @param color : color
	 * @param index
	 */
	public void setColor(Color color, int index){
		setMode(ColorMode.COLOR_MAP);
		this.colors[index] = color;
	}
	
	/**
	 * clear the colors
	 *
	 * @author HomeletWei
	 */
	public void clearColors(){
		setColors();
	}
	
	/**
	 * @return is the color hasn't been set yet
	 * @author HomeletWei
	 */
	public boolean isNull(){
		return getMode() == ColorMode.NULL;
	}
	
	/** @return mode */
	public ColorMode getMode(){
		return mode;
	}
	
	/** @param mode : mode */
	public void setMode(ColorMode mode){
		this.mode = mode;
	}
	
	public GradientColorMapping getGradientColorMappingMode(){
		return colorMapping;
	}
	
	public void setGradientColorMappingMode(GradientColorMapping colorMapping){
		this.colorMapping = colorMapping;
	}
	
	/**
	 * the indicators for color map
	 *
	 * @author HomeletWei
	 * @date Jun 15, 2018
	 */
	public enum ColorMode{
		/** @Fields <b>COLOR_MAP</b> indicate current coloring object is appered as a color Map */
		COLOR_MAP,
		/** @Fields <b>PAIN_COLOR</b> indecate current coloring object is appered as pain color */
		PAIN_COLOR,
		/** @Fields <b>PAIN_COLOR</b> indecate current coloring object hasn't been initialize yet */
		NULL;
	}
}
