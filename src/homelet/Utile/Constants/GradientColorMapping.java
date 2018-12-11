/**
 * @author HomeletWei
 * @date Apr 25, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Utile.Constants;

import homelet.Utile.GraphicsHandler;

import java.awt.*;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 25, 2018
 */
public enum GradientColorMapping{
	TOP_TO_BOTTOM("Top To Bottom"),
	LEFT_TO_RIGHT("Left To Right"),
	TOP_LEFT_TO_BOTTOM_RIGHT("Top Left To Bottom Right"),
	BOTTOM_LEFT_TO_TOP_RIGHT("Bottom Left To Top Right"),
	INNER_TO_OUTER("Inner To Outer");
	public static int                    defaultValue     = 0;
	public static String[]               colorMappingMode = new String[]{ TOP_TO_BOTTOM.getValue(), LEFT_TO_RIGHT.getValue(), TOP_LEFT_TO_BOTTOM_RIGHT.getValue(), BOTTOM_LEFT_TO_TOP_RIGHT.getValue(), INNER_TO_OUTER.getValue() };
	public static GradientColorMapping[] colorMapping     = new GradientColorMapping[]{ TOP_TO_BOTTOM, LEFT_TO_RIGHT, TOP_LEFT_TO_BOTTOM_RIGHT, BOTTOM_LEFT_TO_TOP_RIGHT, INNER_TO_OUTER };
	private       String                 value;
	
	/**
	 * constructor for GradientColorMapping
	 * TODO
	 *
	 * @author HomeletWei
	 */
	private GradientColorMapping(String value){
		this.value = value;
	}
	
	/**
	 * create a gradient color map
	 *
	 * @param from the from Color
	 * @param to   the to Color
	 * @param DI   Dimension
	 * @return the desired color mapping
	 * @author HomeletWei
	 */
	public int[][][] getGradiantColorMap(Color from, Color to, Dimension DI){
		return GraphicsHandler.getGraphicsHandler().getGradientColorMapping(from, to, this, DI);
	}
	
	/** @return value */
	public String getValue(){
		return value;
	}
}
