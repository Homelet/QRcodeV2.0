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

/**
 * TODO
 *
 * @author HomeletWei
 * @date Apr 17, 2018
 */
public abstract class Function{
	
	Coefficient coefficient;
	
	/**
	 * constructor for Function
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public Function(Coefficient functionCoefficient){
		this.coefficient = functionCoefficient;
	}
	
	public static int[] getRelativeLocation(int x, int y, int scale){
		return new int[]{ getRelativeX(x, scale), getRelativeY(y, scale) };
	}
	
	public static int getRelativeX(int x, int scale){
		return Math.abs(-scale - x);
	}
	
	public static int getRelativeY(int y, int scale){
		return Math.abs(scale - y);
	}
}
