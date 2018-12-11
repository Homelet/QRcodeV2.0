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
 * @date Jun 11, 2018
 */
package homelet.Utile.Math;

import java.awt.*;

/**
 * @author HomeletWei
 * @date Jun 11, 2018
 */
public class PrecisePoint{
	
	double x;
	double y;
	
	/**
	 * constructor for PrecisedPoint
	 * TODO
	 *
	 * @param x
	 * @param y
	 * @author HomeletWei
	 */
	public PrecisePoint(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return a newly allocated point
	 * @author HomeletWei
	 */
	public Point toPoint(){
		return new Point((int) x, (int) y);
	}
	
	/** @return x */
	public double getX(){
		return x;
	}
	
	/** @param x : x */
	public void setX(double x){
		this.x = x;
	}
	
	/** @return y */
	public double getY(){
		return y;
	}
	
	/** @param y : y */
	public void setY(double y){
		this.y = y;
	}
}
