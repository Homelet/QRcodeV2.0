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
public class RelationFunction extends Function{
	
	Relation relation;
	
	/**
	 * constructor for RelationFunction
	 * TODO
	 *
	 * @author HomeletWei
	 */
	public RelationFunction(Relation relation, Coefficient coefficient){
		super(coefficient);
		this.relation = relation;
	}
	
	public double[] doEquate(int x){
		return relation.equate(x, coefficient);
	}
}
