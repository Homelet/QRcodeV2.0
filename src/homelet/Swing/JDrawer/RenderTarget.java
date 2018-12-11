/**
 * @author HomeletWei
 * @date Apr 11, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Swing.JDrawer;

import java.awt.*;

/**
 * a renderTarget is a target object which allows to draw on a certain graphics object within the specific Dimension
 *
 * @author HomeletWei
 * @date Apr 11, 2018
 */
public interface RenderTarget extends Render{
	
	/**
	 * sepcific the dimension this acquired, and calculate the final dimension for rendering
	 * <p>
	 * this method may be called many time
	 * </p>
	 * <p>
	 * if this method returns null, represent the current object will hold the same dimention as the canvas
	 * </p>
	 *
	 * @return the dimension of the vertex
	 * @author HomeletWei
	 */
	public abstract Dimension getDimension();
	
	/**
	 * use the frame dimension takes in to calculate the vertex
	 * <p>
	 * if this method returns null, represent the current object will hold the vertex as 0, 0 (top left as the canvas)
	 * </p>
	 *
	 * @param frameDimension
	 * @return the vertex of the graphics
	 * @author HomeletWei
	 */
	public abstract Point getVertex(Dimension frameDimension, Dimension objectDimension);
}
