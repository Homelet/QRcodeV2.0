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

import java.awt.*;

/**
 * Design for giving accurate position
 * <br>
 * <ul>
 * <li>top left</li>
 * <ul>
 * <li>the context will be display at the top left corner of the canvas</li>
 * </ul>
 * <li>top center</li>
 * <ul>
 * <li>the context will be display at the top center of the canvas</li>
 * </ul>
 * <li>top right</li>
 * <ul>
 * <li>the context will be display at the top right corner of the canvas</li>
 * </ul>
 * <li>center left</li>
 * <ul>
 * <li>the context will be display at the top center left of the canvas</li>
 * </ul>
 * <li>center</li>
 * <ul>
 * <li>the context will be display at the center of the canvas</li>
 * </ul>
 * <li>center right</li>
 * <ul>
 * <li>the context will be display at the center right of the canvas</li>
 * </ul>
 * <li>bottom left</li>
 * <ul>
 * <li>the context will be display at the bottom left of the canvas</li>
 * </ul>
 * <li>bottom center</li>
 * <ul>
 * <li>the context will be display at the bottom center of the canvas</li>
 * </ul>
 * <li>bottom right</li>
 * <ul>
 * <li>the context will be display at the bottom right of the canvas</li>
 * </ul>
 * <li>keep x on left</li>
 * <ul>
 * <li>the context will be display at the given y position with x = 0 of the canvas</li>
 * </ul>
 * <li>keep x on center</li>
 * <ul>
 * <li>the context will be display at the given y position with x = half the of (width - imageWidth) of the canvas</li>
 * </ul>
 * <li>keep x on right</li>
 * <ul>
 * <li>the context will be display at the given y position with x = width - imageWidth of the canvas</li>
 * </ul>
 * <li>keep y on top</li>
 * <ul>
 * <li>the context will be display at the given x position with y = 0 of the canvas</li>
 * </ul>
 * <li>keep y on center</li>
 * <ul>
 * <li>the context will be display at the given x position with y = half the of (width - imageWidth) of the canvas</li>
 * </ul>
 * <li>keep y on bottom</li>
 * <ul>
 * <li>the context will be display at the given x position with y = 0 of the canvas</li>
 * </ul>
 * <li>customize</li>
 * <ul>
 * <li>the context will be display at the customize position of the canvas</li>
 * </ul>
 * </ul>
 *
 * @author HomeletWei
 * @date Apr 25, 2018
 */
public enum Alignment{
	//////////////////////////////////VV Full Position Alignment VV/////////////////////////////////////////
	/** @Fields <b>TOP_LEFT</b> the context will be display at the top left corner of the canvas */
	TOP_LEFT,
	/** @see Alignment#TOP_LEFT */
	NORTH_WEST(TOP_LEFT),
	/** @Fields <b>TOP</b> the context will be display at the top center of the canvas */
	TOP,
	/** @see Alignment#TOP */
	NORTH(TOP),
	/** @Fields <b>TOP_RIGHT</b> the context will be display at the top right corner of the canvas */
	TOP_RIGHT,
	/** @see Alignment#TOP_RIGHT */
	NORTH_EAST(TOP_RIGHT),
	/** @Fields <b>LEFT</b> the context will be display at the top center left of the canvas */
	LEFT,
	/** @see Alignment#LEFT */
	WEST(LEFT),
	/** @Fields <b>CENTER</b> the context will be display at the center of the canvas */
	CENTER,
	/** @see Alignment#CENTER */
	ORIGIN(CENTER),
	/** @Fields <b>RIGHT</b> the context will be display at the center right of the canvas */
	RIGHT,
	/** @see Alignment#RIGHT */
	EAST(RIGHT),
	/** @Fields <b>BOTTOM_LEFT</b> the context will be display at the bottom left of the canvas */
	BOTTOM_LEFT,
	/** @see Alignment#BOTTOM_LEFT */
	SOUTH_WEST(BOTTOM_LEFT),
	/** @Fields <b>BOTTOM</b> the context will be display at the bottom center of the canvas */
	BOTTOM,
	/** @see Alignment#BOTTOM */
	SOUTH(BOTTOM),
	/** @Fields <b>BOTTOM_RIGHT</b> the context will be display at the bottom center of the canvas */
	BOTTOM_RIGHT,
	/** @see Alignment#BOTTOM_RIGHT */
	SOUTH_EAST(BOTTOM_RIGHT),
	//////////////////////////////////VV Single Line Alignment VV/////////////////////////////////////////
	/** @Fields <b>KEEP_X_ON_LEFT</b> the context will be display at the given y position with x = 0 of the canvas */
	KEEP_X_ON_LEFT,
	/** @see Alignment#KEEP_X_ON_LEFT */
	KEEP_X_ON_WEST(KEEP_X_ON_LEFT),
	/** @Fields <b>KEEP_X_ON_CENTER</b> the context will be display at the given y position with x = half the of (width - imageWidth) of the canvas */
	KEEP_X_ON_CENTER,
	/** @see Alignment#KEEP_X_ON_CENTER */
	KEEP_X_ON_ORIGIN(KEEP_X_ON_CENTER),
	/** @Fields <b>KEEP_X_ON_RIGHT</b> the context will be display at the given y position with x = width - imageWidth of the canvas */
	KEEP_X_ON_RIGHT,
	/** @see Alignment#KEEP_X_ON_RIGHT */
	KEEP_X_ON_EAST(KEEP_X_ON_RIGHT),
	/** @Fields <b>KEEP_Y_ON_TOP</b> the context will be display at the given x position with y = 0 of the canvas */
	KEEP_Y_ON_TOP,
	/** @see Alignment#KEEP_Y_ON_TOP */
	KEEP_Y_ON_NORTH(KEEP_Y_ON_TOP),
	/** @Fields <b>KEEP_Y_ON_CENTER</b> the context will be display at the given x position with y = half the of (width - imageWidth) of the canvas */
	KEEP_Y_ON_CENTER,
	/** @see Alignment#KEEP_Y_ON_CENTER */
	KEEP_Y_ON_ORIGIN(KEEP_Y_ON_CENTER),
	/** @Fields <b>KEEP_Y_ON_BOTTOM</b> the context will be display at the given x position with y = 0 of the canvas */
	KEEP_Y_ON_BOTTOM,
	/** @see Alignment#KEEP_Y_ON_BOTTOM */
	KEEP_Y_ON_SOUTH(KEEP_Y_ON_BOTTOM),
	/////////////////////////////////////VV Free Alignment VV///////////////////////////////////////////
	/** @Fields <b>CUSTOMIZE</b> the context will be display at the customize position of the canvas */
	CUSTOMIZE,
//	/** @see Alignment#CUSTOMIZE */
//	KEEP_X(CUSTOMIZE),
//	/** @see Alignment#CUSTOMIZE */
//	KEEP_Y(CUSTOMIZE)
	;
	public static final String[]    alignment        = { "Top-Left Corner(North West)", "Top-Center(North)", "Top-Right Corner(North East)", "Center-Left(West)", "Center(Origin)", "Center-Right(East)", "Bottom-Left Corner(South West)", "Bottom-Center(South)", "Bottom-Right Corner(South East)", "Keep X on Left(West)", "Keep X on Center(Origin)", "Keep X on Right(East)", "Keep Y on Top(North)", "Keep Y on Center(Origin)", "Keep Y on Bottom(East)", "Customize" };
	public static final String[]    textAlignment    = { "Align Left", "Center Text", "Align Right" };
	public static final Alignment[] alignments       = new Alignment[]{ TOP_LEFT, TOP, TOP_RIGHT, LEFT, CENTER, RIGHT, BOTTOM_LEFT, BOTTOM, BOTTOM_RIGHT, KEEP_X_ON_LEFT, KEEP_X_ON_CENTER, KEEP_X_ON_RIGHT, KEEP_Y_ON_TOP, KEEP_Y_ON_CENTER, KEEP_Y_ON_BOTTOM, CUSTOMIZE };
	public static final Alignment[] textAlignments   = new Alignment[]{ TOP_LEFT, TOP, TOP_RIGHT };
	public static       int         defaultValue     = 4;
	public static       int         defaultTextValue = 1;
	Alignment equivelent;
	
	private Alignment(){
		this.equivelent = this;
	}
	
	private Alignment(Alignment equivelent){
		this.equivelent = equivelent;
	}
	
	public static Alignment getTextAlignment(int selectedIndex){
		return textAlignments[selectedIndex];
	}
	
	/**
	 * Design for giving accurate position
	 *
	 * @param alignment
	 * @param container
	 * @param object
	 * @return a point that meet the desire
	 * @author HomeletWei
	 * @see #getDesiredVertex(Alignment, Point, Dimension, Point, Dimension)
	 */
	public static Point getDesiredVertex(Alignment alignment, Rectangle container, Rectangle object){
		return getDesiredVertex(alignment, container.getLocation(), container.getSize(), object.getLocation(), object.getSize());
	}
	
	/**
	 * Design for giving accurate position
	 *
	 * @param alignment       the alignment mode
	 * @param containerVertex the container vertex
	 * @param containerDI     the container dimension
	 * @param objectVertex    the object vertex could be null if not using single line alignment
	 * @param objectDI        the object dimension
	 * @return a point that meet the desire
	 * @throws NullPointerException
	 * @author HomeletWei
	 * @see Alignment
	 */
	public static Point getDesiredVertex(Alignment alignment, Point containerVertex, Dimension containerDI, Point objectVertex, Dimension objectDI){
		switch(alignment){
			case NORTH_WEST:
			case TOP_LEFT:
				return new Point(containerVertex.x + 0, containerVertex.y + 0);
			case NORTH:
			case TOP:
				return new Point(containerVertex.x + (containerDI.width - objectDI.width) / 2, containerVertex.y + 0);
			case NORTH_EAST:
			case TOP_RIGHT:
				return new Point(containerVertex.x + (containerDI.width - objectDI.width), containerVertex.y + 0);
			case WEST:
			case LEFT:
				return new Point(containerVertex.x + 0, containerVertex.y + (containerDI.height - objectDI.height) / 2);
			case ORIGIN:
			case CENTER:
				return new Point(containerVertex.x + (containerDI.width - objectDI.width) / 2, containerVertex.y + (containerDI.height - objectDI.height) / 2);
			case EAST:
			case RIGHT:
				return new Point(containerVertex.x + (containerDI.width - objectDI.width), containerVertex.y + (containerDI.height - objectDI.height) / 2);
			case SOUTH_WEST:
			case BOTTOM_LEFT:
				return new Point(containerVertex.x + 0, containerVertex.y + (containerDI.height - objectDI.height));
			case SOUTH:
			case BOTTOM:
				return new Point(containerVertex.x + (containerDI.width - objectDI.width) / 2, containerVertex.y + (containerDI.height - objectDI.height));
			case SOUTH_EAST:
			case BOTTOM_RIGHT:
				return new Point(containerVertex.x + (containerDI.width - objectDI.width), containerVertex.y + (containerDI.height - objectDI.height));
			case KEEP_X_ON_WEST:
			case KEEP_X_ON_LEFT:
				return new Point(containerVertex.x + 0, containerVertex.y + objectVertex.y);
			case KEEP_X_ON_ORIGIN:
			case KEEP_X_ON_CENTER:
				return new Point(containerVertex.x + (containerDI.width - objectDI.width) / 2, containerVertex.y + objectVertex.y);
			case KEEP_X_ON_EAST:
			case KEEP_X_ON_RIGHT:
				return new Point(containerVertex.x + containerDI.width - objectDI.width, containerVertex.y + objectVertex.y);
			case KEEP_Y_ON_NORTH:
			case KEEP_Y_ON_TOP:
				return new Point(containerVertex.x + objectVertex.x, containerVertex.y + 0);
			case KEEP_Y_ON_ORIGIN:
			case KEEP_Y_ON_CENTER:
				return new Point(containerVertex.x + objectVertex.x, containerVertex.y + (containerDI.height - objectDI.height) / 2);
			case KEEP_Y_ON_SOUTH:
			case KEEP_Y_ON_BOTTOM:
				return new Point(containerVertex.x + objectVertex.x, containerVertex.y + containerDI.height - objectDI.height);
			case CUSTOMIZE:
			default:
				return new Point(containerVertex.x + objectVertex.x, containerVertex.y + objectVertex.y);
		}
	}
	
	/**
	 * Design for giving accurate position
	 *
	 * @param indexSelected
	 * @param container
	 * @param object
	 * @return a point that meet the desire
	 * @author HomeletWei
	 * @see #getDesiredVertex(Alignment, Point, Dimension, Point, Dimension)
	 */
	public static Point getDesiredVertex(int indexSelected, Rectangle container, Rectangle object){
		return getDesiredVertex(getAlignment(indexSelected), container.getLocation(), container.getSize(), object.getLocation(), object.getSize());
	}
	
	public static Alignment getAlignment(int selectedIndex){
		return alignments[selectedIndex];
	}
	
	/**
	 * Design for giving accurate position
	 *
	 * @param alignment
	 * @param container
	 * @param objectDI
	 * @return a point that meet the desire
	 * @author HomeletWei
	 * @see #getDesiredVertex(Alignment, Point, Dimension, Point, Dimension)
	 */
	public static Point getDesiredVertex(Alignment alignment, Rectangle container, Dimension objectDI){
		return getDesiredVertex(alignment, container.getLocation(), container.getSize(), null, objectDI);
	}
	
	/**
	 * Design for giving accurate position
	 *
	 * @param indexSelected
	 * @param container
	 * @param objectDI
	 * @return a point that meet the desire
	 * @author HomeletWei
	 * @see #getDesiredVertex(Alignment, Point, Dimension, Point, Dimension)
	 */
	public static Point getDesiredVertex(int indexSelected, Rectangle container, Dimension objectDI){
		return getDesiredVertex(getAlignment(indexSelected), container.getLocation(), container.getSize(), null, objectDI);
	}
	
	/**
	 * Design for giving accurate position
	 *
	 * @param alignment
	 * @param container
	 * @param objectVertex
	 * @param objectDI
	 * @return a point that meet the desire
	 * @author HomeletWei
	 * @see #getDesiredVertex(Alignment, Point, Dimension, Point, Dimension)
	 */
	public static Point getDesiredVertex(Alignment alignment, Rectangle container, Point objectVertex, Dimension objectDI){
		return getDesiredVertex(alignment, container.getLocation(), container.getSize(), objectVertex, objectDI);
	}
	
	/**
	 * Design for giving accurate position
	 *
	 * @param indexSelected
	 * @param container
	 * @param objectVertex
	 * @param objectDI
	 * @return a point that meet the desire
	 * @author HomeletWei
	 * @see #getDesiredVertex(Alignment, Point, Dimension, Point, Dimension)
	 */
	public static Point getDesiredVertex(int indexSelected, Rectangle container, Point objectVertex, Dimension objectDI){
		return getDesiredVertex(getAlignment(indexSelected), container.getLocation(), container.getSize(), objectVertex, objectDI);
	}
	
	/**
	 * Design for giving accurate position
	 *
	 * @param indexSelected
	 * @param containerVertex
	 * @param containerDI
	 * @param objectDI
	 * @return a point that meet the desire
	 * @author HomeletWei
	 * @see #getDesiredVertex(Alignment, Point, Dimension, Point, Dimension)
	 */
	public static Point getDesiredVertex(int indexSelected, Point containerVertex, Dimension containerDI, Dimension objectDI){
		return getDesiredVertex(getAlignment(indexSelected), containerVertex, containerDI, null, objectDI);
	}
	
	/**
	 * Design for giving accurate position
	 *
	 * @param indexSelected
	 * @param containerVertex
	 * @param containerDI
	 * @param objectVertex
	 * @param objectDI
	 * @return a point that meet the desire
	 * @author HomeletWei
	 * @see #getDesiredVertex(Alignment, Point, Dimension, Point, Dimension)
	 */
	public static Point getDesiredVertex(int indexSelected, Point containerVertex, Dimension containerDI, Point objectVertex, Dimension objectDI){
		return getDesiredVertex(getAlignment(indexSelected), containerVertex, containerDI, objectVertex, objectDI);
	}
	
	/**
	 * Design for giving accurate position
	 *
	 * @param alignment
	 * @param containerVertex
	 * @param containerDI
	 * @param objectDI
	 * @return a point that meet the desire
	 * @author HomeletWei
	 * @see #getDesiredVertex(Alignment, Point, Dimension, Point, Dimension)
	 */
	public static Point getDesiredVertex(Alignment alignment, Point containerVertex, Dimension containerDI, Dimension objectDI){
		return getDesiredVertex(alignment, containerVertex, containerDI, null, objectDI);
	}
	
	/**
	 * TODO
	 *
	 * @param alignment the alignment that going to be compared
	 * @return true if the same, false otherwise
	 * @author HomeletWei
	 */
	public boolean equals(Alignment alignment){
		if(alignment == this || alignment == this.equivelent){
			return true;
		}
		return false;
	}
}
