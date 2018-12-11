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
public class PolynomialFunction extends Function{
	
	Polynomial equation;
	
	/**
	 * constructor for Function
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public PolynomialFunction(Polynomial equation, Coefficient coefficient){
		super(coefficient);
		this.equation = equation;
		this.coefficient = coefficient;
	}
	
	public double doEquate(double x){
		return equation.equate(x, coefficient);
	}
}
