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
public class Coefficient{
	
	double[] coefficient;
	
	/**
	 * constructor for Parameters
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public Coefficient(double... coefficient){
		this.coefficient = coefficient;
	}
	
	/** @return parameters */
	public double get(int index){
		return coefficient[index];
	}
	
	/** @param parameters : parameters */
	public void setCoefficient(double[] coefficient){
		this.coefficient = coefficient;
	}
}
