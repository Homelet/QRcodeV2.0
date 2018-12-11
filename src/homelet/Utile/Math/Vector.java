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

/**
 * TODO
 *
 * @author HomeletWei
 * @date Jun 11, 2018
 */
public class Vector{
	
	double    magnitude;
	Direction direction;
	
	/**
	 * constructor for Vertex
	 *
	 * @param magnitude
	 * @param direction
	 * @author HomeletWei
	 */
	public Vector(double magnitude, Direction direction){
		this.magnitude = magnitude;
		this.direction = direction;
	}
	
	/**
	 * constructor for Vector
	 *
	 * @author HomeletWei
	 */
	public Vector(){
	}
	
	/** @return magnitude */
	public double getMagnitude(){
		return magnitude;
	}
	
	/** @param magnitude : magnitude */
	public void setMagnitude(double magnitude){
		this.magnitude = magnitude;
	}
	
	/** @return direction */
	public Direction getDirection(){
		return direction;
	}
	
	/** @param direction : direction */
	public void setDirection(Direction direction){
		this.direction = direction;
	}
}
