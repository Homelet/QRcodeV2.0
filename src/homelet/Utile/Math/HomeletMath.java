/**
 * @author HomeletWei
 * @date Apr 17, 2018
 */
/*
 * Update Log:
 * ****************************************************
 * Name:
 * Date:
 * Description:
 * *****************************************************
 */
package homelet.Utile.Math;

import homelet.Utile.Utility;

import java.awt.*;

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 17, 2018
 */
public class HomeletMath{
	
	private static HomeletMath homeletMath = new HomeletMath();
	
	private HomeletMath(){
	}
	
	public static HomeletMath getMath(){
		return homeletMath;
	}
	
	/**
	 * TODO
	 *
	 * @param function
	 * @param x
	 * @param y
	 * @return
	 * @author HomeletWei
	 */
	public boolean isOnEquation(PolynomialFunction function, double x, double y){
		return y == function.doEquate(x);
	}
	
	/**
	 * TODO
	 *
	 * @param arcFunction
	 * @param x
	 * @param y
	 * @return
	 * @author HomeletWei
	 */
	public int isInsideFunction(RelationFunction arcFunction, int x, int y){
		int upperLimit = (int) (arcFunction.doEquate(x)[0]);
		int lowerLimit = (int) (arcFunction.doEquate(x)[1]);
		return Utility.getUtility().between(y, upperLimit, lowerLimit);
	}
	
	/**
	 * TODO
	 *
	 * @param rect
	 * @return
	 * @author HomeletWei
	 */
	public double getDiagonal(Rectangle rect){
		return getDiagonal(rect.getSize());
	}
	
	/**
	 * TODO
	 *
	 * @param rect
	 * @return
	 * @author HomeletWei
	 */
	public double getDiagonal(Dimension rect){
		return Math.pow(Math.pow(rect.getWidth(), 2.0) + Math.pow(rect.getHeight(), 2.0), 0.5);
	}
	
	/**
	 * get the endPoint for the vector and the startPoint
	 *
	 * @param start
	 * @param vector
	 * @return the endPoint as {@link PrecisePoint}
	 * @author HomeletWei
	 */
	public PrecisePoint getEndPoint(Point start, Vector vector){
		return new PrecisePoint(start.x + Math.cos(vector.getDirection().getRadiens()) * vector.getMagnitude(), start.y + Math.sin(vector.getDirection().getRadiens()) * vector.getMagnitude());
	}
	
	/**
	 * precition the value as the numbers of digits takes in
	 *
	 * @param value
	 * @param numbersOfDigits
	 * @return the precision as the numbers of digits
	 * @author HomeletWei
	 */
	public String percision(double value, int numbersOfDigits){
		return percision(String.valueOf(value), numbersOfDigits);
	}
	
	/**
	 * precition the value as the numbers of digits takes in
	 *
	 * @param value
	 * @param numbersOfDigits
	 * @return the precision as the numbers of digits
	 * @author HomeletWei
	 */
	public String percision(String value, int numbersOfDigits){
		int dotIndex = value.lastIndexOf('.');
		if(dotIndex == -1)
			return value;
		int endIndex = dotIndex + numbersOfDigits + 1;
		return value.substring(0, endIndex > value.length() ? value.length() : endIndex);
	}
	
	/**
	 * precition the value as the numbers of digits takes in
	 *
	 * @param value
	 * @param numbersOfDigits
	 * @return the precision as the numbers of digits
	 * @author HomeletWei
	 */
	public String percision(float value, int numbersOfDigits){
		return percision(String.valueOf(value), numbersOfDigits);
	}
	
	/**
	 * @param d1
	 * @param d2
	 * @param container
	 * @author HomeletWei
	 */
	public void maxDimension(Dimension d1, Dimension d2, Dimension container){
		container.setSize(Math.max(d1.width, d2.width), Math.max(d1.height, d2.height));
	}
}
